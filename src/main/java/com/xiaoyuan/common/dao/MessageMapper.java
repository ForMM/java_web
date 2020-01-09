package com.xiaoyuan.common.dao;

import com.xiaoyuan.common.entity.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(Long nMessageId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long nMessageId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}