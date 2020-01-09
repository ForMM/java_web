package com.xiaoyuan.web.article.dto;

import java.util.Date;

public class ArticleDetailDto {
   private Long articleId;
   private String title;
   private String headImg;
   private String userName;
   private String schoolName;
   private String major;
   private Integer isAttention;
   private String content;
   private Date createDate;
   private String tagName;
   private Integer articleType;
   private Integer submitStatus;
   private String contentHtml;
   private String grade;
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public Long getArticleId() {
	return articleId;
}
public void setArticleId(Long articleId) {
	this.articleId = articleId;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
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
public String getContent() {
	return content;
}
public Integer getIsAttention() {
	return isAttention;
}
public void setIsAttention(Integer isAttention) {
	this.isAttention = isAttention;
}
public void setContent(String content) {
	this.content = content;
}
public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
public String getTagName() {
	return tagName;
}
public void setTagName(String tagName) {
	this.tagName = tagName;
}
public Integer getArticleType() {
	return articleType;
}
public void setArticleType(Integer articleType) {
	this.articleType = articleType;
}
public Integer getSubmitStatus() {
	return submitStatus;
}
public void setSubmitStatus(Integer submitStatus) {
	this.submitStatus = submitStatus;
}
public String getContentHtml() {
	return contentHtml;
}
public void setContentHtml(String contentHtml) {
	this.contentHtml = contentHtml;
}
   
   
   
}
