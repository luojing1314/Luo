package com.luo.choice.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by luojing on 2016/8/28.
 */
public class Recommend extends BmobObject {
    public String type;
    public String titleAndAuthor;
    public String reason;
    public String referrer;

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitleAndAuthor() {
        return titleAndAuthor;
    }
    public void setTitleAndAuthor(String titleAndAuthor) {
        this.titleAndAuthor = titleAndAuthor;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
