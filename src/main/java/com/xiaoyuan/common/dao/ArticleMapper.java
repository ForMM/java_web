package com.xiaoyuan.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.common.entity.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long nArticleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long nArticleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
    
    List<Article> findByParam(Map<String,Object> param);
    
    int countByParam(Map<String,Object> param);
   
    public void updateSchoolIdByUserId(@Param("userId") Long userId,@Param("schoolId") Long schoolId);
    
    int updateTopBySchoolId(@Param("schoolId") Long schoolId);
}