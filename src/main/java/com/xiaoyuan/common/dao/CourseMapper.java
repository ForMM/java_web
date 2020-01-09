package com.xiaoyuan.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.common.entity.Course;

public interface CourseMapper {
    int deleteByPrimaryKey(Long nCourseId);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Long nCourseId);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
    
    public void updateSchoolIdByUserId(@Param("userId") Long userId,@Param("schoolId") Long schoolId);

	List<Course> findRecommends(Map<String, Object> param);
	
	int countRecommends(Map<String, Object> param);
	
	
	List<Course> findByParam(Map<String, Object> param);
	
	int countByParam(Map<String, Object> param);
}