package com.xiaoyuan.common.entity;

import java.util.Date;

public class Tag {
    private Long nTagId;

    private String cTagName;

    private Integer nTagType;

    private Long nCreateId;

    private Date tCreateTime;

    public Long getnTagId() {
        return nTagId;
    }

    public void setnTagId(Long nTagId) {
        this.nTagId = nTagId;
    }

    public String getcTagName() {
        return cTagName;
    }

    public void setcTagName(String cTagName) {
        this.cTagName = cTagName == null ? null : cTagName.trim();
    }

    public Integer getnTagType() {
        return nTagType;
    }

    public void setnTagType(Integer nTagType) {
        this.nTagType = nTagType;
    }

    public Long getnCreateId() {
        return nCreateId;
    }

    public void setnCreateId(Long nCreateId) {
        this.nCreateId = nCreateId;
    }

    public Date gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(Date tCreateTime) {
        this.tCreateTime = tCreateTime;
    }
}