package com.xiaoyuan.web.article.dto;

public class ArticleMsgDto {
  private Long articleId;
  private String content;
  private String tag;
  private String title;
  private Integer top;
  private String contentHtml;
public Long getArticleId() {
	return articleId;
}
public void setArticleId(Long articleId) {
	this.articleId = articleId;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getTag() {
	return tag;
}
public void setTag(String tag) {
	this.tag = tag;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
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


}
