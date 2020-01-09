package com.xiaoyuan.common.entity;

import java.util.Date;

public class Message {
    private Long nMessageId;

    private Long nMessageFrome;

    private Long nMessageTo;

    private String cMessageContent;

    private Integer nMessageType;

    private Date tCreateTime;

    public Long getnMessageId() {
        return nMessageId;
    }

    public void setnMessageId(Long nMessageId) {
        this.nMessageId = nMessageId;
    }

    public Long getnMessageFrome() {
        return nMessageFrome;
    }

    public void setnMessageFrome(Long nMessageFrome) {
        this.nMessageFrome = nMessageFrome;
    }

    public Long getnMessageTo() {
        return nMessageTo;
    }

    public void setnMessageTo(Long nMessageTo) {
        this.nMessageTo = nMessageTo;
    }

    public String getcMessageContent() {
        return cMessageContent;
    }

    public void setcMessageContent(String cMessageContent) {
        this.cMessageContent = cMessageContent == null ? null : cMessageContent.trim();
    }

    public Integer getnMessageType() {
		return nMessageType;
	}

	public void setnMessageType(Integer nMessageType) {
		this.nMessageType = nMessageType;
	}

	public Date gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(Date tCreateTime) {
        this.tCreateTime = tCreateTime;
    }
}