package com.xiaoyuan.common.dao;

import com.xiaoyuan.common.entity.Tips;

public interface TipsMapper {
    int deleteByPrimaryKey(Long nTipsId);

    int insert(Tips record);

    int insertSelective(Tips record);
}