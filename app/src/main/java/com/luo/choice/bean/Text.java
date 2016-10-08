package com.luo.choice.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Text extends BmobObject {
	public BmobFile pagerPic;
	//type用数字表示 1一段文字 2一本书 3一首诗歌
//	type(诗歌，一段文字，一本书)
//	title（标题）
//	content（内容）
//	source（来源）
//	date（日期）
//	author(作者)
//	recommened(referrer推荐人)
//	reason（推荐语
	public String type;
	public String title;
	public String content;
	public BmobFile typeImage;
	public String date;

	public String source;
	public String author;
	public String referrer;
	public String reason;
	public Integer typeNumber;

	public Integer getTypeNumber() {
		return typeNumber;
	}

	public void setTypeNumber(Integer typeNumber) {
		this.typeNumber = typeNumber;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public BmobFile getTypeImage() {
		return typeImage;
	}
	public void setTypeImage(BmobFile typeImage) {
		this.typeImage = typeImage;
	}

	public String getReferrer() {
		return referrer;
	}

	public BmobFile getPagerPic() {
		return pagerPic;
	}

	public void setPagerPic(BmobFile pagerPic) {
		this.pagerPic = pagerPic;
	}
}
