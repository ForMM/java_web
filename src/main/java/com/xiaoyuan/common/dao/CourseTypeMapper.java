package com.xiaoyuan.common.dao;

import java.util.List;

import com.xiaoyuan.common.entity.CourseType;

public interface CourseTypeMapper {
    int deleteByPrimaryKey(Long nCourseTypeId);

    int insert(CourseType record);

    int insertSelective(CourseType record);

    CourseType selectByPrimaryKey(Long nCourseTypeId);

    int updateByPrimaryKeySelective(CourseType record);

    int updateByPrimaryKey(CourseType record);

	List<CourseType> findAll();
}