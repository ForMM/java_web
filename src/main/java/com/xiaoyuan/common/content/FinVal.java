package com.xiaoyuan.common.content;
/**
 * 
 * 常量类！
 * */
public class FinVal {
	/**
	 * 默认状态1，yes
	 * */
	public static final Integer DEFAULT_YES=1;
	/**
	 * 默认状态0，no
	 * */
	public static final Integer DEFAULT_NO=0;
	
	/**
	 * accesstoken保存时间：7天
	 * */
	public static final Integer 	TOKEN_EXPIRECE 	= 60*60*24*7;//单位，秒
	
	//public static final int 	TOKEN_EXPIRECE 	= 60;//单位，秒
	/**
	 * 旧的的token key
	 * */
	public static final String 	OLD_TOKEN_MAP 	= "OLD_TOKEN_MAP";
	/**
	 * redis公共静态信息key
	 * */
	public static final String     REDIS_COMMON_KEY="COMMON";
	public static final String     COURSE_TYPE="COURSE_TYPE";
	/**
	 * 默认翻页数据
	 * 页码
	 * */
	public static final Integer DEFAULT_PAGE=1;
	
	/**
	 * 默认翻页数据
	 * 每页条数
	 * */
	public static final Integer DEFAULT_PAGESIZE=20;
	/**
	 * 学校
	 * */
	public static final String SCHOOL_KEY="SCHOOL_KEY";
	/**
	 * 验证码位数
	 * */
	public static final Integer     CHECK_CODE_NUM=5;
	
	/**
	 * 验证码有效时间，分钟
	 * */
	public static final String    CHECK_CODE_INVALID_TIME="5";
	/**
	 * 注册验证码
	 * */
	public static final String CHECK_CODE_TYPE_REGISTER="register";
	/**
	 * 修改密码验证码
	 * */
	public static final String CHECK_CODE_TYPE_RESETPWD="resetpwd";
	/**
	 * 头像图片
	 * */
	public static final Integer FILE_TYPE_USERPORAIT=1;//
	/**
	 * 直播申请图片
	 * */
	public static final Integer FILE_TYPE_AUTH=2;//
	/**
	 * 文章图片
	 * */
	public static final Integer FILE_TYPE_ARTICLE=3;//
	/**
	 * 认证状态：未提交
	 * */
	public static final Integer AUTH_STATUS_UN_SUBMIT=0;
	/**
	 * 认证状态：提交
	 * */
	public static final Integer AUTH_STATUS_SUBMIT=1;
	/**
	 * 认证状态：认证成功
	 * */
	public static final Integer AUTH_STATUS_SUCCESS=2;
	/**
	 * 认证状态：认证失败
	 * */
	public static final Integer AUTH_STATUS_FAILURE=3;
	/**
	 * 文章评论
	 * */
	public static final Integer COMMENT_TYPE_ARTICLE=1;
	/**
	 * 个人用户
	 * */
	public static final Integer USER_TYPE_PRIVATE=1;
	/**
	 * 机构用户
	 * */
	public static final Integer USER_TYPE_ORG=2;
	/**
	 * 学习用户
	 * */
	public static final Integer USER_TYPE_SCHOOL=3;
	
	/**
	 * 课程状态：1即将开始
	 * */
	public static final Integer COURSE_STATU_BEFORE=1;
	/**
	 * 课程状态：2直播中
	 * */
	public static final Integer COURSE_STATU_BGIN=2;
	/**
	 * 课程状态：2已结束
	 * */
	public static final Integer COURSE_STATU_END=3;
	
	/**
	 * session保存时间：30分钟
	 * */
	public static final Integer SESSION_EXPIRECE 	= 60*30;//单位，秒
	
	/**
	 * 上传文章图片文件名
	 * */
	public static final String ARTICLE = "article";
	/**
	 * 上传头像文件名
	 * */
	public static final String HEADERIMG = "portrait";
	/**
	 * 上传头像文件名
	 * */
	public static final String AUTH = "auth";
	/**
	 * 上传身份证图片文件名
	 * */
	public static final String USERCODEIMG = "usercodeimg";
	/**
	 * 上传课堂图片文件名
	 * */
	public static final String COURSE = "course";
	/**
	 * 文章状态：草稿
	 * */
	public static final Integer ARTICLE_STATU_DRAFT=-1;
	/**
	 * 文章状态：未提交
	 * */
	public static final Integer ARTICLE_STATU_UN_SUBMIT=0;
	/**
	 * 文章状态：已提交
	 * */
	public static final Integer ARTICLE_STATU_SUBMIT=1;
	/**
	 * 文章状态：投稿失败
	 * */
	public static final Integer ARTICLE_STATU_SUBMIT_FALURE=2;
	/**
	 * 文章状态投稿成功
	 * */
	public static final Integer ARTICLE_STATU_SUBMIT_SUCCESS=3;
	
	/**
	 * 文章类型（学校）
	 * */
	public static final Integer ARTICLE_TYPE_SCHOOL=2;
	/**
	 * 文章类型（个人）
	 * */
	public static final Integer ARTICLE_TYPE_USER=1;
	/**
	 * 文章类型（机构）
	 * */
	public static final Integer ARTICLE_TYPE_ORG=4;
	/**
	 * 文章类型（系统文章）
	 * */
	public static final Integer ARTICLE_TYPE_SYS=3;
	
	/**
	 * 上传的PPT等文档的最大页码数
	 */
	public static final int MaxNumberOfPage=25;
	
}
