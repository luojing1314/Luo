package com.luo.choice.crash;

import android.content.Context;
import android.text.TextUtils;

import com.luo.choice.bean.AppException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luojing on 2016/9/5.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private Context mContext;
    private String mExceptionInfo = null;
    private static CrashHandler INSTANCE = new CrashHandler();
    private DateFormat m = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /** 获取CrashHandler实例 ,单例模式 */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }
    private boolean handleException(Throwable throwable) {
        if (throwable == null) {
            return false;
        }
        mExceptionInfo = getErrorInfoFromException(throwable);
        if(!TextUtils.isEmpty(mExceptionInfo)){
            AppException appException = new AppException();
            appException.setDate(m.format(new Date(System.currentTimeMillis())));
            appException.setExceptionDetail(mExceptionInfo);
            appException.setPhoneInfo("手机型号: " + android.os.Build.MODEL + ",SDK版本:"
                    + android.os.Build.VERSION.SDK_INT + ",系统版本:"
                    + android.os.Build.VERSION.RELEASE);
            appException.save();
        }
        return true;
    }
    public static String getErrorInfoFromException(Throwable ex) {
        if (ex != null) {
            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            printWriter.close();
            String result = writer.toString();
            return result;
        } else {
            return null;
        }
    }
}
