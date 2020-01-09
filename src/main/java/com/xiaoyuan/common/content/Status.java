package com.xiaoyuan.common.content;

/**
 * 
 * 全局状态码表
 * 
 * @auther tangqing
 * 
 */
public class Status {  
	public static final int 	success_status 	= 10000;
	public static final String 	success_info 	= "操作成功";
	
	public static final int 	appkey_error_status 	= 10001;
	public static final String 	appkey_error_info 	= "缺少appkey";
	
	public static final int 	appsecurity_error_status 	= 10002;
	public static final String 	appsecurity_error_info 	= "非法的key值";
	
	public static final int 	sign_error_status 	= 10003;
	public static final String 	sign_error_info 	= "签名验证失败";
	
	public static final int 	login_error_status 	= 10004;
	public static final String 	login_error_info 	= "用户名或密码错误！";
	
	public static final int 	account_error_status 	= 10005;
	public static final String 	account_error_info 	= "请输入手机号";
	
	public static final int 	pwd_empty_status 	= 10006;
	public static final String 	pwd_empty_info 	= "请输入密码";
	
	public static final int     oldtoken_empty_status = 10007;
	public static final String 	oldtoken_empty_info 	= "oldtoken不能为空";
	
	public static final int     reqid_empty_status = 10008;
	public static final String 	reqid_empty_info 	= "reqid不能为空";
	
	public static final int 	reqid_fmt_error_status 	= 10009;
	public static final String 	reqid_fmt_error_info 	= "reqid格式错误";
	
	public static final int     oldtoken_error_status = 10010;
	public static final String 	oldtoken_error_info 	= "请重新登录";
	
	public static final int 	send_checkcode_error_status 	= 10011;
	public static final String 	send_checkcode_error_info 	= "验证码发送失败";
	
	public static final int 	phone_fmt_error_status 	= 10012;
	public static final String 	phone_fmt_error_info 	= "手机号码格式错误";
	
	public static final int 	checkcode_empty_error_status 	= 10013;
	public static final String 	checkcode_empty_error_info 	= "请输入验证码";
	
	public static final int 	checkcode_error_status 	= 10014;
	public static final String 	checkcode_error_info 	= "验证码错误";
	
	public static final int     phone_empty_error_status = 10015;
	public static final String 	phone_empty_error_info 	= "请输入手机号";
	
	public static final int     phone_exist_error_status = 10016;
	public static final String 	phone_exist_error_info 	= "该手机号已被注册";
	
	public static final int 	checkcode_type_error_status 	= 10017;
	public static final String 	checkcode_type_error_info 	= "验证码类型错误";
	
	public static final int     phone_not_exist_error_status = 10018;
	public static final String 	phone_not_exist_error_info 	= "该手机号未注册";
	
	public static final int     token_empty_error_status = 10019;
	public static final String 	token_empty_error_info 	= "token不能为空";
	
	public static final int     token_error_status = 10020;
	public static final String 	token_error_info 	= "无效的token";
	
	public static final int     username_empty_status=10021;
	public static final String 	username_empty_info 	= "请输入姓名";
	
	public static final int     user_not_exist_error_status = 10022;
	public static final String 	user_not_exist_error_info 	= "用户不存在";
	
	public static final int		school_empty_status=10023;
	public static final String  school_empty_info="请选择学校";
	
	public static final int		school_not_exist_status=10024;
	public static final String  school_not_exist_info="学校不存在";
	
	public static final int		major_empty_status=100245;
	public static final String  major_empty_info="请输入专业";
	
	public static final int		grade_empty_status=10026;
	public static final String  grade_empty_info="请选择年级";
	
	public static final int		file_empty_status=10027;
	public static final String  file_empty_info="请选择文件";
	
	public static final int 	img_type_error_status=10028;
	public static final String  img_type_error_info="图片类型错误";
	
	public static final int 	upload_error_status=10029;
	public static final String 	upload_error_info="文件上传失败";
	
	public static final int		file_type_error_status=10030;
	public static final String  file_type_error_info="文件类型错误";
	
	public static final int		advice_empty_status=10031;
	public static final String  advice_empty_info="请输入意见反馈内容";
	
	public static final int 	id_error_status=10032; 
	public static final String 	id_error_info="身份证格式错误"; 
	
	public static final int 	course_type_empty_status=10033; 
	public static final String 	course_type_empty_info="请输入开课类别"; 
	
	public static final int 	intro_empty_status=10034; 
	public static final String 	intro_empty_info="请输入个人简介";
	
	public static final int		authImg_empty_status=10035; 
	public static final String 	authImg_empty_info="请上次认证图片";
	
	public static final int		article_content_empty_status=10036; 
	public static final String 	article_content_empty_info="请输入文章内容";
	
	public static final int		article_id_empty_status=10037; 
	public static final String 	article_id_empty_info="文章ID为空";
	
	public static final int		article_not_exist_status=10038; 
	public static final String 	article_not_exist_info="文章不存在";
	
