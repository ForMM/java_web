package com.xiaoyuan.web.article.dto;

import java.util.Date;
import java.util.List;

public class ArticleListDto {
	  private Long userId;
	  private String headImg;
	  private String userName;
	  private Long articleId;
	  private String articleTitle;
	  private List<String> imgs;
	  private String shortContent;
	  private Date publisTime;
	  private int comments;
	  private String tagName;
	  private Integer articleType;
	  private Integer submitStatus;
	public Integer getArticleType() {
		return articleType;
	}
	public void setArticleType(Integer articleType) {
		this.articleType = articleType;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public Long getUserId() {
		return userId;
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
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public List<String> getImgs() {
		return imgs;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}
	public String getShortContent() {
		return shortContent;
	}
	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}
	public Date getPublisTime() {
		return publisTime;
	}
	public void setPublisTime(Date publisTime) {
		this.publisTime = publisTime;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Integer getSubmitStatus() {
		return submitStatus;
	}
	public void setSubmitStatus(Integer submitStatus) {
		this.submitStatus = submitStatus;
	}
	  
}
