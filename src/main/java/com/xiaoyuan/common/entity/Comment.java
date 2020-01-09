package com.xiaoyuan.common.entity;

import java.util.Date;

public class Comment {
    private Long nCommentId;

    private String cCommentContent;

    private Integer nCommentType;

    private Long nTargetId;

    private Long nUserId;

    private Date tCreateTime;

    private Integer nStatus;
    
    private Long nParentId;
    
    private Long nReplyUserId;
    
    private Integer nIsRead;
    
    private Long nTopCommentId;

    public Long getnCommentId() {
        return nCommentId;
    }

    public Long getnParentId() {
		return nParentId;
	}

	public void setnParentId(Long nParentId) {
		this.nParentId = nParentId;
	}

	public void setnCommentId(Long nCommentId) {
        this.nCommentId = nCommentId;
    }

    public String getcCommentContent() {
        return cCommentContent;
    }

    public void setcCommentContent(String cCommentContent) {
        this.cCommentContent = cCommentContent == null ? null : cCommentContent.trim();
    }

    public Integer getnCommentType() {
		return nCommentType;
	}

	public void setnCommentType(Integer nCommentType) {
		this.nCommentType = nCommentType;
	}

	public Long getnTargetId() {
        return nTargetId;
    }

    public void setnTargetId(Long nTargetId) {
        this.nTargetId = nTargetId;
    }

    public Long getnUserId() {
        return nUserId;
    }

    public void setnUserId(Long nUserId) {
        this.nUserId = nUserId;
    }

    public Date gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(Date tCreateTime) {
        this.tCreateTime = tCreateTime;
    }

    public Integer getnStatus() {
        return nStatus;
    }

    public void setnStatus(Integer nStatus) {
        this.nStatus = nStatus;
    }

	public Long getnReplyUserId() {
		return nReplyUserId;
	}

	public void setnReplyUserId(Long nReplyUserId) {
		this.nReplyUserId = nReplyUserId;
	}

	public Integer getnIsRead() {
		return nIsRead;
	}

	public void setnIsRead(Integer nIsRead) {
		this.nIsRead = nIsRead;
	}

	public Long getnTopCommentId() {
		return nTopCommentId;
	}

	public void setnTopCommentId(Long nTopCommentId) {
		this.nTopCommentId = nTopCommentId;
	}
    
    
}