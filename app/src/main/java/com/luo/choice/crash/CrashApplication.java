package com.luo.choice.crash;

import android.app.Application;
import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by luojing on 2016/8/23.
 */
public class CrashApplication extends Application{
    private  Context context;
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        new Thread() {
            @Override
            public void run() {
                JPushInterface.setDebugMode(true);
                JPushInterface.init(context);
            }
        }.start();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(context);
    }
}
