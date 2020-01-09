package com.xiaoyuan.common.entity;

public class School {
    private Long nSchoolId;

    private String cSchoolName;

    private String cPinyinName;

    private String cPinyinShort;

    private String areaId;

    private String areaName;

    private Integer nOrder;

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

    public String getcPinyinName() {
        return cPinyinName;
    }

    public void setcPinyinName(String cPinyinName) {
        this.cPinyinName = cPinyinName == null ? null : cPinyinName.trim();
    }

    public String getcPinyinShort() {
        return cPinyinShort;
    }

    public void setcPinyinShort(String cPinyinShort) {
        this.cPinyinShort = cPinyinShort == null ? null : cPinyinShort.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Integer getnOrder() {
        return nOrder;
    }

    public void setnOrder(Integer nOrder) {
        this.nOrder = nOrder;
    }
}