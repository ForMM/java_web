package com.xiaoyuan.common.dao;

import java.util.List;

import com.xiaoyuan.common.entity.Tag;

public interface TagMapper {
    int deleteByPrimaryKey(Long nTagId);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Long nTagId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
    
    List<Tag> articleTags();
}