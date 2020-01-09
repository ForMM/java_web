package com.xiaoyuan.common.entity;

import java.util.Date;

public class UserFans {
    private Long nUserfansId;

    private Long nUserId;

    private Long nFansId;

    private Date tCreateTime;

    public Long getnUserfansId() {
        return nUserfansId;
    }

    public void setnUserfansId(Long nUserfansId) {
        this.nUserfansId = nUserfansId;
    }

    public Long getnUserId() {
        return nUserId;
    }

    public void setnUserId(Long nUserId) {
        this.nUserId = nUserId;
    }

    public Long getnFansId() {
        return nFansId;
    }

    public void setnFansId(Long nFansId) {
        this.nFansId = nFansId;
    }

    public Date gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(Date tCreateTime) {
        this.tCreateTime = tCreateTime;
    }
}