package com.xiaoyuan.web.course.dto;

public class CourseUsersDto {
   private Long userId;
   private String headImg;
   private String userName;
   private String schoolName;
   private String major;
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public String getHeadImg() {
	return headImg;
}
public void setHeadImg(String headImg) {
	this.headImg = headImg;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getSchoolName() {
	return schoolName;
}
public void setSchoolName(String schoolName) {
	this.schoolName = schoolName;
}
public String getMajor() {
	return major;
}
public void setMajor(String major) {
	this.major = major;
}
   
}
