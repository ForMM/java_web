package com.xiaoyuan.web.article.service;

import com.xiaoyuan.common.util.Result;
import com.xiaoyuan.web.article.model.ArticleModel;

public interface ArticleService {
	  /**
	    *保存、发布文章
	    * */
	   public Result pubArticle(ArticleModel articleModel,Integer submitStatus);
	   
	   /**
	    * 查看用户文章
	    * */
	   public Result userArticleList(Long userId,Integer page ,Integer pageSize);
	   
	   /**
	    * 查看用户文章(保存或发布)
	    * */
	   public Result userArticleListByStatus(String operaType,Integer submitStatus,Integer articleType,Integer page, Integer pageSize);
	   
	   /**
	    * 获取文章标签
	    * */
	   public Result articleTags();
	   
	   /**
	    * 修改文章
	    * */
	   public Result modifyArticle(ArticleModel articleModel);
	   
	   /**
	    * 文章基本信息
	    * */
	   public Result articleMsg(Long articleId);
	   
	   /**
	    * 文章评论列表
	    * */
	   public Result articleCommentList(Long articleId,Integer page,Integer pageSize);
	   
	   /**
	    * 文章详情
	    * */
	   public Result articleDetail(Long articleId);
	   
	   /**
	    * 查看我学校的文章
	    * */
	   public Result mySchoolArticleList(Integer submitStatus,Integer articleType,Integer page ,Integer pageSize);
	   
	   /**
	    * 投稿
	    * */
	   public Result submitArticle(Long articleId,Integer submitStatus);
	   
	   /**
	    * 置顶
	    * */
	   public Result articleTop(Long articleId);
	   
	   /**
	    * 删除
	    * */
	   public Result delArticle(Long articleId);
	   /**
	    * 评论文章
	    * */
	   public Result commentArticle(Long articleId,Long commentId,String content);
	   
	   /**
	    * 子评论条数
	    * */
	   Result subCommentList(Long articleId,Long commentId, Integer page,
	 		Integer pageSize);
}
