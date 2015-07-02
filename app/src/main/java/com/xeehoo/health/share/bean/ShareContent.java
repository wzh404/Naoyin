package com.xeehoo.health.share.bean;

public class ShareContent {
	private Integer shareID;
	private String name;
	private String time;
	private String content;
	private String[] images;
	private Integer likes;
	private Integer comments;
	
	public Integer getShareID() {
		return shareID;
	}
	public void setShareID(Integer shareID) {
		this.shareID = shareID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}	
}
