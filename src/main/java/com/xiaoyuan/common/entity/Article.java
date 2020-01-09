package com.xiaoyuan.common.entity;

import java.util.Date;

public class Article {
    private Long nArticleId;

    private String cArticleTitle;

    private String cArticleTag;

    private String cArticleImgs;

    private Date tCreateTime;

    private Date tUpdateTime;

    private Long nCreateId;

    private Long nSchoolId;

    private String cSchoolName;

    private String cArticleContent;
    
    private Integer nCountComment;
    
    private Integer nSubmitStatus;
    
    private Integer nArticleType;
    
    private Integer nTop;
    
    private String cContentHtml;
    
    public Integer getnCountComment() {
		return nCountComment;
	}

	public Integer getnSubmitStatus() {
		return nSubmitStatus;
	}

	public Integer getnArticleType() {
		return nArticleType;
	}

	public void setnArticleType(Integer nArticleType) {
		this.nArticleType = nArticleType;
	}

	public void setnSubmitStatus(Integer nSubmitStatus) {
		this.nSubmitStatus = nSubmitStatus;
	}

	public void setnCountComment(Integer nCountComment) {
		this.nCountComment = nCountComment;
	}

	public Long getnArticleId() {
        return nArticleId;
    }

    public void setnArticleId(Long nArticleId) {
        this.nArticleId = nArticleId;
    }

    public String getcArticleTitle() {
        return cArticleTitle;
    }

    public void setcArticleTitle(String cArticleTitle) {
        this.cArticleTitle = cArticleTitle == null ? null : cArticleTitle.trim();
    }

    public String getcArticleTag() {
        return cArticleTag;
    }

    public void setcArticleTag(String cArticleTag) {
        this.cArticleTag = cArticleTag == null ? null : cArticleTag.trim();
    }

    public String getcArticleImgs() {
        return cArticleImgs;
    }

    public void setcArticleImgs(String cArticleImgs) {
        this.cArticleImgs = cArticleImgs == null ? null : cArticleImgs.trim();
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

    public Long getnCreateId() {
        return nCreateId;
    }

    public void setnCreateId(Long nCreateId) {
        this.nCreateId = nCreateId;
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

    public String getcArticleContent() {
        return cArticleContent;
    }

    public void setcArticleContent(String cArticleContent) {
        this.cArticleContent = cArticleContent == null ? null : cArticleContent.trim();
    }

	public Integer getnTop() {
		return nTop;
	}

	public void setnTop(Integer nTop) {
		this.nTop = nTop;
	}

	public String getcContentHtml() {
		return cContentHtml;
	}

	public void setcContentHtml(String cContentHtml) {
		this.cContentHtml = cContentHtml;
	}
    
}