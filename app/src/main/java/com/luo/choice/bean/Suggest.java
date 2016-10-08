package com.luo.choice.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by luojing on 2016/8/28.
 */
public class Suggest extends BmobObject{
    public String suggestContent;
    public String contactEmail;
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuggestContent() {
        return suggestContent;
    }

    public void setSuggestContent(String suggestContent) {
        this.suggestContent = suggestContent;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
