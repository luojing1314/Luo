package com.luo.choice.bean;


import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Video extends BmobObject{
	public BmobFile resource;
	public String title;
	public String des;
	public String author;
	public String typeDes;
	public Integer type;
	public BmobFile thumbnail;
	public String referrer;
	public String reason;

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BmobFile getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(BmobFile thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeDes() {
		return typeDes;
	}

	public void setTypeDes(String typeDes) {
		this.typeDes = typeDes;
	}

	public BmobFile getResource() {
		return resource;
	}
	public void setResource(BmobFile resource) {
		this.resource = resource;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
