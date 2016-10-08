package com.luo.choice.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by luojing on 2016/8/28.
 */
public class AppException extends BmobObject {
    public String exceptionDetail;
    public String phoneInfo;
    public String Date;

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public String getPhoneInfo() {
        return phoneInfo;
    }

    public void setPhoneInfo(String phoneInfo) {
        this.phoneInfo = phoneInfo;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
