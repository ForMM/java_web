package com.xiaoyuan.common.dao;

import java.util.List;
import java.util.Map;

import com.xiaoyuan.common.entity.Comment;

public interface CommentMapper {
    int deleteByPrimaryKey(Long nCommentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long nCommentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

	List<Comment> findByParam(Map<String, Object> param);
	int countByParam(Map<String, Object> param);
	
	List<Comment> myComments(Map<String, Object> param);
	int myCommentsCount(Map<String, Object> param);
}