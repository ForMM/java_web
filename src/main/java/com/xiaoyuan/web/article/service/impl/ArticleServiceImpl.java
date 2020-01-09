package com.xiaoyuan.web.article.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.common.content.FinVal;
import com.xiaoyuan.common.content.Status;
import com.xiaoyuan.common.dao.ArticleMapper;
import com.xiaoyuan.common.dao.CommentMapper;
import com.xiaoyuan.common.dao.TagMapper;
import com.xiaoyuan.common.dao.UserFansMapper;
import com.xiaoyuan.common.dao.UserMapper;
import com.xiaoyuan.common.entity.Article;
import com.xiaoyuan.common.entity.Comment;
import com.xiaoyuan.common.entity.Tag;
import com.xiaoyuan.common.entity.User;
import com.xiaoyuan.common.entity.UserFans;
import com.xiaoyuan.common.log.LogTool;
import com.xiaoyuan.common.util.GsonUtil;
import com.xiaoyuan.common.util.Help;
import com.xiaoyuan.common.util.MUtil;
import com.xiaoyuan.common.util.Paginator;
import com.xiaoyuan.common.util.Result;
import com.xiaoyuan.web.article.dto.ArticleDetailDto;
import com.xiaoyuan.web.article.dto.ArticleListDto;
import com.xiaoyuan.web.article.dto.ArticleMsgDto;
import com.xiaoyuan.web.article.dto.CommentDto;
import com.xiaoyuan.web.article.dto.SubCommentDto;
import com.xiaoyuan.web.article.model.ArticleModel;
import com.xiaoyuan.web.article.service.ArticleService;
import com.xiaoyuan.web.user.service.UserService;

@Service
public class ArticleServiceImpl implements ArticleService {
	private LogTool log = LogTool.getInstance(ArticleServiceImpl.class);
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserFansMapper  userFansMapper;
	
