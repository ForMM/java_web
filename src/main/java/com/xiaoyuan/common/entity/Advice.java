package com.xiaoyuan.common.entity;

import java.util.Date;

public class Advice {
    private Long nAdviceId;

    private String cAdviceContent;

    private Date tCreateTime;

    private Long nCreateId;

    public Long getnAdviceId() {
        return nAdviceId;
    }

    public void setnAdviceId(Long nAdviceId) {
        this.nAdviceId = nAdviceId;
    }

    public String getcAdviceContent() {
        return cAdviceContent;
    }

    public void setcAdviceContent(String cAdviceContent) {
        this.cAdviceContent = cAdviceContent == null ? null : cAdviceContent.trim();
    }

    public Date gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(Date tCreateTime) {
        this.tCreateTime = tCreateTime;
    }

    public Long getnCreateId() {
        return nCreateId;
    }

    public void setnCreateId(Long nCreateId) {
        this.nCreateId = nCreateId;
    }
}