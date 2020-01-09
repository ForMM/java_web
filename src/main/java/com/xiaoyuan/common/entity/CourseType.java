package com.xiaoyuan.common.entity;

public class CourseType {
    private Long nCourseTypeId;

    private String cTypeName;

    private Integer nIndex;

    public Long getnCourseTypeId() {
        return nCourseTypeId;
    }

    public void setnCourseTypeId(Long nCourseTypeId) {
        this.nCourseTypeId = nCourseTypeId;
    }

    public String getcTypeName() {
        return cTypeName;
    }

    public void setcTypeName(String cTypeName) {
        this.cTypeName = cTypeName == null ? null : cTypeName.trim();
    }

    public Integer getnIndex() {
        return nIndex;
    }

    public void setnIndex(Integer nIndex) {
        this.nIndex = nIndex;
    }
}