	public static final int		auth_deny_status=10039; 
	public static final String 	auth_deny_info="未通过直播认证";
	
	public static final int		courseName_empty_status=10040; 
	public static final String 	courseName_empty_info="请输入课程名称";
	
	public static final int		courseIntro_empty_status=10041; 
	public static final String 	courseIntro_empty_info="请输入课程介绍";
	
	public static final int		course_beginDate_empty_status=10042; 
	public static final String 	course_beginDate_empty_info="请选择开课时间";
	
	public static final int		course_endDate_empty_status=10043; 
	public static final String 	course_endDate_empty_info="请选择结束时间";
	
	public static final int		course_beginDate_error_status=10044; 
	public static final String 	course_beginDate_error_info="开课时间必须大于当前时间";
	
	public static final int		course_endDate_error_status=10045; 
	public static final String 	course_endDate_error_info="课程结束时间不能小于开始时间";
	
	public static final int		date_format_error_status=10046; 
	public static final String 	date_format_error_info="时间格式错误";
	
	public static final int		course_id_empty_status=10047; 
	public static final String 	course_id_empty_info="请选择报名课程";
	
	public static final int		course_not_exist_status=10048; 
	public static final String 	course_not_exist_info="课程不存在！";
	
	public static final int     course_entry_error_status=10049;
	public static final String  course_entry_error_info="不能报名自己的课堂";
	
	public static final int     course_entry_again_error_status=10050;
	public static final String  course_entry_again_error_info="已报名该课堂，无需重复报名";
	
	public static final int     attention_again_error_status=10051;
	public static final String  attention_again_error_info="已关注，无需重复关注";
	
	public static final int     article_submit_error_status=10052;
	public static final String  article_submit_error_info="无权投稿该文章";
	
	public static final int     attention_error_status=10053;
	public static final String  attention_error_info="无需关注自己";
	
	public static final int     logo_error_status=10054;
	public static final String  logo_error_info="请上传机构logo";
	
	public static final int     orgAuth_error_status=10055;
	public static final String  orgAuth_error_info="请上传营业执照";
	
	public static final int     orgIntro_error_status=10056;
	public static final String  orgIntro_error_info="请填写机构简介";
	
	public static final int     orgAddr_error_status=10057;
	public static final String  orgAddr_error_info="请填写机构地址";
	
	public static final int     auth_error_status=10058;
	public static final String  auth_error_info="你已提交认证资料，请勿重复提交";
	
	public static final int		permission_error_status=10059; 
	public static final String 	permission_error_info="您无权操作";
	
	public static final int		orguser_error_status=10060; 
	public static final String 	orguser_error_info="无法添加该用户";
	
	public static final int		orguserauth_error_status=10061; 
	public static final String 	orguserauth_error_info="该手机号还没有认证成功，请认证成功后再添加！";
	
	public static final int 	orguseronly_error_status=10062;
	public static final String 	orguseronly_error_info="该手机号用户已经加入其他机构！";
	
	public static final int 	orguseradd_error_status=10063;
	public static final String 	orguseradd_error_info="您还没有认证通过，暂时不能添加机构用户！";
	
	public static final int 	orguserdel_error_status=10064;
	public static final String 	orguserdel_error_info="删除的用户不存在！";
	
	public static final int 	orguserteach_error_status=10065;
	public static final String 	orguserteach_error_info="机构老师的手机号未填！";
	
	public static final int 	orguserteach_not_error_status=10066;
	public static final String 	orguserteach_not_error_info="添加的老师可能还没有加入到机构老师中！";
	
	public static final int     orguser_not_exist_error_status = 10067;
	public static final String 	orguser_not_exist_error_info 	= "添加的老师可能还没有注册！";
	
	public static final int     orguser_not_match_error_status = 10068;
	public static final String 	orguser_not_match_error_info 	= "手机号码和姓名不匹配!";
	
	public static final int     orguser_not_exist_teacher_error_status = 10070;
	public static final String 	orguser_not_exist_teacher_error_info 	= "讲师的名字还没有填写！";
	
	public static final int     orguser_not_exist_teacherintro_error_status = 10071;
	public static final String 	orguser_not_exist_teacherintro_error_info 	= "讲师的介绍还没有填写！";
	
	public static final int     comment_empty_status = 10072;
	public static final String 	comment_empty_info 	= "评论内容不能为空！";
	
	public static final int comment_no_exist_status=10073;
	public static final String comment_no_exist_info="评论不存在！";
	
	public static final int commentid_empty_error_status=10074;
	public static final String commentid_empty_error_info="评论ID不能为空";
	
	public static final int userrealname_empty_error_status=10075;
	public static final String userrealname_empty_error_info="运营人姓名不能为空";
	public static final int usercode_empty_error_status=10076;
	public static final String usercode_empty_error_info="身份证号码不能为空";
	public static final int usercodeimg_empty_error_status=10077;
	public static final String usercodeimg_empty_error_info="身份证图片没有上传";
}  

