package com.xiaoyuan.web.article.dto;

import java.util.Date;

public class SubCommentDto {
	private Long articleId;
	private Long commentId;
	private Long targetId;
	 private String headImg;
	  private Date commentTime;
	  private String userName;
	  private String content;
	public String getHeadImg() {
		return headImg;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public Long getTargetId() {
		return targetId;
	}
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	
}
