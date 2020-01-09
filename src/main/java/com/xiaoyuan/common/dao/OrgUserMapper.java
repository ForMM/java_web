package com.xiaoyuan.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.common.entity.OrgUser;

public interface OrgUserMapper {
    int deleteByPrimaryKey(Long nOrgUserId);

    int insert(OrgUser record);

    int insertSelective(OrgUser record);

    OrgUser selectByPrimaryKey(Long nOrgUserId);

    int updateByPrimaryKeySelective(OrgUser record);

    int updateByPrimaryKey(OrgUser record);

	List<OrgUser> findByUserId(@Param("userId")Long userId);
	
	List<OrgUser> findByOrgId(@Param("orgId")Long orgId);
}