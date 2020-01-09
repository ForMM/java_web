package com.xiaoyuan.web.course.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoyuan.common.util.Result;
import com.xiaoyuan.web.course.model.CourseModel;

public interface CourseService {
	 /**
	    * 查询所有课堂类别
	    * */
	   public Result allCourseType();
	   
	   /**
	    * 个人讲师申请
	    * */
	   public Result lecturerApply(String IDNum,
			String courseTypeName, String intro, String authImg);
	   
	   /**
	    * 发布直播课程
	    * */
	   public Result pubCourse(CourseModel courseModel);
	   
	   /**
	    * 用户的开课列表
	    * */
	   public Result userCourseList(Long userId, Integer page, Integer pageSize);
	   
	   /**
	    * 课堂详情
	    * */
	   public Result courseDetail(Long courseId);
	   
	   /**
	    * 修改课程
	    * */
	   public Result modifyCourse(CourseModel courseModel) throws Exception;
	   
	   /**
	    * 上传PPT文件
	    * @param courseFile
	    * @param request
	    * @param response
	    * @param courseId
	    * @throws Exception
	    */
	   public Result uploadCourseFile(@RequestParam("courseFile") MultipartFile courseFile,HttpServletRequest request,HttpServletResponse response,Long courseId) throws Exception;
	   
}
