package com.xiaoyuan.common.entity;

import java.util.Date;

public class User {
    private Long nUserId;

    private String cUserAccount;

    private String cUserPassword;

    private String cUserRealname;

    private String cUserPortrait;

    private String cUserIntroduce;

    private String cMobilePhone;

    private Long nSchoolId;

    private String cSchoolName;

    private String cMajor;

    private String cGrade;

    private String cUserCode;
    
    private String cUserCodeImg;

    private Integer nSex;

    private Integer nAuthStatus;

    private String cAuthImgs;

    private String cCourseType;

    private Integer nUserType;

    private Date tCreateTime;

    private Date tUpdateTime;
    
    private Integer nCountFans;
    
    private Integer nCountAttentions;
    
    private String cAddr;

    public Long getnUserId() {
        return nUserId;
    }

    public String getcAddr() {
		return cAddr;
	}

	public void setcAddr(String cAddr) {
		this.cAddr = cAddr;
	}

	public void setnUserId(Long nUserId) {
        this.nUserId = nUserId;
    }

    public String getcUserAccount() {
        return cUserAccount;
    }

    public void setcUserAccount(String cUserAccount) {
        this.cUserAccount = cUserAccount == null ? null : cUserAccount.trim();
    }

    public String getcUserPassword() {
        return cUserPassword;
    }

    public void setcUserPassword(String cUserPassword) {
        this.cUserPassword = cUserPassword == null ? null : cUserPassword.trim();
    }

    public String getcUserRealname() {
        return cUserRealname;
    }

    public void setcUserRealname(String cUserRealname) {
        this.cUserRealname = cUserRealname == null ? null : cUserRealname.trim();
    }

    public String getcUserPortrait() {
        return cUserPortrait;
    }

    public void setcUserPortrait(String cUserPortrait) {
        this.cUserPortrait = cUserPortrait == null ? null : cUserPortrait.trim();
    }

    public String getcUserIntroduce() {
        return cUserIntroduce;
    }

    public void setcUserIntroduce(String cUserIntroduce) {
        this.cUserIntroduce = cUserIntroduce == null ? null : cUserIntroduce.trim();
    }

    public String getcMobilePhone() {
        return cMobilePhone;
    }

    public void setcMobilePhone(String cMobilePhone) {
        this.cMobilePhone = cMobilePhone == null ? null : cMobilePhone.trim();
    }

    public Long getnSchoolId() {
        return nSchoolId;
    }

    public void setnSchoolId(Long nSchoolId) {
        this.nSchoolId = nSchoolId;
    }

    public String getcSchoolName() {
        return cSchoolName;
    }

    public void setcSchoolName(String cSchoolName) {
        this.cSchoolName = cSchoolName == null ? null : cSchoolName.trim();
    }

    public String getcMajor() {
        return cMajor;
    }

    public void setcMajor(String cMajor) {
        this.cMajor = cMajor == null ? null : cMajor.trim();
    }

    public String getcGrade() {
        return cGrade;
    }

    public void setcGrade(String cGrade) {
        this.cGrade = cGrade == null ? null : cGrade.trim();
    }

    public String getcUserCode() {
        return cUserCode;
    }

    public void setcUserCode(String cUserCode) {
        this.cUserCode = cUserCode == null ? null : cUserCode.trim();
    }
    
    public String getcUserCodeImg() {
		return cUserCodeImg;
	}

	public void setcUserCodeImg(String cUserCodeImg) {
		this.cUserCodeImg = cUserCodeImg;
	}

	public Integer getnSex() {
        return nSex;
    }

    public void setnSex(Integer nSex) {
        this.nSex = nSex;
    }

    public Integer getnAuthStatus() {
        return nAuthStatus;
    }

    public void setnAuthStatus(Integer nAuthStatus) {
        this.nAuthStatus = nAuthStatus;
    }

    public String getcAuthImgs() {
        return cAuthImgs;
    }

    public void setcAuthImgs(String cAuthImgs) {
        this.cAuthImgs = cAuthImgs == null ? null : cAuthImgs.trim();
    }

    public String getcCourseType() {
        return cCourseType;
    }

    public void setcCourseType(String cCourseType) {
        this.cCourseType = cCourseType == null ? null : cCourseType.trim();
    }

    public Integer getnUserType() {
        return nUserType;
    }

    public void setnUserType(Integer nUserType) {
        this.nUserType = nUserType;
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

	public Integer getnCountFans() {
		return nCountFans;
	}

	public void setnCountFans(Integer nCountFans) {
		this.nCountFans = nCountFans;
	}

	public Integer getnCountAttentions() {
		return nCountAttentions;
	}

	public void setnCountAttentions(Integer nCountAttentions) {
		this.nCountAttentions = nCountAttentions;
	}
}