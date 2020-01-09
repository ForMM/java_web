package com.xiaoyuan.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.xiaoyuan.common.dao.SchoolMapper;
import com.xiaoyuan.common.entity.School;
import com.xiaoyuan.common.log.BKLogger;
import com.xiaoyuan.common.log.LogTool;
import com.xiaoyuan.common.util.Paginator;
import com.xiaoyuan.common.util.Result;
import com.xiaoyuan.web.user.service.UserService;

public class TestUserService {
	BKLogger logger = BKLogger.getLogger(TestUserService.class);
	LogTool log=LogTool.getInstance(TestUserService.class);
	@Test
	public void login(){
		UserService userService = AutoRunClass.getBean(UserService.class);
		Result login = userService.login("18320792425", "password");
		logger.infobk("登录输出列表", login);
	}
	/*@Test
	public void initSchool(){
		SchoolMapper schoolMapper = AutoRunClass.getBean(SchoolMapper.class);
		Map<String, Object> param=new HashMap<String,Object>();
		int countByParam = schoolMapper.countByParam(param);
		Paginator paginator = new Paginator(0, 100);
		
		int pageCount = paginator.calcPageCount(countByParam); // 总页数
		logger.debug("=========共"+pageCount+"页==========");
		for(int i=1;i<=pageCount;i++){
			logger.debug("=======更新第"+i+"页==========");
			paginator.gotoPage(i);
			param.put("startRow", paginator.getStartRow());
			param.put("pageSize", paginator.getPageSize());
			
			List<School> list = schoolMapper.findByParam(param);
			for(School school:list){
				String schoolName = school.getcSchoolName();
				String pinyin = PinYinUtil.getPingYin(schoolName);
				String first = PinYinUtil.getFirstSpell(schoolName);
				school.setcPinyinName(pinyin);
				school.setcPinyinShort(first);
				schoolMapper.updateByPrimaryKeySelective(school);
			}
			logger.debug("=======第"+i+"页更新完毕==========");
		}
	}*/
	
/*	@Test
	public void school(){
		UserService userService = AutoRunClass.getBean(UserService.class);
		long begin = System.currentTimeMillis();
		try{
		Result result = userService.school("znlykjdx");
		long end = System.currentTimeMillis();
		logger.infobk("学校输出列表", result);
		logger.debug("耗时"+(end-begin)+"(毫秒)");
		}catch(Exception ex){
			log.error("出错", ex);
		}
	}*/
}
