package com.xiaoyuan.web.course.dto;

import java.util.Date;
import java.util.List;

public class CourseDetailDto {
  private Long courseId;
  private String courseImg;
  private String courseName;
  private String courseType;
  private String courseIntro;
  private Long teacherId;
  private String teacherName;
  private String teacherMajor;
  private String teacherSchoolName;
  private Integer isAttention;
  private Date begin;
  private Date end;
  private int countUser;
  private List<CourseUsersDto> courseUsers;
  private String teacherIntro;
  private Long orgId;
  private String orgName;
  private String orgIntro;
  private Integer isSignup;
  private Integer isTeacher;
  private List<String> imgs;
public Long getCourseId() {
	return courseId;
}
public void setCourseId(Long courseId) {
	this.courseId = courseId;
}
public String getCourseIntro() {
	return courseIntro;
}
public void setCourseIntro(String courseIntro) {
	this.courseIntro = courseIntro;
}
public Long getOrgId() {
	return orgId;
}
public void setOrgId(Long orgId) {
	this.orgId = orgId;
}
public String getCourseImg() {
	return courseImg;
}
public void setCourseImg(String courseImg) {
	this.courseImg = courseImg;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public Long getTeacherId() {
	return teacherId;
}
public void setTeacherId(Long teacherId) {
	this.teacherId = teacherId;
}
public String getTeacherName() {
	return teacherName;
}
public void setTeacherName(String teacherName) {
	this.teacherName = teacherName;
}
public String getTeacherMajor() {
	return teacherMajor;
}
public void setTeacherMajor(String teacherMajor) {
	this.teacherMajor = teacherMajor;
}
public String getTeacherSchoolName() {
	return teacherSchoolName;
}
public void setTeacherSchoolName(String teacherSchoolName) {
	this.teacherSchoolName = teacherSchoolName;
}
public Date getBegin() {
	return begin;
}
public void setBegin(Date begin) {
	this.begin = begin;
}
public Date getEnd() {
	return end;
}
public void setEnd(Date end) {
	this.end = end;
}
public int getCountUser() {
	return countUser;
}
public void setCountUser(int countUser) {
	this.countUser = countUser;
}
public List<CourseUsersDto> getCourseUsers() {
	return courseUsers;
}
public void setCourseUsers(List<CourseUsersDto> courseUsers) {
	this.courseUsers = courseUsers;
}
public String getTeacherIntro() {
	return teacherIntro;
}
public void setTeacherIntro(String teacherIntro) {
	this.teacherIntro = teacherIntro;
}
public String getOrgName() {
	return orgName;
}
public void setOrgName(String orgName) {
	this.orgName = orgName;
}
public String getOrgIntro() {
	return orgIntro;
}
public void setOrgIntro(String orgIntro) {
	this.orgIntro = orgIntro;
}
public Integer getIsAttention() {
	return isAttention;
}
public void setIsAttention(Integer isAttention) {
	this.isAttention = isAttention;
}
public Integer getIsSignup() {
	return isSignup;
}
public void setIsSignup(Integer isSignup) {
	this.isSignup = isSignup;
}
public Integer getIsTeacher() {
	return isTeacher;
}
public void setIsTeacher(Integer isTeacher) {
	this.isTeacher = isTeacher;
}
public List<String> getImgs() {
	return imgs;
}
public void setImgs(List<String> imgs) {
	this.imgs = imgs;
}
public String getCourseType() {
	return courseType;
}
public void setCourseType(String courseType) {
	this.courseType = courseType;
}

}
