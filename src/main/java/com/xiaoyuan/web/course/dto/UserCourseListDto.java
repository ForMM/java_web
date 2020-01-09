package com.xiaoyuan.web.course.dto;

import java.util.Date;
import java.util.Map;

public class UserCourseListDto {

	  private Long courseId;
	  private String img;
	  private String courseName;
	  private String courseIntro;
	  private int count;
	  private int statu;
	  private String teacherName;
	  private Date createTime;
	  private Date beginTime;
	  private Map<String,Object> pptImgs;//layer显示相册
	public Long getCourseId() {
		return courseId;
	}
	public Map<String, Object> getPptImgs() {
		return pptImgs;
	}
	public void setPptImgs(Map<String, Object> pptImgs) {
		this.pptImgs = pptImgs;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public String getCourseIntro() {
		return courseIntro;
	}
	public void setCourseIntro(String courseIntro) {
		this.courseIntro = courseIntro;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	  
	  
	  

}
