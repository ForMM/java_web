package com.xiaoyuan.common.entity;

import java.util.Date;

public class CourseUser {
    private Long nCourseUserId;

    private Long nUserId;

    private Long nCourseId;

    private Date tCreateTime;

    public Long getnCourseUserId() {
        return nCourseUserId;
    }

    public void setnCourseUserId(Long nCourseUserId) {
        this.nCourseUserId = nCourseUserId;
    }

    public Long getnUserId() {
        return nUserId;
    }

    public void setnUserId(Long nUserId) {
        this.nUserId = nUserId;
    }

    public Long getnCourseId() {
        return nCourseId;
    }

    public void setnCourseId(Long nCourseId) {
        this.nCourseId = nCourseId;
    }

    public Date gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(Date tCreateTime) {
        this.tCreateTime = tCreateTime;
    }
}