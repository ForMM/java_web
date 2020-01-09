package com.xiaoyuan.web.article.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoyuan.common.content.FinVal;
import com.xiaoyuan.common.content.Status;
import com.xiaoyuan.common.entity.User;
import com.xiaoyuan.common.log.BKLogger;
import com.xiaoyuan.common.log.LogTool;
import com.xiaoyuan.common.util.Help;
import com.xiaoyuan.common.util.LayUIResultUtil;
import com.xiaoyuan.common.util.Result;
import com.xiaoyuan.web.article.dto.LayUploadResult;
import com.xiaoyuan.web.article.model.ArticleModel;
import com.xiaoyuan.web.article.service.ArticleService;
import com.xiaoyuan.web.article.service.FileService;
import com.xiaoyuan.web.base.BaseController;
import com.xiaoyuan.web.user.dto.UserInfoDto;
import com.xiaoyuan.web.user.service.UserService;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController{
  
	BKLogger logger = BKLogger.getLogger(ArticleController.class);
	private LogTool log = LogTool.getInstance(ArticleController.class);
	@Autowired
	private ArticleService articleService;
	@Autowired
	private FileService fileService;	
	@Autowired
	private  UserService userService;
	
	 @RequestMapping("/articlePage")	 
	 public String articlePageController(HttpServletRequest request,Model model){		 
		 Result result = articleService.articleTags();
		 Map<String,Object> data = (Map<String,Object>)result.getData();
		 model.addAttribute("dataList", data.get("dataList"));	
		 model.addAttribute("userType", data.get("userType"));	
		 
		 UserInfoDto currentUser = userService.getCurrentUser();
		 model.addAttribute("currentUser", currentUser);
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 
		 return "website/article/addArticle";	 
	 }
	
	 @RequestMapping("/saveArticle")
	 @ResponseBody
	 public void saveArticleController(HttpServletRequest request,Model model,ArticleModel articleModel){
		 Result result = null;
		 try{
			 result = articleService.pubArticle(articleModel,FinVal.ARTICLE_STATU_DRAFT);
		 }catch(Exception ex){
			 log.error("保存文章失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("保存文章", result));		 
	 }
	 
	 @RequestMapping("/pubArticle")
	 @ResponseBody
	 public void pubArticleController(HttpServletRequest request,Model model,ArticleModel articleModel){
		 Result result = null;
		 try{
			 result = articleService.pubArticle(articleModel,null);
		 }catch(Exception ex){
			 log.error("发布文章失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("发布文章", result));		 
	 }
	 
	 @RequestMapping(value = "/uploadArticleImg.do",method=RequestMethod.POST)	
	 @ResponseBody
	 public void uploadArticleImg(@RequestParam("file") MultipartFile file,HttpServletRequest request, Model model){
		 Result result=null;
		 try{
			 result = fileService.uploadOSSFile(file,FinVal.ARTICLE);		
		 }catch(Exception ex){
			 log.error("上传文章图片失败！");
			 log.error("上传文章图片失败！", ex);
			 result=new Result(); 
		 }
		 
		 LayUploadResult layUploadResult = LayUIResultUtil.convertResult(result);
		 
		 getPrintWriter().print(logger.infobk("上传文章图片", layUploadResult));
	 }
	 
	 @RequestMapping("/articleDraftListPage")	 
	 public String articleDraftListPageController(HttpServletRequest request,Model model){
		 User user = userService.getCurUser();
		 model.addAttribute("userId", user.getnUserId());
		 
		 UserInfoDto currentUser = userService.getCurrentUser();
		 model.addAttribute("currentUser", currentUser);
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 
		 return "website/article/articleDraftList";	
	 }
	 
	 @RequestMapping("/articlePubListPage")	 
	 public String articleListPubPageController(HttpServletRequest request,Model model){
		 User user = userService.getCurUser();
		 model.addAttribute("userId", user.getnUserId());
		 UserInfoDto currentUser = userService.getCurrentUser();
		 model.addAttribute("currentUser", currentUser);
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 return "website/article/articlePubList";	
	 }
	 
	 @RequestMapping("/verifyArticleListPage")	 
	 public String verifyArticleListPageController(HttpServletRequest request,Model model){
		 User user = userService.getCurUser();
		 model.addAttribute("userId", user.getnUserId());
		 
		 UserInfoDto currentUser = userService.getCurrentUser();
		 model.addAttribute("currentUser", currentUser);
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 
		 return "website/article/schoolArticleList";	
	 }
	 
	 @RequestMapping("/userAriclePubList")
	 @ResponseBody
	 public void userAriclePubListController(HttpServletRequest request,Model model,Long userId,Integer page,Integer pageSize){
		 Result result = null;
		 try{
			 result= articleService.userArticleListByStatus("draft",FinVal.ARTICLE_STATU_DRAFT,null, page, pageSize);
		 }catch(Exception ex){
			 log.error("查询文章列表失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("查询文章列表输出列表", result));
	 }
	 
	 @RequestMapping("/userAricleDraftList")
	 @ResponseBody
	 public void userAricleDraftListController(HttpServletRequest request,Model model,Long userId,Integer page,Integer pageSize){
		 Result result = null;
		 try{
			 result= articleService.userArticleListByStatus(null,FinVal.ARTICLE_STATU_DRAFT,null, page, pageSize);
		 }catch(Exception ex){
			 log.error("查询文章列表失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("查询文章列表输出列表", result));
	 }
	 
	 /**
	  * 获取文章的基本信息
	  * @param request
	  * @param model
	  * @param accesstoken
	  * @param articleId
	  */
	 @RequestMapping("/articleMsg")
	 @ResponseBody
	 public void articleMsgController(HttpServletRequest request,Model model,String accesstoken,Long articleId){
		 Result result = null;
		 try{
			 result= articleService.articleMsg(articleId);
		 }catch(Exception ex){
			 log.error("查询文章基本信息失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("查询文章基本信息输出列表", result));
	 }
	 
	 @RequestMapping("/modifyArticle")
	 @ResponseBody
	 public void modifyArticleController(HttpServletRequest request,Model model,ArticleModel articleModel){
		 Result result = null;
		 try{
			 result= articleService.modifyArticle(articleModel);
		 }catch(Exception ex){
			 log.error("发布文章失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("修改文章输出列表", result));
	 }
	 
	 @RequestMapping("/updateArticlePage")	 
	 public String updateArticlePageController(HttpServletRequest request,Model model,Long articleId){		 
		 Result result = articleService.articleTags();
		 Map<String,Object> data = (Map<String,Object>)result.getData();
		 model.addAttribute("articleId", articleId);
		 model.addAttribute("userType", data.get("userType"));	
		 model.addAttribute("dataList", data.get("dataList"));
		 
		 UserInfoDto currentUser = userService.getCurrentUser();
		 model.addAttribute("currentUser", currentUser);
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 return "website/article/updateArticle";	 
	 }
	 @RequestMapping("/commentList")
	 @ResponseBody
	 public void articleCommentListController(HttpServletRequest request,Model model,String accesstoken,Long articleId,Integer page,Integer pageSize){
		 Result result = null;
		 try{
			 result= articleService.articleCommentList(articleId, page, pageSize);
		 }catch(Exception ex){
			 log.error("查询文章评论列表失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("文章评论列表输出列表", result));
	 }
	 
	 @RequestMapping("/articleDetail")
	 @ResponseBody
	 public void articleDetailController(HttpServletRequest request,Model model,String accesstoken,Long articleId){
		 Result result = null;
		 try{
			 result= articleService.articleDetail(articleId);
		 }catch(Exception ex){
			 log.error("查询文章详情失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("查询文章详情输出列表", result));
	 }
	 
	 @RequestMapping("/articleInfoPage")	 
	 public String articleInfoPageController(HttpServletRequest request,Model model,Long articleId){		 		 		
		 model.addAttribute("articleId", articleId);
		 User user = userService.getCurUser();
		 
		 UserInfoDto currentUser = userService.getCurrentUser();
		 model.addAttribute("currentUser", currentUser);
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 
		 if(user.getnUserType() == FinVal.USER_TYPE_SCHOOL){
			 return "website/article/schoolArticleInfo";	
		 }else{
			 return "website/article/articleInfo";	 	
		 }	
	 }
	 
	 @RequestMapping("/schoolArticleListPage")	 
	 public String schoolArticleListPageController(HttpServletRequest request,Model model){
		 
		 UserInfoDto currentUser = userService.getCurrentUser();
		 model.addAttribute("currentUser", currentUser);
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 
		 return "website/article/schoolArticleList";	 
	 }
	 
	 @RequestMapping("/mySchoolAricleList")
	 @ResponseBody
	 public void mySchoolAricleListController(HttpServletRequest request,Model model,Integer submitStatus,Integer articleType,Integer page,Integer pageSize){
		 Result result = null;
		 try{
			 result= articleService.mySchoolArticleList(submitStatus,articleType,page, pageSize);
		 }catch(Exception ex){
			 log.error("查询文章列表失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("查询文章列表输出列表", result));
	 }
	 
	 @RequestMapping("/submitArticle")
	 @ResponseBody
	 public void submitArticleController(HttpServletRequest request,Model model,Long articleId){
		 Result result = null;
		 try{
			 result= articleService.submitArticle(articleId,FinVal.ARTICLE_STATU_SUBMIT);
		 }catch(Exception ex){
			 log.error("投稿文章失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("投稿文章输出列表", result));
	 }
	 
	 @RequestMapping("/approveArticle")
	 @ResponseBody
	 public void approveArticleController(HttpServletRequest request,Model model,Long articleId){
		 Result result = null;
		 try{
			 result= articleService.submitArticle(articleId,FinVal.ARTICLE_STATU_SUBMIT_SUCCESS);
		 }catch(Exception ex){
			 log.error("审核文章失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("审核文章", result));
	 }
	 
	 @RequestMapping("/notApproveArticle")
	 @ResponseBody
	 public void notApproveArticleController(HttpServletRequest request,Model model,Long articleId){
		 Result result = null;
		 try{
			 result= articleService.submitArticle(articleId,FinVal.ARTICLE_STATU_SUBMIT_FALURE);
		 }catch(Exception ex){
			 log.error("审核文章失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("审核文章", result));
	 }
	 
	 @RequestMapping("/articleTop.do")
	 @ResponseBody
	 public void articleTopController(HttpServletRequest request,Model model,Long articleId){
		 Result result = null;
		 try{
			 result= articleService.articleTop(articleId);
		 }catch(Exception ex){
			 log.error("文章置顶失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("文章置顶", result));
	 }
	 
	 @RequestMapping("/delArticle.do")
	 @ResponseBody
	 public void delArticleController(HttpServletRequest request,Model model,Long articleId){
		 Result result = null;
		 try{
			 result = articleService.delArticle(articleId);
		 }catch(Exception ex){
			 log.error("删除文章失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("删除文章", result));		 
	 }
	 
	 @RequestMapping("/replyComment.do")
	 @ResponseBody
	 public void replyCommentController(HttpServletRequest request,Model model,Long targetId,Long commentId,String commentContent){
		 Result result = null;
		 try{
			 result = articleService.commentArticle(targetId, commentId, commentContent);
		 }catch(Exception ex){
			 log.error("添加评论回复失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("添加评论回复", result));		 
	 }
	 
	 @RequestMapping("/subCommentList.do")
	 @ResponseBody
	 public void subCommentListController(HttpServletRequest request,Model model,Long articleId,Long commentId,Integer page,Integer pageSize){
		 Result result = null;
		 try{
			 result= articleService.subCommentList(articleId, commentId, page, pageSize);
		 }catch(Exception ex){
			 log.error("查询评论的子评论列表失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("查询评论的子评论列表输出列表", result));
	 }
	 
	 private String redirectPage(UserInfoDto currentUser){
		 if(currentUser.getUserType()==FinVal.USER_TYPE_ORG&&currentUser.getAuthStatus()==FinVal.AUTH_STATUS_UN_SUBMIT){
			 return "website/user/orgSignup";
		 }
		 if(currentUser.getUserType()==FinVal.USER_TYPE_SCHOOL&&currentUser.getAuthStatus()==FinVal.AUTH_STATUS_UN_SUBMIT||currentUser.getUserType()==FinVal.USER_TYPE_SCHOOL&&currentUser.getAuthStatus()==FinVal.AUTH_STATUS_SUBMIT){
			 return "website/user/schoolSignup";
		 }
		 return null;
	 }
}
