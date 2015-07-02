package com.xeehoo.health.nurse.bean;

public class Nurse {
	private Integer nurseID;
	private String title;
	private String time;
	private Integer likes;
	private Integer comments;
	private Integer views;
	private String image;
	public Integer getNurseID() {
		return nurseID;
	}
	public void setNurseID(Integer nurseID) {
		this.nurseID = nurseID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
