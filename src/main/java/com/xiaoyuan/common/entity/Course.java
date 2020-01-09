package com.xiaoyuan.common.entity;

import java.util.Date;

public class Course {
    private Long nCourseId;

    private String cCourseName;

    private String cCourseIntro;

    private String cCourseType;

    private Date tBeginTime;

    private Date tEndTime;

    private Long nTeacherId;

    private Long nOrgId;

    private Long nSchoolId;

    private Date tCreateTime;

    private Date tUpdateTime;

    private Integer nCountUser;

    private Integer nRecommend;
    
    private String cCourseImg;
    
    private String cPptPath;
    
    private String cPptImgs;

    public Long getnCourseId() {
        return nCourseId;
    }

    public void setnCourseId(Long nCourseId) {
        this.nCourseId = nCourseId;
    }

    public String getcCourseName() {
        return cCourseName;
    }

    public void setcCourseName(String cCourseName) {
        this.cCourseName = cCourseName == null ? null : cCourseName.trim();
    }

    public String getcCourseIntro() {
        return cCourseIntro;
    }

    public void setcCourseIntro(String cCourseIntro) {
        this.cCourseIntro = cCourseIntro == null ? null : cCourseIntro.trim();
    }

    public String getcCourseType() {
        return cCourseType;
    }

    public void setcCourseType(String cCourseType) {
        this.cCourseType = cCourseType == null ? null : cCourseType.trim();
    }

    public Date gettBeginTime() {
        return tBeginTime;
    }

    public void settBeginTime(Date tBeginTime) {
        this.tBeginTime = tBeginTime;
    }

    public Date gettEndTime() {
        return tEndTime;
    }

    public void settEndTime(Date tEndTime) {
        this.tEndTime = tEndTime;
    }

    public Long getnTeacherId() {
        return nTeacherId;
    }

    public void setnTeacherId(Long nTeacherId) {
        this.nTeacherId = nTeacherId;
    }

    public Long getnOrgId() {
        return nOrgId;
    }

    public void setnOrgId(Long nOrgId) {
        this.nOrgId = nOrgId;
    }

    public Long getnSchoolId() {
        return nSchoolId;
    }

    public void setnSchoolId(Long nSchoolId) {
        this.nSchoolId = nSchoolId;
    }

    public Date gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(Date tCreateTime) {
        this.tCreateTime = tCreateTime;
    }

    public Date gettUpdateTime() {
        return tUpdateTime;
    }

    public void settUpdateTime(Date tUpdateTime) {
        this.tUpdateTime = tUpdateTime;
    }

    public Integer getnCountUser() {
        return nCountUser;
    }

    public void setnCountUser(Integer nCountUser) {
        this.nCountUser = nCountUser;
    }

    public Integer getnRecommend() {
        return nRecommend;
    }

    public String getcCourseImg() {
		return cCourseImg;
	}

	public void setcCourseImg(String cCourseImg) {
		this.cCourseImg = cCourseImg;
	}

	public void setnRecommend(Integer nRecommend) {
        this.nRecommend = nRecommend;
    }

	public String getcPptPath() {
		return cPptPath;
	}

	public void setcPptPath(String cPptPath) {
		this.cPptPath = cPptPath;
	}

	public String getcPptImgs() {
		return cPptImgs;
	}

	public void setcPptImgs(String cPptImgs) {
		this.cPptImgs = cPptImgs;
	}
	
	
	
}