package com.xiaoyuan.common.entity;

import java.util.Date;

public class ArticleFav {
    private Long nFavId;

    private Long nArticleId;

    private Long nUserId;

    private Date tCreateTime;

    public Long getnFavId() {
        return nFavId;
    }

    public void setnFavId(Long nFavId) {
        this.nFavId = nFavId;
    }

    public Long getnArticleId() {
        return nArticleId;
    }

    public void setnArticleId(Long nArticleId) {
        this.nArticleId = nArticleId;
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
}