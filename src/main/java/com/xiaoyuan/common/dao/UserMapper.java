package com.xiaoyuan.common.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.common.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long nUserId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long nUserId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User login(@Param("account")String account,@Param("password")String password);
    
    User findByAccount(@Param("account")String account);
}