package com.xiaoyuan.web.user.service.impl;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.EMutil;
import com.sun.star.uno.RuntimeException;
import com.xiaoyuan.common.content.FinVal;
import com.xiaoyuan.common.content.GeetestConfig;
import com.xiaoyuan.common.content.GeetestLib;
import com.xiaoyuan.common.content.SchoolContent;
import com.xiaoyuan.common.content.SendSMS;
import com.xiaoyuan.common.content.Status;
import com.xiaoyuan.common.content.SysContent;
import com.xiaoyuan.common.dao.AdviceMapper;
import com.xiaoyuan.common.dao.ArticleMapper;
import com.xiaoyuan.common.dao.CourseMapper;
import com.xiaoyuan.common.dao.OrgUserMapper;
import com.xiaoyuan.common.dao.SchoolMapper;
import com.xiaoyuan.common.dao.UserMapper;
import com.xiaoyuan.common.entity.Advice;
import com.xiaoyuan.common.entity.OrgUser;
import com.xiaoyuan.common.entity.School;
import com.xiaoyuan.common.entity.User;
import com.xiaoyuan.common.log.LogTool;
import com.xiaoyuan.common.redis.RedisBean;
import com.xiaoyuan.common.util.GsonUtil;
import com.xiaoyuan.common.util.Help;
import com.xiaoyuan.common.util.MUtil;
import com.xiaoyuan.common.util.Paginator;
import com.xiaoyuan.common.util.Result;
import com.xiaoyuan.web.user.dto.SchoolDto;
import com.xiaoyuan.web.user.dto.UserInfoDto;
import com.xiaoyuan.web.user.model.OrgUserModel;
import com.xiaoyuan.web.user.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	private LogTool log = LogTool.getInstance(UserServiceImpl.class);
	@Autowired
	private RedisBean redisBean;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private SchoolMapper schoolMapper;
	@Autowired
	private AdviceMapper adviceMapper;
	
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private OrgUserMapper orgUserMapper;
	
	private static Map<String,String> map=new HashMap<String,String>();
	@Override
	public Result login(String account, String password) {
		// TODO Auto-generated method stub
		Result result = new Result();
		log.debug("开始登陆。。。");
		
		log.debug("开始验证参数");
		if(MUtil.isEmpty(account)){
			result.setStatus(Status.account_error_status);
			result.setInfo(Status.account_error_info);
			return result;
		}
		if(!MUtil.toJudgeInput(account, 2)){
			log.info("手机号格式错误");
			result.setStatus(Status.phone_fmt_error_status);
			result.setInfo(Status.phone_fmt_error_info);
			return result;
		}
		if(MUtil.isEmpty(password)){
			result.setStatus(Status.pwd_empty_status);
			result.setInfo(Status.pwd_empty_info);
			return result;
		}
		User user = userMapper.login(account,MUtil.md5(password));
		if(user!=null){
			log.debug("登陆成功。");
			UserInfoDto userInfoDto=new UserInfoDto();
			userInfoDto.setUserId(user.getnUserId());
			userInfoDto.setUserAccount(user.getcUserAccount());
			userInfoDto.setUserName(user.getcUserRealname());
			userInfoDto.setHeadImg(user.getcUserPortrait());
			userInfoDto.setSchoolId(user.getnSchoolId());
			userInfoDto.setSchoolName(user.getcSchoolName());
			userInfoDto.setGrade(user.getcGrade());
			userInfoDto.setMajor(user.getcMajor());
			userInfoDto.setAuthImgs(user.getcAuthImgs());
			userInfoDto.setAuthStatus(user.getnAuthStatus());
			userInfoDto.setCourseType(user.getcCourseType());
			userInfoDto.setUserIntro(user.getcUserIntroduce());
			userInfoDto.setUserCode(user.getcUserCode());
			userInfoDto.setUserAddr(user.getcAddr());
			userInfoDto.setUserPhone(user.getcMobilePhone());
			userInfoDto.setUserType(user.getnUserType());
			result.setData(userInfoDto);
			result.setStatus(Status.success_status);
			result.setInfo(Status.success_info);
			String sessionId = SysContent.getSession().getId();
			redisBean.set(sessionId, FinVal.SESSION_EXPIRECE, GsonUtil.objectToJson(user));
		}else{
			log.debug("登陆失败。。。");
			result.setStatus(Status.login_error_status);
			result.setInfo(Status.login_error_info);
		}
		
		return result;
	}

	private static String fomartPhone(String reqid){
    	StringBuffer sbf=new StringBuffer();
    	if(map.isEmpty()){
	    	map.put("1", "9");
	    	map.put("2", "8");
	    	map.put("3", "7");
	    	map.put("4", "6");
	    	map.put("5", "5");
	    	map.put("6", "4");
	    	map.put("7", "3");
	    	map.put("8", "2");
	    	map.put("9", "1");
	    	map.put("0", "0");
    	}
    	for(int i=0;i<reqid.length();i++){
    		sbf.append(map.get(reqid.substring(i, i+1)));
    	}
    	
    	return sbf.toString();
    	
    }
	
	@Override
	public Result school(String schoolName) {
		// TODO Auto-generated method stub
		Result result = new Result();
		List<SchoolDto> schools = new LinkedList<SchoolDto>();
		Map<String, String> schoolMap = SchoolContent.getInstance().schoolMap;
		if(Help.isNull(schoolMap)){
			if(redisBean.exists(FinVal.SCHOOL_KEY)){
				schoolMap = redisBean.hgetAll(FinVal.SCHOOL_KEY);
			}else{
				schoolMap=initSchool();
			}
			SchoolContent.getInstance().schoolMap=schoolMap;
		}
		for (Map.Entry entry : schoolMap.entrySet()) {
		 	String key = entry.getKey().toString();
			String schoolDetail = (String) entry.getValue();
    		String[] schoolArr = schoolDetail.split("#");
		    if(Help.isNotNull(schoolName)){
		    	schoolName=schoolName.toLowerCase();//转小写字母
		    	if(key.indexOf(schoolName)!=-1){
		    		SchoolDto school = new SchoolDto();
		    		school.setSchoolId(Integer.parseInt(schoolArr[0]));
		    		school.setSchoolName(schoolArr[1]);
		    		schools.add(school);
		    	}
		    }else{
		    	SchoolDto school = new SchoolDto();
	    		school.setSchoolId(Integer.parseInt(schoolArr[0]));
	    		school.setSchoolName(schoolArr[1]);
	    		schools.add(school);
		    }
	    }
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("dataList", schools);
		result.setData(data);
		return result;
	}
	@Override
	public Map<String,String> initSchool() {
		// TODO Auto-generated method stub
		Map<String,String> schoolMap = new LinkedHashMap<String, String>();
				log.debug("初始化学校信息。。。。");
				Map<String, Object> param=new HashMap<String,Object>();
				int countByParam = schoolMapper.countByParam(param);
				Paginator paginator = new Paginator(0, 200);
				int pageCount = paginator.calcPageCount(countByParam); // 总页数
				
				for(int i=1;i<=pageCount;i++){
					paginator.gotoPage(i);
					param.put("startRow", paginator.getStartRow());
					param.put("pageSize", paginator.getPageSize());
					List<School> list = schoolMapper.findByParam(param);
					for(School school:list){
						String schoolName = school.getcSchoolName();
						String pinyinName = school.getcPinyinName();
						String pinyinShort = school.getcPinyinShort();
						Long schoolId = school.getnSchoolId();
						Integer order = school.getnOrder();
						schoolMap.put(schoolName+"_"+pinyinName+"_"+pinyinShort, schoolId+"#"+schoolName+"#"+order);
					}
					
				}
				redisBean.hset(FinVal.SCHOOL_KEY, schoolMap);
				log.debug("初始化学校信息完成....");
				return schoolMap;
	}
	@Override
	public Result sendCheckCode(String mobilePhone,String type) {
		// TODO Auto-generated method stub
		Result result = new Result();
		log.info("开始参数===");	
		if(MUtil.isEmpty(mobilePhone)){
			log.info("手机号未填！！");
			result.setStatus(Status.phone_empty_error_status);
			result.setInfo(Status.phone_empty_error_info);
			return result;
		}
		if(type==null||(!type.equals(FinVal.CHECK_CODE_TYPE_REGISTER)&&!type.equals(FinVal.CHECK_CODE_TYPE_RESETPWD))){
			log.debug("验证码类型错误！！");
			result.setStatus(Status.checkcode_type_error_status);
			result.setInfo(Status.checkcode_type_error_info);
			return result;
		}
		if(!MUtil.toJudgeInput(mobilePhone, 2)){
			log.info("手机号格式错误");
			result.setStatus(Status.phone_fmt_error_status);
			result.setInfo(Status.phone_fmt_error_info);
			return result;
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userAccount", mobilePhone);
		User user = userMapper.findByAccount(mobilePhone);
		if(type.equals(FinVal.CHECK_CODE_TYPE_REGISTER)&&user!=null){//注册验证码拦截已注册用户
			log.info("该手机号已被注册");
			result.setStatus(Status.phone_exist_error_status);
			result.setInfo(Status.phone_exist_error_info);
			return result;
		}
		if(type.equals(FinVal.CHECK_CODE_TYPE_RESETPWD)&&user==null){//修改密码拦截未注册用户
			log.info("该手机号未注册");
			result.setStatus(Status.phone_not_exist_error_status);
			result.setInfo(Status.phone_not_exist_error_info);
			return result;
		}
	////极客验证


			GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key());
				
			String challenge = SysContent.getRequest().getParameter(GeetestLib.fn_geetest_challenge);
			String validate = SysContent.getRequest().getParameter(GeetestLib.fn_geetest_validate);
			String seccode = SysContent.getRequest().getParameter(GeetestLib.fn_geetest_seccode);
			
			//从session中获取gt-server状态
			int gt_server_status_code = (Integer) SysContent.getRequest().getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
			
			//从session中获取userid
			String userid = (String)SysContent.getRequest().getSession().getAttribute("userid");
			
			int gtResult = 0;

			if (gt_server_status_code == 1) {
				//gt-server正常，向gt-server进行二次验证
					
				gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, userid);
				System.out.println(gtResult);
			} else {
				// gt-server非正常情况下，进行failback模式验证
					
				System.out.println("failback:use your own server captcha validate");
				gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
				System.out.println(gtResult);
			}


			if (gtResult == 1) {
			
				String checkCode = MUtil.random(FinVal.CHECK_CODE_NUM);
				Map<String, Object> map = SendSMS.sendSMS(mobilePhone, checkCode, FinVal.CHECK_CODE_INVALID_TIME);
				if("000000".equals(map.get("statusCode"))){
					redisBean.set(mobilePhone+"_"+type,Integer.parseInt(FinVal.CHECK_CODE_INVALID_TIME)*60, checkCode);
					result.setStatus(Status.success_status);
					result.setInfo(Status.success_info);
				}else{
					log.error("发送验证码失败"+mobilePhone);
					log.error(("错误码=" + map.get("statusCode") +" 错误信息= "+map.get("statusMsg")));
					result.setStatus(Status.send_checkcode_error_status);
					result.setInfo(Status.send_checkcode_error_info);
				}
			}else{
				log.error("验证失败");
			}
		return result;
	}
	@Override
	public Result register(String mobilePhone, String checkCode,
			String password, String userName, Long schoolId, String major,
			String grade) {
		// TODO Auto-generated method stub
		Result result = new Result();
		log.info("开始参数===");	
		if(MUtil.isEmpty(mobilePhone)){
			log.info("手机号未填！！");
			result.setStatus(Status.phone_empty_error_status);
			result.setInfo(Status.phone_empty_error_info);
			return result;
		}
		if(!MUtil.toJudgeInput(mobilePhone, 2)){
			log.info("手机号格式错误");
			result.setStatus(Status.phone_fmt_error_status);
			result.setInfo(Status.phone_fmt_error_info);
			return result;
		}
		if(Help.isNull(checkCode)){
			log.info("验证码为空！");
			result.setStatus(Status.checkcode_empty_error_status);
			result.setInfo(Status.checkcode_empty_error_info);
			return result;
		}
		if(Help.isNull(password)){
			log.info("密码为空！");
			result.setStatus(Status.pwd_empty_status);
			result.setInfo(Status.pwd_empty_info);
			return result;
		}
		if(Help.isNull(userName)){
			log.info("用户姓名为空！");
			result.setStatus(Status.username_empty_status);
			result.setInfo(Status.username_empty_info);
			return result;
		}
		if(Help.isNull(schoolId)){
			log.info("未选择学校");
			result.setStatus(Status.school_empty_status);
			result.setInfo(Status.school_empty_info);
			return result;
		}
		School school = schoolMapper.selectByPrimaryKey(schoolId);
		if(Help.isNull(school)){
			log.info("学校不存在");
			result.setStatus(Status.school_not_exist_status);
			result.setInfo(Status.school_not_exist_info);
			return result;
		}
		if(Help.isNull(major)){
			log.info("专业未填");
			result.setStatus(Status.major_empty_status);
			result.setInfo(Status.major_empty_info);
			return result;
		}
		if(Help.isNull(grade)){
			log.info("年级未填");
			result.setStatus(Status.grade_empty_status);
			result.setInfo(Status.grade_empty_info);
			return result;
		}
		String redisCheckCode = redisBean.get(mobilePhone+"_"+FinVal.CHECK_CODE_TYPE_REGISTER);
		if(!checkCode.equals(redisCheckCode)){
			log.info("验证码错误！");
			log.info("正确验证码为："+redisCheckCode);
			log.info("验证码错误为："+checkCode);
			result.setStatus(Status.checkcode_error_status);
			result.setInfo(Status.checkcode_error_info);
			return result;
		}
		User user = userMapper.findByAccount(mobilePhone);
		if(Help.isNotNull(user)){
			log.info("手机号码"+mobilePhone+"已被注册");
			result.setStatus(Status.phone_exist_error_status);
			result.setInfo(Status.phone_exist_error_info);
			return result;
		}
		User register = new User();
		Date date = new Date();
		register.setcUserAccount(mobilePhone);
		register.setcGrade(grade);
		register.setcMajor(major);
		register.setcMobilePhone(mobilePhone);
		register.setcSchoolName(school.getcSchoolName());
		register.setnSchoolId(school.getnSchoolId());
		register.setcUserPassword(MUtil.md5(password));
		register.settCreateTime(date);
		register.settUpdateTime(date);
		register.setcUserRealname(userName);
		register.setnUserType(FinVal.USER_TYPE_PRIVATE);
		userMapper.insertSelective(register);
		
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		
		redisBean.delete(mobilePhone+"_"+FinVal.CHECK_CODE_TYPE_REGISTER);
//		boolean createEMUser = EMutil.createEMUser(mobilePhone, MUtil.md5(password), userName);
//		if(!createEMUser){
//			log.error("创建环信账户失败！！！");
//			throw new RuntimeException();
//		}
		
		return login(mobilePhone, password);
	}
	@Override
	public Result advice(String content) {
		Result result = new Result();
		log.debug("意见反馈开始");
		if(Help.isNull(content)){
			log.info("意见反馈内容为空");
			result.setStatus(Status.advice_empty_status);
			result.setInfo(Status.advice_empty_info);
			return result;
		}
		Advice advice = new Advice();
		advice.setcAdviceContent(content);
		User sessionUser = getSessionUser();
		advice.setnCreateId(sessionUser.getnUserId());
		advice.settCreateTime(new Date());
		adviceMapper.insertSelective(advice);
		
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		return result;
	}
	private User getSessionUser(){
		User user= null;
		String userJSON = SysContent.getRequest().getParameter("accesstoken");
		if(Help.isNotNull(userJSON)){
			 user = (User) GsonUtil.jsonToObject(userJSON, User.class);
		}
		return user;
	}

	@Override
	public Result loginOut(HttpServletRequest request) {
		// TODO Auto-generated method stub
		log.debug("用户退出");
		Result result = new Result();
		
		String sessionId = SysContent.getSession().getId();
		redisBean.delete(sessionId);
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		return result;
	}

	@Override
	public Result resetPwd(String mobilePhone, String checkCode, String newPwd) {

		// TODO Auto-generated method stub
		Result result = new Result();
		log.info("修改密码，开始参数===");	
		if(MUtil.isEmpty(mobilePhone)){
			log.info("手机号未填！！");
			result.setStatus(Status.phone_empty_error_status);
			result.setInfo(Status.phone_empty_error_info);
			return result;
		}
		if(!MUtil.toJudgeInput(mobilePhone, 2)){
			log.info("手机号格式错误");
			result.setStatus(Status.phone_fmt_error_status);
			result.setInfo(Status.phone_fmt_error_info);
			return result;
		}
		String redisCheckCode = redisBean.get(mobilePhone+"_"+FinVal.CHECK_CODE_TYPE_RESETPWD);
		if(!checkCode.equals(redisCheckCode)){
			log.info("验证码错误！");
			log.info("正确验证码为："+redisCheckCode);
			log.info("验证码错误为："+checkCode);
			result.setStatus(Status.checkcode_error_status);
			result.setInfo(Status.checkcode_error_info);
			return result;
		}
		if(Help.isNull(newPwd)){
			log.info("密码未填！");
			result.setStatus(Status.pwd_empty_status);
			result.setInfo(Status.pwd_empty_info);
			return result;
		}
		User user = userMapper.findByAccount(mobilePhone);
		if(Help.isNull(user)){
			log.info("手机号码"+mobilePhone+"未注册");
			result.setStatus(Status.phone_not_exist_error_status);
			result.setInfo(Status.phone_not_exist_error_info);
			return result;
		}
		user.setcUserPassword(MUtil.md5(newPwd));
		user.settUpdateTime(new Date());
		userMapper.updateByPrimaryKeySelective(user);
		
//		boolean resetPwd = EMutil.resetPwd(mobilePhone, MUtil.md5(newPwd));
//		if(!resetPwd){
//			throw new RuntimeException("重置环信密码失败！");
//		}
		
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		
		return result;
	
	}

	@Override
	public Result getUserInfo() {
		// TODO Auto-generated method stub
		String sessionId = SysContent.getSession().getId();
		String userStr = redisBean.get(sessionId);
		User user = null;
		UserInfoDto userInfoDto = null;
		if(Help.isNotNull(userStr)){
			userInfoDto=new UserInfoDto();
			user = (User) GsonUtil.jsonToObject(userStr, User.class);
			userInfoDto.setUserId(user.getnUserId());
			userInfoDto.setUserAccount(user.getcUserAccount());
			userInfoDto.setUserName(user.getcUserRealname());
			userInfoDto.setHeadImg(user.getcUserPortrait());
			userInfoDto.setSchoolId(user.getnSchoolId());
			userInfoDto.setSchoolName(user.getcSchoolName());
			userInfoDto.setGrade(user.getcGrade());
			userInfoDto.setMajor(user.getcMajor());
			userInfoDto.setAuthImgs(user.getcAuthImgs());
			userInfoDto.setAuthStatus(user.getnAuthStatus());
			userInfoDto.setCourseType(user.getcCourseType());
			userInfoDto.setUserIntro(user.getcUserIntroduce());
			userInfoDto.setUserCode(user.getcUserCode());
			userInfoDto.setUserAddr(user.getcAddr());
			userInfoDto.setUserPhone(user.getcMobilePhone());
			userInfoDto.setUserType(user.getnUserType());
		}
				
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("user", userInfoDto);
		Result result  = new Result();
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		result.setData(data);
		return result;
	}

	@Override
	public UserInfoDto getCurrentUser() {
		// TODO Auto-generated method stub
		String sessionId = SysContent.getSession().getId();
		String userStr = redisBean.get(sessionId);
		User user = null;	
		UserInfoDto userInfoDto = null;
		if(Help.isNotNull(userStr)){
			user = (User) GsonUtil.jsonToObject(userStr, User.class);
			
			if(Help.isNotNull(userStr)){
				userInfoDto=new UserInfoDto();
				user = (User) GsonUtil.jsonToObject(userStr, User.class);
				userInfoDto.setUserId(user.getnUserId());
				userInfoDto.setUserAccount(user.getcUserAccount());
				userInfoDto.setUserName(user.getcUserRealname());
				userInfoDto.setHeadImg(user.getcUserPortrait());
				userInfoDto.setSchoolId(user.getnSchoolId());
				userInfoDto.setSchoolName(user.getcSchoolName());
				userInfoDto.setGrade(user.getcGrade());
				userInfoDto.setMajor(user.getcMajor());
				userInfoDto.setAuthImgs(user.getcAuthImgs());
				userInfoDto.setAuthStatus(user.getnAuthStatus());
				userInfoDto.setCourseType(user.getcCourseType());
				userInfoDto.setUserIntro(user.getcUserIntroduce());
				userInfoDto.setUserCode(user.getcUserCode());
				userInfoDto.setUserAddr(user.getcAddr());
				userInfoDto.setUserPhone(user.getcMobilePhone());
				userInfoDto.setUserType(user.getnUserType());
				userInfoDto.setUserRealName(user.getcUserRealname());
				userInfoDto.setUserCodeImg(user.getcUserCodeImg());
			}
		}
		return userInfoDto;
	}
	
	@Override
	public User getCurUser() {
		String sessionId = SysContent.getSession().getId();
		String userStr = redisBean.get(sessionId);
		User user = null;	
		
		if(Help.isNotNull(userStr)){
			user = (User) GsonUtil.jsonToObject(userStr, User.class);
		}
		return user;
	}

	@Override
	public Result modifyUserInfo(String userName,
			Long schoolId, String major, String grade, String potrait) {
		// TODO Auto-generated method stub
		Result result = new Result();
		
		String sessionId = SysContent.getSession().getId();
		String userStr = redisBean.get(sessionId);
		User user  = (User) GsonUtil.jsonToObject(userStr, User.class);
		if(Help.isNull(user)){
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		Long userId = user.getnUserId();
		if(Help.isNull(userName)){
			log.info("用户姓名为空！");
			result.setStatus(Status.username_empty_status);
			result.setInfo(Status.username_empty_info);
			return result;
		}
		if(Help.isNull(schoolId)){
			log.info("未选择学校");
			result.setStatus(Status.school_empty_status);
			result.setInfo(Status.school_empty_info);
			return result;
		}
		School school = schoolMapper.selectByPrimaryKey(schoolId);
		if(Help.isNull(school)){
			log.info("学校不存在");
			result.setStatus(Status.school_not_exist_status);
			result.setInfo(Status.school_not_exist_info);
			return result;
		}
		if(Help.isNull(major)){
			log.info("专业未填");
			result.setStatus(Status.major_empty_status);
			result.setInfo(Status.major_empty_info);
			return result;
		}
		if(Help.isNull(grade)){
			log.info("年级未填");
			result.setStatus(Status.grade_empty_status);
			result.setInfo(Status.grade_empty_info);
			return result;
		}		
		
		Long userSchoolId = user.getnSchoolId();
		if(!userSchoolId.equals(schoolId)){
			log.debug("用户改变了学校，改变相关文章，课堂的学校ID");
			courseMapper.updateSchoolIdByUserId(userId, schoolId);
			articleMapper.updateSchoolIdByUserId(userId, schoolId);
		}
		
		user.setcUserRealname(userName);
		user.setnSchoolId(schoolId);
		user.setcSchoolName(school.getcSchoolName());
		user.setcMajor(major);
		user.setcGrade(grade);
		user.setcUserPortrait(potrait);
		userMapper.updateByPrimaryKeySelective(user);
       		
		redisBean.set(sessionId, FinVal.SESSION_EXPIRECE, GsonUtil.objectToJson(user));
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		return result;
	}

	@Override
	public Result modifyUserOrg(OrgUserModel orgUserModel,boolean orgLisenceFlag) {
		// TODO Auto-generated method stub
		Result result = new Result();
		
		String sessionId = SysContent.getSession().getId();
		String userStr = redisBean.get(sessionId);
		User user  = (User) GsonUtil.jsonToObject(userStr, User.class);
		if(Help.isNull(user)){
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		
		if(Help.isNull(orgUserModel.getUserName())){
			log.info("机构名称为空！");
			result.setStatus(Status.username_empty_status);
			result.setInfo(Status.username_empty_info);
			return result;
		}
		else{
			user.setcUserRealname(orgUserModel.getUserName());	
		}
		
		if(Help.isNull(orgUserModel.getHeadImg())){
			log.info("机构logo未上传!");
			result.setStatus(Status.logo_error_status);
			result.setInfo(Status.logo_error_info);
			return result;
		}else{
			user.setcUserPortrait(orgUserModel.getHeadImg());
		}
		
		if(Help.isNull(orgUserModel.getAuthImgs()) && orgLisenceFlag){
			log.info("机构营业执照未上传!");
			result.setStatus(Status.orgAuth_error_status);
			result.setInfo(Status.orgAuth_error_info);
			return result;
		}else{
			user.setnAuthStatus(FinVal.AUTH_STATUS_SUBMIT);
			user.setcAuthImgs(orgUserModel.getAuthImgs());
		}
		
		if(Help.isNull(orgUserModel.getUserIntro())){
			log.info("机构简介未填");
			result.setStatus(Status.orgIntro_error_status);
			result.setInfo(Status.orgIntro_error_info);
			return result;
		}else{
			user.setcUserIntroduce(orgUserModel.getUserIntro());
		}
		
		if(Help.isNull(orgUserModel.getUserRealName())){
			log.info("运营人姓名未填");
			result.setStatus(Status.userrealname_empty_error_status);
			result.setInfo(Status.userrealname_empty_error_info);
			return result;
		}else{
			user.setcUserRealname(orgUserModel.getUserRealName());
		}
		if(Help.isNull(orgUserModel.getUserCode())){
			log.info("身份证号码未填");
			result.setStatus(Status.usercode_empty_error_status);
			result.setInfo(Status.usercode_empty_error_info);
			return result;
		}else{
			user.setcUserCode(orgUserModel.getUserCode());
		}
		if(Help.isNull(orgUserModel.getUserCodeImg()) && orgLisenceFlag){
			log.info("身份证图片未上传!");
			result.setStatus(Status.usercodeimg_empty_error_status);
			result.setInfo(Status.usercodeimg_empty_error_info);
			return result;
		}else{
			
			user.setcUserCodeImg(orgUserModel.getUserCodeImg());
		}
		
		
		if(Help.isNull(orgUserModel.getPhoneNum())){
			log.info("联系方式未填");
			result.setStatus(Status.phone_empty_error_status);
			result.setInfo(Status.phone_empty_error_info);
			return result;
		}else{
			user.setcMobilePhone(orgUserModel.getPhoneNum());
		}	
		
		if(Help.isNull(orgUserModel.getUserAddr())){
			log.info("机构地址未填");
			result.setStatus(Status.orgAddr_error_status);
			result.setInfo(Status.orgAddr_error_info);
			return result;
		}else{
			user.setcAddr(orgUserModel.getUserAddr());
		}	

		user.setnUserType(FinVal.USER_TYPE_ORG);
		
		userMapper.updateByPrimaryKeySelective(user);
       		
		redisBean.set(sessionId, FinVal.SESSION_EXPIRECE, GsonUtil.objectToJson(user));
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		return result;
	}

	@Override
	public Result addUserForOrg(String teacherName,String phoneNum,String teacherIntro) {
		Result result = new Result();
		
		if(Help.isNull(teacherName)){
			log.info("讲师的姓名未填");
			result.setStatus(Status.orguser_not_exist_teacher_error_status);
			result.setInfo(Status.orguser_not_exist_teacher_error_info);
			return result;
		}
		
		if(Help.isNull(phoneNum)){
			log.info("联系方式未填");
			result.setStatus(Status.phone_empty_error_status);
			result.setInfo(Status.phone_empty_error_info);
			return result;
		}
		
		if(Help.isNull(teacherIntro)){
			log.info("讲师介绍未填");
			result.setStatus(Status.orguser_not_exist_teacherintro_error_status);
			result.setInfo(Status.orguser_not_exist_teacherintro_error_info);
			return result;
		}
		
		
		User curUser = getCurUser();
		/*if(curUser.getnAuthStatus() != FinVal.AUTH_STATUS_SUCCESS){
			log.info("您还没有认证通过，暂时不能添加机构用户！");
			result.setStatus(Status.orguseradd_error_status);
			result.setInfo(Status.orguseradd_error_info);
			return result;
		}*/
		
		User findByAccount = userMapper.findByAccount(phoneNum);
		
		if(Help.isNull(findByAccount)){
			log.info("该手机号还没有注册！");
			result.setStatus(Status.phone_not_exist_error_status);
			result.setInfo(Status.phone_not_exist_error_info);
			return result;
		}
		
		if(!findByAccount.getcUserRealname().equals(teacherName)){
			log.info("手机号码和姓名不匹配!");
			result.setStatus(Status.orguser_not_match_error_status);
			result.setInfo(Status.orguser_not_match_error_info);
			return result;
		}
		
		if(findByAccount.getnUserType() != FinVal.USER_TYPE_PRIVATE){
			log.info("该手机号不是个人用户！");
			result.setStatus(Status.orguser_error_status);
			result.setInfo(Status.orguser_error_info);
			return result;
		}
		
		
		
		/*if(findByAccount.getnAuthStatus() != FinVal.AUTH_STATUS_SUCCESS){
			log.info("该手机号用户还没有认证成功！");
			result.setStatus(Status.orguserauth_error_status);
			result.setInfo(Status.orguserauth_error_info);
			return result;
		}*/
		
		List<OrgUser> findByUserId = orgUserMapper.findByUserId(findByAccount.getnUserId());
		if(Help.isNotNull(findByUserId)){
			log.info("该手机号用户已经加入其他机构！");
			result.setStatus(Status.orguseronly_error_status);
			result.setInfo(Status.orguseronly_error_info);
			return result;
		}
	
		OrgUser orgUser = new OrgUser();
		orgUser.setnOrgId(curUser.getnUserId());
		orgUser.setnUserId(findByAccount.getnUserId());
		orgUser.settCreateTime(new Date());
		orgUser.setcOrgUserIntro(teacherIntro);
		orgUserMapper.insertSelective(orgUser);
		
		//机构添加老师的话直接认证成功
		curUser.setnAuthStatus(FinVal.AUTH_STATUS_SUCCESS);
		userMapper.updateByPrimaryKeySelective(curUser);
		
		result.setInfo(Status.success_info);
		result.setStatus(Status.success_status);
		
		return result;
	}

	@Override
	public Result getOrgUsers() {
		// TODO Auto-generated method stub
		Result result = new Result();
		User curUser = getCurUser();
		List<OrgUser> OrgUsers = orgUserMapper.findByOrgId(curUser.getnUserId());
		List<UserInfoDto> userInfoDtoList =  new ArrayList<UserInfoDto>();
		if(Help.isNotNull(OrgUsers)){
			for(OrgUser orgUser:OrgUsers){
				UserInfoDto userInfoDto = new UserInfoDto();
				Long getnUserId = orgUser.getnUserId();
				User selectByPrimaryKey = userMapper.selectByPrimaryKey(getnUserId);
				userInfoDto.setUserId(selectByPrimaryKey.getnUserId());
				userInfoDto.setUserName(selectByPrimaryKey.getcUserRealname());
				userInfoDto.setUserPhone(selectByPrimaryKey.getcMobilePhone());
				userInfoDto.setHeadImg(selectByPrimaryKey.getcUserPortrait());
				userInfoDto.setMajor(selectByPrimaryKey.getcMajor());
				userInfoDto.setUserIntro(selectByPrimaryKey.getcUserIntroduce());
				userInfoDto.setSchoolName(selectByPrimaryKey.getcSchoolName());
				userInfoDto.setUserOrgId(orgUser.getnOrgUserId());
				userInfoDto.setUserOrgIntro(orgUser.getcOrgUserIntro());
				userInfoDtoList.add(userInfoDto);
			}
		}
		
		Map<String,Object> dataList = new HashMap<String, Object>();
		dataList.put("dataList", userInfoDtoList);
		result.setData(dataList);
		result.setInfo(Status.success_info);
		result.setStatus(Status.success_status);
		
		return result;
	}

	@Override
	public Result registSchool(String mobilePhone, String checkCode,
			String password, Long schoolId) {

		// TODO Auto-generated method stub
		Result result = new Result();
		log.info("开始参数===");	
		if(MUtil.isEmpty(mobilePhone)){
			log.info("手机号未填！！");
			result.setStatus(Status.phone_empty_error_status);
			result.setInfo(Status.phone_empty_error_info);
			return result;
		}
		if(!MUtil.toJudgeInput(mobilePhone, 2)){
			log.info("手机号格式错误");
			result.setStatus(Status.phone_fmt_error_status);
			result.setInfo(Status.phone_fmt_error_info);
			return result;
		}
		if(Help.isNull(checkCode)){
			log.info("验证码为空！");
			result.setStatus(Status.checkcode_empty_error_status);
			result.setInfo(Status.checkcode_empty_error_info);
			return result;
		}
		if(Help.isNull(password)){
			log.info("密码为空！");
			result.setStatus(Status.pwd_empty_status);
			result.setInfo(Status.pwd_empty_info);
			return result;
		}
		
		if(Help.isNull(schoolId)){
			log.info("未选择学校");
			result.setStatus(Status.school_empty_status);
			result.setInfo(Status.school_empty_info);
			return result;
		}
		School school = schoolMapper.selectByPrimaryKey(schoolId);
		if(Help.isNull(school)){
			log.info("学校不存在");
			result.setStatus(Status.school_not_exist_status);
			result.setInfo(Status.school_not_exist_info);
			return result;
		}
		
		String redisCheckCode = redisBean.get(mobilePhone+"_"+FinVal.CHECK_CODE_TYPE_REGISTER);
		if(!checkCode.equals(redisCheckCode)){
			log.info("验证码错误！");
			log.info("正确验证码为："+redisCheckCode);
			log.info("验证码错误为："+checkCode);
			result.setStatus(Status.checkcode_error_status);
			result.setInfo(Status.checkcode_error_info);
			return result;
		}
		User user = userMapper.findByAccount(mobilePhone);
		if(Help.isNotNull(user)){
			log.info("手机号码"+mobilePhone+"已被注册");
			result.setStatus(Status.phone_exist_error_status);
			result.setInfo(Status.phone_exist_error_info);
			return result;
		}
		User register = new User();
		Date date = new Date();
		register.setcUserAccount(mobilePhone);
		register.setcMobilePhone(mobilePhone);
		register.setcSchoolName(school.getcSchoolName());
		register.setnSchoolId(school.getnSchoolId());
		register.setcUserPassword(MUtil.md5(password));
		register.settCreateTime(date);
		register.settUpdateTime(date);
		register.setnUserType(FinVal.USER_TYPE_SCHOOL);
		userMapper.insertSelective(register);
		
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		
		redisBean.delete(mobilePhone+"_"+FinVal.CHECK_CODE_TYPE_REGISTER);
//		boolean createEMUser = EMutil.createEMUser(mobilePhone, MUtil.md5(password),school.getcSchoolName());
//		if(!createEMUser){
//			log.error("创建环信账户失败！！！");
//			throw new RuntimeException();
//		}
		
		return login(mobilePhone, password);
	
	}

	@Override
	public Result registOrg(String mobilePhone, String checkCode,
			String password) {

		// TODO Auto-generated method stub
		Result result = new Result();
		log.info("开始参数===");	
		if(MUtil.isEmpty(mobilePhone)){
			log.info("手机号未填！！");
			result.setStatus(Status.phone_empty_error_status);
			result.setInfo(Status.phone_empty_error_info);
			return result;
		}
		if(!MUtil.toJudgeInput(mobilePhone, 2)){
			log.info("手机号格式错误");
			result.setStatus(Status.phone_fmt_error_status);
			result.setInfo(Status.phone_fmt_error_info);
			return result;
		}
		if(Help.isNull(checkCode)){
			log.info("验证码为空！");
			result.setStatus(Status.checkcode_empty_error_status);
			result.setInfo(Status.checkcode_empty_error_info);
			return result;
		}
		if(Help.isNull(password)){
			log.info("密码为空！");
			result.setStatus(Status.pwd_empty_status);
			result.setInfo(Status.pwd_empty_info);
			return result;
		}
		String redisCheckCode = redisBean.get(mobilePhone+"_"+FinVal.CHECK_CODE_TYPE_REGISTER);
		if(!checkCode.equals(redisCheckCode)){
			log.info("验证码错误！");
			log.info("正确验证码为："+redisCheckCode);
			log.info("验证码错误为："+checkCode);
			result.setStatus(Status.checkcode_error_status);
			result.setInfo(Status.checkcode_error_info);
			return result;
		}
		User user = userMapper.findByAccount(mobilePhone);
		if(Help.isNotNull(user)){
			log.info("手机号码"+mobilePhone+"已被注册");
			result.setStatus(Status.phone_exist_error_status);
			result.setInfo(Status.phone_exist_error_info);
			return result;
		}
		User register = new User();
		Date date = new Date();
		register.setcUserAccount(mobilePhone);
		register.setcMobilePhone(mobilePhone);
		register.setcUserPassword(MUtil.md5(password));
		register.settCreateTime(date);
		register.settUpdateTime(date);
		register.setnUserType(FinVal.USER_TYPE_ORG);
		userMapper.insertSelective(register);
		
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		
		redisBean.delete(mobilePhone+"_"+FinVal.CHECK_CODE_TYPE_REGISTER);
//		boolean createEMUser = EMutil.createEMUser(mobilePhone, MUtil.md5(password),"");
//		if(!createEMUser){
//			log.error("创建环信账户失败！！！");
//			throw new RuntimeException();
//		}
		
		return login(mobilePhone, password);
	
	}

	@Override
	public Result delOrgUser(Long orgUserId) {
		Result result = new Result();
		log.info("开始删除===");	
		if(Help.isNull(orgUserId)){
			log.info("删除用户不存在！");
			result.setStatus(Status.orguserdel_error_status);
			result.setInfo(Status.orguserdel_error_info);
			return result;
		}
		orgUserMapper.deleteByPrimaryKey(orgUserId);
		
		result.setInfo(Status.success_info);
		result.setStatus(Status.success_status);
		
		return result;
	}

	@Override
	public Result modifyUserSchool(OrgUserModel orgUserModel,
			boolean orgLisenceFlag) {
		// TODO Auto-generated method stub
		Result result = new Result();
		
		String sessionId = SysContent.getSession().getId();
		String userStr = redisBean.get(sessionId);
		User user  = (User) GsonUtil.jsonToObject(userStr, User.class);
		if(Help.isNull(user)){
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		
		if(Help.isNull(orgUserModel.getUserRealName())){
			log.info("运营人姓名未填");
			result.setStatus(Status.userrealname_empty_error_status);
			result.setInfo(Status.userrealname_empty_error_info);
			return result;
		}else{
			user.setcUserRealname(orgUserModel.getUserRealName());
		}
		if(Help.isNull(orgUserModel.getUserCode())){
			log.info("身份证号码未填");
			result.setStatus(Status.usercode_empty_error_status);
			result.setInfo(Status.usercode_empty_error_info);
			return result;
		}else{
			user.setcUserCode(orgUserModel.getUserCode());
		}
		if(Help.isNull(orgUserModel.getUserCodeImg()) && orgLisenceFlag){
			log.info("身份证图片未上传!");
			result.setStatus(Status.usercodeimg_empty_error_status);
			result.setInfo(Status.usercodeimg_empty_error_info);
			return result;
		}else{
			user.setnAuthStatus(FinVal.AUTH_STATUS_SUBMIT);
			user.setcUserCodeImg(orgUserModel.getUserCodeImg());
		}
		
		if(Help.isNull(orgUserModel.getPhoneNum())){
			log.info("联系方式未填");
			result.setStatus(Status.phone_empty_error_status);
			result.setInfo(Status.phone_empty_error_info);
			return result;
		}else{
			user.setcMobilePhone(orgUserModel.getPhoneNum());
		}	
		
		user.setnUserType(FinVal.USER_TYPE_SCHOOL);
		
		userMapper.updateByPrimaryKeySelective(user);
       		
		redisBean.set(sessionId, FinVal.SESSION_EXPIRECE, GsonUtil.objectToJson(user));
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		return result;
	}

	
}