	@Override
	public Result pubArticle(ArticleModel articleModel,Integer submitStatus) {

		// TODO Auto-generated method stub
		Result result = new Result();
		log.info("发布文章，开始。。。");		
		
		if(Help.isNull(articleModel.getContents())){
			log.info("文章内容为空！");
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		Article record = new Article();
		record.setcArticleContent(articleModel.getContents());
		record.setcContentHtml(articleModel.getContentHtml());
		record.setcArticleImgs(articleModel.getArticleImgs());
		record.setcArticleTag(articleModel.getTag());
		
		User currentUser = userService.getCurUser();
		Integer userType = currentUser.getnUserType();
		if(FinVal.USER_TYPE_SCHOOL.equals(userType)){
			record.setnArticleType(FinVal.ARTICLE_TYPE_SCHOOL);
			Integer top = articleModel.getTop();
			if(Help.isNull(top)||(top!=FinVal.DEFAULT_NO&&top!=FinVal.DEFAULT_YES)){
				top=FinVal.DEFAULT_NO;
			}
			if(top==FinVal.DEFAULT_YES){
				Long schoolId = currentUser.getnSchoolId();
				articleMapper.updateTopBySchoolId(schoolId);
			}
			record.setnTop(top);
		}else if(FinVal.USER_TYPE_ORG.equals(userType)){
			record.setnArticleType(FinVal.ARTICLE_TYPE_ORG);
		}else{
			record.setnArticleType(FinVal.ARTICLE_TYPE_USER);
		}
		if(Help.isNotNull(currentUser)){
			record.setcSchoolName(currentUser.getcSchoolName());
			record.setnSchoolId(currentUser.getnSchoolId());
			record.setnCreateId(currentUser.getnUserId());
		}
		
		if(currentUser.getnUserType().equals(FinVal.USER_TYPE_SCHOOL)){
			record.setnSubmitStatus(FinVal.ARTICLE_STATU_SUBMIT_SUCCESS);
		}else{
			record.setnSubmitStatus(FinVal.ARTICLE_STATU_UN_SUBMIT);
		}
        if(FinVal.ARTICLE_STATU_DRAFT.equals(submitStatus)){
        	record.setnSubmitStatus(FinVal.ARTICLE_STATU_DRAFT);
        }
		Date now = new Date();
		record.settCreateTime(now);
		record.settUpdateTime(now);
		record.setcArticleTitle(articleModel.getTitle());
		articleMapper.insertSelective(record);
		
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		return result;
	
	}

	@Override
	public Result userArticleList(Long userId, Integer page, Integer pageSize) {
		Result result = new Result();
		if(Help.isNull(userId)){
			log.debug("用户ID为空");
			result.setStatus(Status.user_not_exist_error_status);
			result.setInfo(Status.user_not_exist_error_info);
			return result;
		}
		User user = userMapper.selectByPrimaryKey(userId);
		if(Help.isNull(user )){
			log.debug("用户不存在");
			result.setStatus(Status.user_not_exist_error_status);
			result.setInfo(Status.user_not_exist_error_info);
			return result;
		}
		Map<String,Object> data = findArticles(userId, null, page, pageSize);
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		result.setData(data);
		return result;
	}
	
	
	
	private Map<String,Object> findArticles(Long userId,Long schoolId,Integer page,Integer pageSize){
		page=page==null?FinVal.DEFAULT_PAGE:page;
		pageSize=pageSize==null?FinVal.DEFAULT_PAGESIZE:pageSize;
		Map<String,Object> data=new HashMap<String,Object>();
		List<ArticleListDto> articleListDtos= new ArrayList<ArticleListDto>();
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("userId", userId);
		param.put("schoolId", schoolId);
		int countByParam = articleMapper.countByParam(param);
		Paginator paginator = new Paginator(0, pageSize);
		paginator.gotoPage(page);
		int pageCount = paginator.calcPageCount(countByParam); // 总页数
		
		param.put("startRow", paginator.getStartRow());
		param.put("pageSize", paginator.getPageSize());
		List<Article> list = articleMapper.findByParam(param);
		if(Help.isNotNull(list)){
			for(Article article:list){
				articleListDtos.add(convertArticleDto(article));
			}
		}
		data.put("dataList", articleListDtos);
		data.put("page", page);
		data.put("pageSize", pageSize);
		data.put("pageCount", pageCount);
		return 	data;
	}
	private ArticleListDto convertArticleDto(Article article){
		ArticleListDto articleListDto = new ArticleListDto();
		Long userId = article.getnCreateId();
		articleListDto.setUserId(userId);
		User user = userMapper.selectByPrimaryKey(userId);
		if(Help.isNotNull(user)){
			articleListDto.setUserName(user.getcUserRealname());
			articleListDto.setHeadImg(user.getcUserPortrait());
		}
		articleListDto.setArticleId(article.getnArticleId());
		articleListDto.setArticleTitle(article.getcArticleTitle());
		articleListDto.setComments(article.getnCountComment());
		articleListDto.setImgs(MUtil.convertStringToList(article.getcArticleImgs()));
		articleListDto.setPublisTime(article.gettCreateTime());
		articleListDto.setTagName(article.getcArticleTag());
		articleListDto.setArticleType(article.getnArticleType());
		articleListDto.setSubmitStatus(article.getnSubmitStatus());
		String articleContent = article.getcArticleContent();
		String shortContent="";
		if(Help.isNotNull(articleContent)){
			List<Map<String,String>> list = (List<Map<String,String>>) GsonUtil.jsonToObject(articleContent, List.class);
			
			if(Help.isNotNull(list)){
				StringBuffer contentBuf=new StringBuffer();
				for(Map<String,String> map :list){
					String content = map.get("content");
					if(Help.isNotNull(content))
					contentBuf.append(content);
				}
				String txtcontent =contentBuf.toString();
		        if(txtcontent.length()>100){
		        	shortContent=txtcontent.substring(0, 100);
		        }else{
		        	shortContent=txtcontent;
		        }
			}
			
		}
		articleListDto.setShortContent(shortContent);
		return articleListDto;
	}

	@Override
	public Result articleTags() {
		Result result = new Result();
		log.debug("开始获取文章标签。。。");
		List<String> tagNames = new ArrayList<String>();
		Map<String,Object> data = new HashMap<String,Object>();
		User curUser = userService.getCurUser();
		if(Help.isNotNull(curUser)){
			Integer userType = curUser.getnUserType();
			if(FinVal.USER_TYPE_PRIVATE.equals(userType)){
				tagNames.add("文章");
			}else{
				List<Tag> tags = tagMapper.articleTags();
				if(Help.isNotNull(tags)){
					for(Tag tag:tags){
						if(Help.isNotNull(tag.getcTagName()))
						tagNames.add(tag.getcTagName());
					}
				}
			}
		}
		data.put("userType", curUser.getnUserType());
		data.put("dataList", tagNames);
		result.setInfo(Status.success_info);
		result.setStatus(Status.success_status);
		
		
		result.setData(data);
		return result;
	}

	@Override
	public Result modifyArticle(ArticleModel articleModel) {
		// TODO Auto-generated method stub
		Result result = new Result();
		log.info("修改文章，开始。。。");
		User user = userService.getCurUser();
		if(Help.isNull(user)){
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		if(Help.isNull(articleModel.getContents())){
			log.info("文章内容为空！");
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		Article article = articleMapper.selectByPrimaryKey(articleModel.getArticleId());
		if(Help.isNull(article)){
			result.setStatus(Status.article_not_exist_status);
			result.setInfo(Status.article_not_exist_info);
			return result;
		}
		Long createId = article.getnCreateId();
		if((!user.getnUserId().equals(createId)||article.getnSubmitStatus().equals(FinVal.ARTICLE_STATU_SUBMIT_SUCCESS)) && user.getnUserType() == FinVal.USER_TYPE_PRIVATE){
			result.setStatus(Status.permission_error_status);
			result.setInfo(Status.permission_error_info);
			return result;
		}
		article.setcArticleContent(articleModel.getContents());
		article.setcContentHtml(articleModel.getContentHtml());
		article.setcArticleImgs(articleModel.getArticleImgs());
		article.setcArticleTag(articleModel.getTag());
		//record.setcSchoolName(user.getcSchoolName());
		//record.setnSchoolId(user.getnSchoolId());
		Date now = new Date();
		article.settUpdateTime(now);
		article.setnCreateId(user.getnUserId());
		article.setcArticleTitle(articleModel.getTitle());
		Integer top = articleModel.getTop();
		if(Help.isNull(top)||(top!=FinVal.DEFAULT_NO&&top!=FinVal.DEFAULT_YES)){
			top=FinVal.DEFAULT_NO;
		}
		if(top==FinVal.DEFAULT_YES){
			Long schoolId = user.getnSchoolId();
			articleMapper.updateTopBySchoolId(schoolId);
		}
		article.setnTop(top);
		
		articleMapper.updateByPrimaryKeySelective(article);
		
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		return result;
	}
	
	public Result articleMsg(Long articleId){
		Result result = new Result();
		User user = userService.getCurUser();
		if(Help.isNull(user)){
			log.info("获取用户信息失败");
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		Article article = articleMapper.selectByPrimaryKey(articleId);
		if(Help.isNull(article)){
			result.setStatus(Status.article_not_exist_status);
			result.setInfo(Status.article_not_exist_info);
			return result;
		}
		ArticleMsgDto articleMsgDto = new ArticleMsgDto();
		articleMsgDto.setArticleId(articleId);
		articleMsgDto.setContent(article.getcArticleContent());
		articleMsgDto.setContentHtml(article.getcContentHtml());
		articleMsgDto.setTag(article.getcArticleTag());
		articleMsgDto.setTitle(article.getcArticleTitle());
		articleMsgDto.setTop(article.getnTop());
		
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("data", articleMsgDto);
		result.setData(data);
		return result;
	}
	
	@Override
	public Result articleCommentList(Long articleId,
			Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		page=page==null?FinVal.DEFAULT_PAGE:page;
		pageSize=pageSize==null?FinVal.DEFAULT_PAGESIZE:pageSize;
		Result result = new Result();
		if(Help.isNull(articleId)){
			result.setStatus(Status.article_id_empty_status);
			result.setInfo(Status.article_id_empty_info);
			return result;
		}
		Article article = articleMapper.selectByPrimaryKey(articleId);
		if(Help.isNull(article)){
			result.setStatus(Status.article_not_exist_status);
			result.setInfo(Status.article_not_exist_info);
			return result;
		}
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("commentType", FinVal.COMMENT_TYPE_ARTICLE);
		param.put("targetId", articleId);
		param.put("isTop", 1);
		int comments = commentMapper.countByParam(param);

		Paginator paginator = new Paginator(0, pageSize);
		paginator.gotoPage(page);
		int pageCount = paginator.calcPageCount(comments); // 总页数
		
		param.put("startRow", paginator.getStartRow());
		param.put("pageSize", paginator.getPageSize());
		List<Comment> list = commentMapper.findByParam(param);
		List<SubCommentDto> commentDtos = new ArrayList<SubCommentDto>();
		if(Help.isNotNull(list)){
			for(Comment comment:list){
				CommentDto commentDto = new CommentDto();
				commentDto.setCommentTime(comment.gettCreateTime());
				commentDto.setContent(comment.getcCommentContent());
				commentDto.setCommentId(comment.getnCommentId());
				commentDto.setTargetId(comment.getnTargetId());
				Long userId = comment.getnUserId();
				User user = userMapper.selectByPrimaryKey(userId);
				if(Help.isNotNull(user)){
					commentDto.setHeadImg(user.getcUserPortrait());
					commentDto.setUserName(user.getcUserRealname());
				}
				log.debug("查询子评论条数");
				param.clear();
				param.put("commentType", FinVal.COMMENT_TYPE_ARTICLE);
				param.put("targetId", articleId);
				param.put("isTop", 0);
				param.put("nTopCommentId", comment.getnCommentId());
				
				int subComments = commentMapper.countByParam(param);
				commentDto.setSubComments(subComments);
				commentDtos.add(commentDto);
			}
		}
		Map<String, Object> data = new HashMap<String,Object>();
		data .put("dataList", commentDtos);
		data.put("page", page);
		data.put("pageSize", pageSize);
		data.put("pageCount", pageCount);
		
		param.clear();
		param.put("commentType", FinVal.COMMENT_TYPE_ARTICLE);
		param.put("targetId", articleId);
		int total = commentMapper.countByParam(param);
		data.put("total", total);
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		result.setData(data);
		return result;
	}
	
	@Override
	public Result articleDetail(Long articleId) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(Help.isNull(articleId)){
			log.info("articleId为空");
			result.setStatus(Status.article_not_exist_status);
			result.setInfo(Status.article_not_exist_info);
			return result;
		}
		Article article = articleMapper.selectByPrimaryKey(articleId);
		if(Help.isNull(article)){
			log.info("article不存在");
			result.setStatus(Status.article_not_exist_status);
			result.setInfo(Status.article_not_exist_info);
			return result;
		}
		
		ArticleDetailDto articleDetailDto = new ArticleDetailDto();
		articleDetailDto.setArticleId(articleId);
		articleDetailDto.setContent(article.getcArticleContent());
		articleDetailDto.setCreateDate(article.gettCreateTime());
		Long createId = article.getnCreateId();
		User author = userMapper.selectByPrimaryKey(createId);
		if(Help.isNotNull(author)){
			articleDetailDto.setHeadImg(author.getcUserPortrait());
			User sessionUser = userService.getCurUser();
			if(Help.isNotNull(sessionUser)&&!sessionUser.getnUserId().equals(author.getnUserId())){
				Map<String, Object> param = new HashMap<String,Object>();
				param.put("fansId", sessionUser.getnUserId());
				param.put("userId", author.getnUserId());
				List<UserFans> list = userFansMapper.findByParam(param );
				if(Help.isNotNull(list)){
					articleDetailDto.setIsAttention(FinVal.DEFAULT_YES);
				}else{
					articleDetailDto.setIsAttention(FinVal.DEFAULT_NO);
				}
			}else if(Help.isNull(sessionUser)){
				articleDetailDto.setIsAttention(FinVal.DEFAULT_NO);
			}
			articleDetailDto.setMajor(author.getcMajor());
			articleDetailDto.setSchoolName(author.getcSchoolName());
			articleDetailDto.setUserName(author.getcUserRealname());
			articleDetailDto.setGrade(author.getcGrade());
		}
		articleDetailDto.setTagName(article.getcArticleTag());
		articleDetailDto.setTitle(article.getcArticleTitle());
		articleDetailDto.setArticleType(article.getnArticleType());
		articleDetailDto.setSubmitStatus(article.getnSubmitStatus());
		
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("articleDetail", articleDetailDto);
		result.setData(data);
		return result;
	}

	@Override
	public Result userArticleListByStatus(String operaType,Integer submitStatus,Integer articleType,Integer page, Integer pageSize) {
		Result result = new Result();
		User user = userService.getCurUser();
		if(Help.isNull(user)){
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		Map<String, Object> param = new HashMap<String,Object>();
		if(user.getnUserType().equals(FinVal.USER_TYPE_SCHOOL)){
			param.put("schoolId", user.getnSchoolId());
			param.put("articleType", FinVal.ARTICLE_TYPE_SCHOOL);
		}else{
			param.put("userId", user.getnUserId());
		}
		
		if("draft".equals(operaType)){
			param.put("draftStatu", submitStatus);
		}else{
			param.put("submitStatu", submitStatus);
		}
		Map<String,Object> data = findArticles(param, page, pageSize);
		data.put("userType", user.getnUserType());
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		result.setData(data);
		return result;
	}

	
	
	@Override
	public Result mySchoolArticleList(Integer submitStatus,Integer articleType,Integer page, Integer pageSize) {
		Result result = new Result();
		User user = userService.getCurUser();
		if(Help.isNull(user)){
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		Long schoolId=user.getnSchoolId();
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("schoolId", schoolId);
		param.put("articleType", articleType);
		param.put("submitStatu", submitStatus);
		Map<String,Object> data = findArticles(param, page, pageSize);
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		result.setData(data);
		return result;
	}
	
	private Map<String,Object> findArticles(Map<String,Object> param,Integer page,Integer pageSize){
		page=page==null?FinVal.DEFAULT_PAGE:page;
		pageSize=pageSize==null?FinVal.DEFAULT_PAGESIZE:pageSize;
		Map<String,Object> data=new HashMap<String,Object>();
		List<ArticleListDto> articleListDtos= new ArrayList<ArticleListDto>();
		int countByParam = articleMapper.countByParam(param);
		Paginator paginator = new Paginator(0, pageSize);
		paginator.gotoPage(page);
		int pageCount = paginator.calcPageCount(countByParam); // 总页数
		
		param.put("startRow", paginator.getStartRow());
		param.put("pageSize", paginator.getPageSize());
		List<Article> list = articleMapper.findByParam(param);
		if(Help.isNotNull(list)){
			for(Article article:list){
				articleListDtos.add(convertArticleDto(article));
			}
		}
		data.put("dataList", articleListDtos);
		data.put("page", page);
		data.put("pageSize", pageSize);
		data.put("pageCount", pageCount);
		return 	data;
	}
	
	@Override
	public Result submitArticle(Long articleId,Integer submitStatus) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(Help.isNull(articleId)){
			log.info("articleId为空");
			result.setStatus(Status.article_not_exist_status);
			result.setInfo(Status.article_not_exist_info);
			return result;
		}
		Article article = articleMapper.selectByPrimaryKey(articleId);
		if(Help.isNull(article)){
			log.info("article不存在");
			result.setStatus(Status.article_not_exist_status);
			result.setInfo(Status.article_not_exist_info);
			return result;
		}
		User user = userService.getCurUser();
		if(Help.isNull(user)){
			log.info("获取用户信息失败");
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		
		
		Long schoolId = user.getnSchoolId();
		String schoolName = user.getcSchoolName();
		article.setnSchoolId(schoolId);
		article.setnSubmitStatus(submitStatus);
		article.setcSchoolName(schoolName);
		article.settUpdateTime(new Date());
		articleMapper.updateByPrimaryKeySelective(article);
		
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		return result;
	}

	@Override
	public Result articleTop(Long articleId) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(Help.isNull(articleId)){
			log.info("articleId为空");
			result.setStatus(Status.article_not_exist_status);
			result.setInfo(Status.article_not_exist_info);
			return result;
		}
		Article article = articleMapper.selectByPrimaryKey(articleId);
		if(Help.isNull(article)){
			log.info("article不存在");
			result.setStatus(Status.article_not_exist_status);
			result.setInfo(Status.article_not_exist_info);
			return result;
		}
		Long schoolId = article.getnSchoolId();
		articleMapper.updateTopBySchoolId(schoolId);
		article.setnTop(FinVal.DEFAULT_YES);
		articleMapper.updateByPrimaryKeySelective(article);
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		return result;
	}

	@Override
	public Result delArticle(Long articleId) {
		Result result = new Result();
		articleMapper.deleteByPrimaryKey(articleId);
		result.setInfo(Status.success_info);
		result.setStatus(Status.success_status);
		return result;
	}

	@Override
	public Result commentArticle(Long articleId, Long commentId, String content) {
		// TODO Auto-generated method stub
		Result result = new Result();
		User user = userService.getCurUser();
		if(Help.isNull(user)){
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		if(Help.isNull(articleId)){
			result.setStatus(Status.article_id_empty_status);
			result.setInfo(Status.article_id_empty_info);
			return result;
		}
		Article article = articleMapper.selectByPrimaryKey(articleId);
		if(Help.isNull(article)){
			result.setStatus(Status.article_not_exist_status);
			result.setInfo(Status.article_not_exist_info);
			return result;
		}
		Long nParentId=0l;
		Comment comment = new Comment();
		if(Help.isNotNull(commentId)){
			Comment cmt = commentMapper.selectByPrimaryKey(commentId);
			if(Help.isNull(cmt)){
				result.setStatus(Status.comment_no_exist_status);
				result.setInfo(Status.comment_no_exist_info);
				return result;
			}
			nParentId=commentId;
			comment.setnReplyUserId(cmt.getnUserId());
		}else{
			comment.setnReplyUserId(article.getnCreateId());
		}
		log.debug("插入评论记录");
		
		comment.setcCommentContent(content);
		comment.setnCommentType(FinVal.COMMENT_TYPE_ARTICLE);
		comment.setnTargetId(articleId);
		comment.setnUserId(user.getnUserId());
		comment.settCreateTime(new Date());
		comment.setnParentId(nParentId);
		comment.setnStatus(FinVal.DEFAULT_YES);
		comment.setnIsRead(FinVal.DEFAULT_NO);
		Long topCommentId = getTopCommentId(nParentId);
		comment.setnTopCommentId(topCommentId);
		commentMapper.insertSelective(comment );
		log.debug("更新文章评论次数");
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("commentType", FinVal.COMMENT_TYPE_ARTICLE);
		param.put("targetId", article.getnArticleId());
		int comments = commentMapper.countByParam(param);
		article.setnCountComment(comments);
		articleMapper.updateByPrimaryKeySelective(article);
		
		SubCommentDto subCommentDto = new SubCommentDto();
		Map<String, Object> commnetMap = new HashMap<String,Object>();
		
		if(Help.isNotNull(commentId)){
			Comment cmt = commentMapper.selectByPrimaryKey(commentId);
			if(Help.isNull(cmt)){
				result.setStatus(Status.comment_no_exist_status);
				result.setInfo(Status.comment_no_exist_info);
				return result;
			}
			User commentCreate = userMapper.selectByPrimaryKey(cmt.getnUserId());
			subCommentDto.setCommentTime(comment.gettCreateTime());
			subCommentDto.setContent(comment.getcCommentContent());
			subCommentDto.setCommentId(comment.getnCommentId());
			subCommentDto.setTargetId(comment.getnTargetId());
			subCommentDto.setHeadImg(user.getcUserPortrait());
			subCommentDto.setUserName(user.getcUserRealname()+"回复@"+commentCreate.getcUserRealname());
			commnetMap.put("commnet", subCommentDto);
		}
	
		log.debug("评论成功");
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		result.setData(commnetMap);
		return result;
	}

	@Override
	public Result subCommentList(Long articleId, Long commentId, Integer page,
			Integer pageSize) {
		// TODO Auto-generated method stub
		page=page==null?FinVal.DEFAULT_PAGE:page;
		pageSize=pageSize==null?FinVal.DEFAULT_PAGESIZE:pageSize;
		Result result = new Result();
		
		if(Help.isNull(articleId)){
			result.setStatus(Status.article_id_empty_status);
			result.setInfo(Status.article_id_empty_info);
			return result;
		}
		Article article = articleMapper.selectByPrimaryKey(articleId);
		if(Help.isNull(article)){
			result.setStatus(Status.article_not_exist_status);
			result.setInfo(Status.article_not_exist_info);
			return result;
		}
		
		if(Help.isNull(commentId)){
			result.setStatus(Status.commentid_empty_error_status);
			result.setInfo(Status.commentid_empty_error_info);
			return result;
		}
		Comment cmt = commentMapper.selectByPrimaryKey(commentId);
		if(Help.isNull(cmt)){
			result.setStatus(Status.comment_no_exist_status);
			result.setInfo(Status.comment_no_exist_info);
			return result;
		}
		 User sessionUser = userService.getCurUser();
		 if(Help.isNull(sessionUser)){
				result.setStatus(Status.oldtoken_error_status);
				result.setInfo(Status.oldtoken_error_info);
				return result;
			}
		Long id = cmt.getnUserId();
		User commentCreate = userMapper.selectByPrimaryKey(id);
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("commentType", FinVal.COMMENT_TYPE_ARTICLE);
		param.put("targetId", articleId);
		param.put("isTop", 0);
		param.put("nTopCommentId", commentId);
		int comments = commentMapper.countByParam(param);

		Paginator paginator = new Paginator(0, pageSize);
		paginator.gotoPage(page);
		int pageCount = paginator.calcPageCount(comments); // 总页数
		
		param.put("startRow", paginator.getStartRow());
		param.put("pageSize", paginator.getPageSize());
		List<Comment> list = commentMapper.findByParam(param);
		List<SubCommentDto> subCommentDtos = new ArrayList<SubCommentDto>();
		if(Help.isNotNull(list)){
			for(Comment comment:list){
				SubCommentDto subCommentDto = new SubCommentDto();
				subCommentDto.setArticleId(articleId);
				subCommentDto.setCommentId(comment.getnCommentId());
				subCommentDto.setCommentTime(comment.gettCreateTime());
				subCommentDto.setContent(comment.getcCommentContent());
				subCommentDto.setTargetId(articleId);
				Long userId = comment.getnUserId();
				User user = userMapper.selectByPrimaryKey(userId);
				Long parentId = comment.getnParentId();
				Comment parentComment = commentMapper.selectByPrimaryKey(parentId);
				if(Help.isNotNull(parentComment)){
					Long parentUserId = parentComment.getnUserId();
					User parentCommentUser = userMapper.selectByPrimaryKey(parentUserId);
					if(Help.isNotNull(user)&&Help.isNotNull(parentCommentUser)){
						subCommentDto.setHeadImg(user.getcUserPortrait());
						String realname = parentCommentUser.getcUserRealname();
						subCommentDto.setUserName(user.getcUserRealname()+"回复@"+realname+":"+parentComment.getcCommentContent());
					}
				}
				
				
				subCommentDtos.add(subCommentDto);
			}
		}
		Map<String, Object> data = new HashMap<String,Object>();
		data .put("dataList", subCommentDtos);
		data.put("page", page);
		data.put("pageSize", pageSize);
		data.put("pageCount", pageCount);
		data.put("total", comments);
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		result.setData(data);
		return result;
	}

	private Long getTopCommentId(Long commentId){
		Comment parentComment = commentMapper.selectByPrimaryKey(commentId);
		Long parentId = parentComment.getnParentId();
		if(parentId==0){
			return parentComment.getnCommentId();
		}else{
			return getTopCommentId(parentId);
		}
	}


}
