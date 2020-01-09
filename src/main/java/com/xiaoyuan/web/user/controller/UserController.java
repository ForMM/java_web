package com.xiaoyuan.web.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoyuan.common.content.FinVal;
import com.xiaoyuan.common.log.BKLogger;
import com.xiaoyuan.common.log.LogTool;
import com.xiaoyuan.common.util.Help;
import com.xiaoyuan.common.util.Result;
import com.xiaoyuan.web.article.service.FileService;
import com.xiaoyuan.web.base.BaseController;
import com.xiaoyuan.web.user.dto.UserInfoDto;
import com.xiaoyuan.web.user.model.OrgUserModel;
import com.xiaoyuan.web.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
  
	BKLogger logger = BKLogger.getLogger(UserController.class);
	private LogTool log = LogTool.getInstance(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private FileService fileService;
	
	 @RequestMapping("/login")
	 @ResponseBody
	 public void login(HttpServletRequest request,Model model,String account,String password){
		 Result result = null;
		 try{
			 result=userService.login(account, password);
		 }catch(Exception ex){
			 log.error("登录列表失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("登录输出列表", result));
		 
	 }
	 @RequestMapping("/sendCheckCode")
	 @ResponseBody
	 public void sendCheckCode(HttpServletRequest request,Model model,String mobilePhone,String type){
		 Result result = null;
		 try{
			 result=userService.sendCheckCode(mobilePhone, type);
		 }catch(Exception ex){
			 log.error("发送验证码失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("发送验证码输出列表", result));
	 }
	 @RequestMapping("/regist")
	 @ResponseBody
	 public void regist(HttpServletRequest request,Model model,String mobilePhone,String checkCode,String password,String userName,Long schoolId,String major,String grade){
		 Result result = null;
		 try{
			 result=userService.register(mobilePhone, checkCode, password, userName, schoolId, major, grade);
		 }catch(Exception ex){
			 log.error("注册个人用户失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("注册输出列表", result));
	 }
	 
	 @RequestMapping("/registSchool")
	 @ResponseBody
	 public void registSchool(HttpServletRequest request,Model model,String mobilePhone,String checkCode,String password,Long schoolId){
		 Result result = null;
		 try{
			 result=userService.registSchool(mobilePhone, checkCode, password, schoolId);
		 }catch(Exception ex){
			 log.error("注册学校用户失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("注册学校用户", result));
	 }
	 
	 @RequestMapping("/registOrg")
	 @ResponseBody
	 public void registOrg(HttpServletRequest request,Model model,String mobilePhone,String checkCode,String password,Long schoolId){
		 Result result = null;
		 try{
			 result=userService.registOrg(mobilePhone, checkCode, password);
		 }catch(Exception ex){
			 log.error("注册机构用户失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("注册机构用户", result));
	 }
	 
	 @RequestMapping("/schoolList")
	 @ResponseBody
	 public void schoolList(HttpServletRequest request,Model model,String schoolName){
		 Result result = null;
		 try{
			 log.debug("学校："+schoolName);
			 result= userService.school(schoolName);
		 }catch(Exception ex){
			 log.error("获取学校列表失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("学校输出列表", result));
	 }
	 @RequestMapping("/advice")
	 @ResponseBody
	 public void adviceController(HttpServletRequest request,Model model,String content){
		 Result result = null;
		 try{
			 result= userService.advice(content);
		 }catch(Exception ex){
			 log.error("意见反馈失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("意见反馈输出列表", result));
	 }
	 
	 @RequestMapping("/loginOut")
	 @ResponseBody
	 public void loginOut(HttpServletRequest request,Model model){
		 Result result = null;
		 try{
			 result=userService.loginOut(request);
		 }catch(Exception ex){
			 log.error("用户退出失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("用户退出", result));		 
	 }
	 @RequestMapping("/resetPwd")
	 @ResponseBody
	 public void resetPwd(HttpServletRequest request,Model model,String mobilePhone,String checkCode,String newPwd){
		 Result result = null;
		 try{
			 result= userService.resetPwd(mobilePhone,checkCode,newPwd);
		 }catch(Exception ex){
			 log.error("修改密码失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("修改密码输出列表", result));
	 }
	 @RequestMapping("/getUserInfo")
	 @ResponseBody
	 public void getUserInfo(HttpServletRequest request,Model model,String mobilePhone){
		 Result result = null;
		 try{
			 result= userService.getUserInfo();
		 }catch(Exception ex){
			 log.error("获取当前登录用户失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("获取当前登录用户", result));
	 }
	 @RequestMapping("/accountPage")	 
	 public String accountPageController(HttpServletRequest request,Model model){
		 UserInfoDto currentUser = userService.getCurrentUser();		 
		 model.addAttribute("currentUser", currentUser);
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 
		 if(currentUser.getUserType() == FinVal.USER_TYPE_ORG){
			 return "website/user/orgAccount";	 
		 }
		 
		 return "website/user/account";	 
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
	 @RequestMapping("/teacherPage")	 
	 public String teacherPageController(HttpServletRequest request,Model model){
		 return "website/user/orgTeacher";	 
	 }
	 @RequestMapping(value = "/uploadHeaderImg.do",method=RequestMethod.POST)	
	 @ResponseBody
	 public void uploadHeaderImgController(@RequestParam("headerImg") MultipartFile contentImg,HttpServletRequest request, Model model){
		 Result result=null;
		 try{
			 result = fileService.uploadOSSFile(contentImg,FinVal.HEADERIMG);		
		 }catch(Exception ex){
			 log.error("上传头像图片失败！");
			 log.error("上传头像图片失败！", ex);
			 result=new Result(); 
		 }
		 getPrintWriter().print(logger.infobk("上传头像图片", result));
	 }
	 @RequestMapping("/modifyUserInfo")
	 @ResponseBody
	 public void modifyUserInfoController(HttpServletRequest request,Model model,String userName,Long schoolId,String major,String grade,String potrait){
		 Result result = null;
		 try{
			 result= userService.modifyUserInfo(userName,schoolId,major,grade,potrait);
		 }catch(Exception ex){
			 log.error("修改用户信息失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("修改用户信息输出列表", result));
	 }
	 @RequestMapping("/orgSignupPage")	 
	 public String orgSignupPageController(HttpServletRequest request,Model model){		
		 UserInfoDto currentUser = userService.getCurrentUser();		 
		 model.addAttribute("currentUser", currentUser);
		 
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 return "website/user/orgSignup";	 
	 }
	 @RequestMapping("/modifyUserOrg")
	 @ResponseBody
	 public void modifyUserOrgController(HttpServletRequest request,Model model,OrgUserModel orgUserModel,boolean orgLisenceFlag){
		 Result result = null;
		 try{
			 result=userService.modifyUserOrg(orgUserModel,orgLisenceFlag);
		 }catch(Exception ex){
			 log.error("提交机构认证资料失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("提交机构认证资料", result));
	 }
	 @RequestMapping("/addUserForOrg")
	 @ResponseBody
	 public void addUserForOrgController(HttpServletRequest request,Model model,String teacherName,String phoneNum,String teacherIntro){
		 Result result = null;
		 try{
			 result=userService.addUserForOrg(teacherName,phoneNum,teacherIntro);
		 }catch(Exception ex){
			 log.error("添加机构老师失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("添加机构老师", result));
	 }
	 @RequestMapping("/getOrgUsers")
	 @ResponseBody
	 public void getOrgUsersController(HttpServletRequest request,Model model){
		 Result result = null;
		 try{
			 result=userService.getOrgUsers();
		 }catch(Exception ex){
			 log.error("获取机构用户失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("获取机构用户", result));
	 }
	 @RequestMapping("/userCenterPage")	 
	 public String userCenterPageController(HttpServletRequest request,Model model,String selectTab){
		 UserInfoDto currentUser = userService.getCurrentUser();		 
		 model.addAttribute("currentUser", currentUser);		 
		 return "website/index";	 
	 }
	 @RequestMapping("/delOrgUser")
	 @ResponseBody
	 public void delOrgUserController(HttpServletRequest request,Model model,OrgUserModel orgUserModel,Long userOrgId){
		 Result result = null;
		 try{
			 result=userService.delOrgUser(userOrgId);
		 }catch(Exception ex){
			 log.error("删除机构老师失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("删除机构老师", result));
	 }
	 
	 @RequestMapping("/teacherManagePage")	 
	 public String teacherManagePageController(HttpServletRequest request,Model model){
		 UserInfoDto currentUser = userService.getCurrentUser();
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 return "website/user/teacherManage";	 
	 }
	 @RequestMapping("/addTeacherPage")	 
	 public String addTeacherPageController(HttpServletRequest request,Model model){
		 return "website/user/addTeacher";	 
	 }
	 
	 @RequestMapping("/teacherListPage")	 
	 public String teacherListPageController(HttpServletRequest request,Model model){
		 return "website/user/teacherList";	 
	 }
	 
	 @RequestMapping(value = "/uploadUserCodeImg.do",method=RequestMethod.POST)	
	 @ResponseBody
	 public void uploadAuthImgController(@RequestParam("userCodeImg") MultipartFile userCodeImg,HttpServletRequest request, Model model){
		 Result result=null;
		 try{ 
			 result = fileService.uploadOSSFile(userCodeImg,FinVal.USERCODEIMG);		
		 }catch(Exception ex){
			 log.error("上传身份证图片失败！");
			 log.error("上传身份证图片失败！", ex);
			 result=new Result(); 
		 }
		 getPrintWriter().print(logger.infobk("上传身份证图片", result));
	 }
	 
	 @RequestMapping("/schoolSignupPage")	 
	 public String schoolSignupPageController(HttpServletRequest request,Model model){		
		 UserInfoDto currentUser = userService.getCurrentUser();		 
		 model.addAttribute("currentUser", currentUser);
		 
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 return "website/user/schoolSignup";	 
	 }
	 @RequestMapping("/modifyUserSchool")
	 @ResponseBody
	 public void modifyUserSchoolController(HttpServletRequest request,Model model,OrgUserModel orgUserModel,boolean orgLisenceFlag){
		 Result result = null;
		 try{
			 result=userService.modifyUserSchool(orgUserModel,orgLisenceFlag);
		 }catch(Exception ex){
			 log.error("提交机构认证资料失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("提交机构认证资料", result));
	 }
}
