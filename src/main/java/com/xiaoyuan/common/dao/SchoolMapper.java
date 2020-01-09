package com.xiaoyuan.common.dao;

import java.util.List;
import java.util.Map;

import com.xiaoyuan.common.entity.School;

public interface SchoolMapper {
    int deleteByPrimaryKey(Long nSchoolId);

    int insert(School record);

    int insertSelective(School record);

    School selectByPrimaryKey(Long nSchoolId);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);
    
    List<School> findByParam(Map<String,Object> param);
    int countByParam(Map<String,Object> param);
}