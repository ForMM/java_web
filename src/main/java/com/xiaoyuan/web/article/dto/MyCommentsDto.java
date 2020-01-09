package com.xiaoyuan.web.article.dto;

import java.util.Date;

public class MyCommentsDto {
  private Long replyArticleId;
  private Long replyCommentId;
  private String replyUserName;
  private String replyHeadImg;
  private String replyContent;
  private Date replyDate;
  private String MyContent;
public String getReplyUserName() {
	return replyUserName;
}
public void setReplyUserName(String replyUserName) {
	this.replyUserName = replyUserName;
}
public String getReplyHeadImg() {
	return replyHeadImg;
}
public void setReplyHeadImg(String replyHeadImg) {
	this.replyHeadImg = replyHeadImg;
}
public String getReplyContent() {
	return replyContent;
}
public void setReplyContent(String replyContent) {
	this.replyContent = replyContent;
}
public Date getReplyDate() {
	return replyDate;
}
public void setReplyDate(Date replyDate) {
	this.replyDate = replyDate;
}
public String getMyContent() {
	return MyContent;
}
public void setMyContent(String myContent) {
	MyContent = myContent;
}
public Long getReplyArticleId() {
	return replyArticleId;
}
public void setReplyArticleId(Long replyArticleId) {
	this.replyArticleId = replyArticleId;
}
public Long getReplyCommentId() {
	return replyCommentId;
}
public void setReplyCommentId(Long replyCommentId) {
	this.replyCommentId = replyCommentId;
}
  
}
