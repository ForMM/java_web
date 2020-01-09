package com.xiaoyuan.common.dao;

import java.util.List;
import java.util.Map;

import com.xiaoyuan.common.entity.UserFans;

public interface UserFansMapper {
    int deleteByPrimaryKey(Long nUserfansId);

    int insert(UserFans record);

    int insertSelective(UserFans record);

    UserFans selectByPrimaryKey(Long nUserfansId);

    int updateByPrimaryKeySelective(UserFans record);

    int updateByPrimaryKey(UserFans record);
    
    List<UserFans> findByParam(Map<String, Object> param);
	int countByParam(Map<String, Object> param);
}