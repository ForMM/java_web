package com.xiaoyuan.web.course.controller;

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
import com.xiaoyuan.common.entity.User;
import com.xiaoyuan.common.exception.UploadException;
import com.xiaoyuan.common.log.BKLogger;
import com.xiaoyuan.common.log.LogTool;
import com.xiaoyuan.common.util.Help;
import com.xiaoyuan.common.util.Result;
import com.xiaoyuan.web.article.model.ArticleModel;
import com.xiaoyuan.web.article.service.FileService;
import com.xiaoyuan.web.base.BaseController;
import com.xiaoyuan.web.course.model.CourseModel;
import com.xiaoyuan.web.course.service.CourseService;
import com.xiaoyuan.web.user.dto.UserInfoDto;
import com.xiaoyuan.web.user.service.UserService;

@Controller
@RequestMapping("/course")
public class CourseController extends BaseController{
	BKLogger logger = BKLogger.getLogger(CourseController.class);
	private LogTool log = LogTool.getInstance(CourseController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private FileService fileService;
	
	 @RequestMapping("/liveApplicationPage.do")	 
	 public String accountPageController(HttpServletRequest request,Model model){
		 UserInfoDto currentUser = userService.getCurrentUser();		 
		 model.addAttribute("currentUser", currentUser);
		 return "website/course/liveApplication";
	 }
	 @RequestMapping("/ownlivePage.do")	 
	 public String ownlivePageController(HttpServletRequest request,Model model){
		 UserInfoDto currentUser = userService.getCurrentUser();		 
		 model.addAttribute("currentUser", currentUser);
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 return "website/course/ownlive";	 
	 }
	 
	 @RequestMapping("/courseType")
	 @ResponseBody
	 public void courseType(HttpServletRequest request,Model model){
		 Result result = null;
		 try{
			 result= courseService.allCourseType();
		 }catch(Exception ex){
			 log.error("查询课堂类别失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("查询课堂类别输出列表", result));
	 }
	 @RequestMapping("/lecturerApply")
	 @ResponseBody
	 public void lecturerApplyController(HttpServletRequest request,Model model,String IDNum,String courseTypeName,String intro,String authImg){
		 Result result = null;
		 try{
			 result= courseService.lecturerApply(IDNum,courseTypeName,intro,authImg);
		 }catch(Exception ex){
			 log.error("个人讲师申请失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("个人讲师申请输出列表", result));
	 }
	 @RequestMapping(value = "/uploadAuthImg.do",method=RequestMethod.POST)	
	 @ResponseBody
	 public void uploadAuthImgController(@RequestParam("authImg") MultipartFile authImg,HttpServletRequest request, Model model){
		 Result result=null;
		 try{ 
			 result = fileService.uploadOSSFile(authImg,FinVal.AUTH);		
		 }catch(Exception ex){
			 log.error("上传个人直播认证图片失败！");
			 log.error("上传个人直播认证图片失败！", ex);
			 result=new Result(); 
		 }
		 getPrintWriter().print(logger.infobk("上传个人直播认证图片", result));
	 }
	 	 
	 @RequestMapping("/addCoursePage")	 
	 public String addCoursePageController(HttpServletRequest request,Model model){
		 UserInfoDto currentUser = userService.getCurrentUser();
		 
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 
		 
		 if(currentUser.getAuthStatus() != FinVal.AUTH_STATUS_SUCCESS){
			 model.addAttribute("currentUser", currentUser);
			 return "website/course/liveApplication";	
		 }
		 model.addAttribute("userType", currentUser.getUserType());	
		 return "website/course/addCourse";	 
	 }
	
	 @RequestMapping("/addCourse")
	 @ResponseBody
	 public void addCourseController(HttpServletRequest request,Model model,CourseModel courseModel){
		 Result result = null;
		 try{
			 result = courseService.pubCourse(courseModel);
		 }catch(Exception ex){
			 log.error("发布课堂失败", ex);
			 result = new Result ();
		 }
		 getPrintWriter().print(logger.infobk("发布课堂", result));		 
	 }
	 
	 @RequestMapping(value = "/uploadCourseImg.do",method=RequestMethod.POST)	
	 @ResponseBody
	 public void uploadCourseImgController(@RequestParam("contentImg") MultipartFile contentImg,HttpServletRequest request, Model model){
		 Result result=null;
		 try{
			 result = fileService.uploadOSSFile(contentImg,FinVal.COURSE);		
		 }catch(Exception ex){
			 log.error("上传课堂图片失败！");
			 log.error("上传课堂图片失败！", ex);
			 result=new Result(); 
		 }
		 getPrintWriter().print(logger.infobk("上传课堂图片", result));
	 }
	 
	 @RequestMapping("/courseManagePage")	 
	 public String courseManagePageController(HttpServletRequest request,Model model){
		 model.addAttribute("userId", userService.getCurrentUser().getUserId());
		 
		 UserInfoDto currentUser = userService.getCurrentUser();
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 
		 return "website/course/courseManage";	 
	 }
	 
	 @RequestMapping("/courseListPage")	 
	 public String courseListPageController(HttpServletRequest request,Model model){
		 model.addAttribute("userId", userService.getCurrentUser().getUserId());
		 
		 UserInfoDto currentUser = userService.getCurrentUser();
		 String redirectPage = redirectPage(currentUser);
		 if(Help.isNotNull(redirectPage)){
			 return redirectPage;
		 }
		 
		 return "website/course/courseList";	 
	 }
	 
	 	/**
		 * 用户的直播列表
		 * 用户开的课
		 * */
		 @RequestMapping("/userCourseList")
		 @ResponseBody
		 public void userCourseListController(HttpServletRequest request,Model model,String accesstoken,Long userId,Integer page,Integer pageSize){
			 Result result = null;
			 try{
				 result= courseService.userCourseList(userId, page,pageSize);
			 }catch(Exception ex){
				 log.error("用户直播列表失败", ex);
				 result = new Result ();
			 }
			 getPrintWriter().print(logger.infobk("用户直播列表输出列表", result));
		 }
		 /**
			 * 获取课堂详情
			 * 
			 * */
			 @RequestMapping("/courseDetail")
			 @ResponseBody
			 public void courseDetailController(HttpServletRequest request,Model model,Long courseId){
				 Result result = null;
				 try{
					 result= courseService.courseDetail(courseId);
				 }catch(Exception ex){
					 log.error("获取课堂详情失败", ex);
					 result = new Result ();
				 }
				 getPrintWriter().print(logger.infobk("获取课堂详情输出列表", result));
			 }
			 @RequestMapping("/updateCoursePage")	 
			 public String updateCoursePageController(HttpServletRequest request,Model model,Long courseId){
				 model.addAttribute("courseId", courseId);
				 
				 UserInfoDto currentUser = userService.getCurrentUser();
				 String redirectPage = redirectPage(currentUser);
				 if(Help.isNotNull(redirectPage)){
					 return redirectPage;
				 }
				 
				 return "website/course/updateCourse";	 
			 }
			 
			 /**
				 * 修改课程
				 * 
				 * */
				 @RequestMapping("/modifyCourse")
				 @ResponseBody
				 public void modifyCourseController(HttpServletRequest request,Model model,CourseModel courseModel){
					 Result result = null;
					 try{
						 result= courseService.modifyCourse(courseModel);
					 }catch(Exception ex){
						 log.error("修改课堂详情失败", ex);
						 result = new Result ();
					 }
					 getPrintWriter().print(logger.infobk("修改课堂详情输出列表", result));
				 }
				 
				 /**
				  * 上传课堂文件
				  * @param courseFile
				  * @param request
				  * @param response
				  * @param courseId
				  * @return
				  */
				 @RequestMapping("uploadCourseFile")
				public void uploadCourseFileController(@RequestParam("courseFile") MultipartFile courseFile,HttpServletRequest request,HttpServletResponse response,Long courseId){		 		
					 Result result = new Result();	
					 try {
						 result = courseService.uploadCourseFile(courseFile,request, response,courseId);							
						} catch(UploadException ex){
							request.setAttribute("result", "error");
							String errorMsg= ex.getMessage();
							request.setAttribute("reason", errorMsg);
							log.error("上传PPT失败...", ex);				
							ex.printStackTrace();	
						}catch (Exception e) {		
							request.setAttribute("result", "error");
							log.error("上传PPT失败...", e);				
							e.printStackTrace();	
						}	
						getPrintWriter().print(logger.infobk("上传PPT", result));
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
