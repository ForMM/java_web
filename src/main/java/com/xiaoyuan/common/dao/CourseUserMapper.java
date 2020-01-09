package com.xiaoyuan.common.dao;

import java.util.List;
import java.util.Map;

import com.xiaoyuan.common.entity.CourseUser;

public interface CourseUserMapper {
    int deleteByPrimaryKey(Long nCourseUserId);

    int insert(CourseUser record);

    int insertSelective(CourseUser record);

    CourseUser selectByPrimaryKey(Long nCourseUserId);

    int updateByPrimaryKeySelective(CourseUser record);

    int updateByPrimaryKey(CourseUser record);

	List<CourseUser> findByParam(Map<String, Object> param);
	int countByParam(Map<String, Object> param);
}