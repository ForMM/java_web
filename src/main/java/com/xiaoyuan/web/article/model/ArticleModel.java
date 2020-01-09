package com.xiaoyuan.web.article.model;

public class ArticleModel {
	private Long articleId;
	private String title;
	private String contents;
	private String articleImgs;
	private String tag;
	private Integer top;
	private String contentHtml;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
		
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getArticleImgs() {
		return articleImgs;
	}
	public void setArticleImgs(String articleImgs) {
		this.articleImgs = articleImgs;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getTop() {
		return top;
	}
	public void setTop(Integer top) {
		this.top = top;
	}
	public String getContentHtml() {
		return contentHtml;
	}
	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	
	
}
