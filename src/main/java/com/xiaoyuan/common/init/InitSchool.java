package com.xiaoyuan.common.init;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.xiaoyuan.common.content.FinVal;
import com.xiaoyuan.common.content.SchoolContent;
import com.xiaoyuan.common.dao.SchoolMapper;
import com.xiaoyuan.common.entity.School;
import com.xiaoyuan.common.log.LogTool;
import com.xiaoyuan.common.redis.RedisBean;
import com.xiaoyuan.common.util.Paginator;
import com.xiaoyuan.web.user.service.UserService;
public class InitSchool  implements ApplicationListener<ContextRefreshedEvent> {
	LogTool log = LogTool.getInstance(InitSchool.class);
	@Autowired
	private UserService userService;
	@Autowired
	private RedisBean redisBean;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		if(event.getApplicationContext().getParent() == null){
			Map<String, String> schoolMap=null;
			if(!redisBean.exists(FinVal.SCHOOL_KEY)){
				schoolMap = userService.initSchool();
			}else{
				schoolMap=redisBean.hgetAll(FinVal.SCHOOL_KEY);
			}
			SchoolContent.schoolMap=schoolMap;
		}
	}

	

}

