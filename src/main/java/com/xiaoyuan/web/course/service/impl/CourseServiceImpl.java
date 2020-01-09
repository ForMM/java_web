package com.xiaoyuan.web.course.service.impl;

import java.util.ArrayList; 
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.oss.util.OSSUtil;
import com.xiaoyuan.common.content.FinVal;
import com.xiaoyuan.common.content.Status;
import com.xiaoyuan.common.content.SysContent;
import com.xiaoyuan.common.dao.CourseMapper;
import com.xiaoyuan.common.dao.CourseTypeMapper;
import com.xiaoyuan.common.dao.CourseUserMapper;
import com.xiaoyuan.common.dao.OrgUserMapper;
import com.xiaoyuan.common.dao.UserFansMapper;
import com.xiaoyuan.common.dao.UserMapper;
import com.xiaoyuan.common.entity.Course;
import com.xiaoyuan.common.entity.CourseType;
import com.xiaoyuan.common.entity.CourseUser;
import com.xiaoyuan.common.entity.OrgUser;
import com.xiaoyuan.common.entity.User;
import com.xiaoyuan.common.entity.UserFans;
import com.xiaoyuan.common.exception.UploadException;
import com.xiaoyuan.common.log.LogTool;
import com.xiaoyuan.common.redis.RedisBean;
import com.xiaoyuan.common.util.Global;
import com.xiaoyuan.common.util.GsonUtil;
import com.xiaoyuan.common.util.Help;
import com.xiaoyuan.common.util.ImageUtil;
import com.xiaoyuan.common.util.MUtil;
import com.xiaoyuan.common.util.MyDateUtil;
import com.xiaoyuan.common.util.Paginator;
import com.xiaoyuan.common.util.Result;
import com.xiaoyuan.web.course.dto.CourseDetailDto;
import com.xiaoyuan.web.course.dto.CourseUsersDto;
import com.xiaoyuan.web.course.dto.UserCourseListDto;
import com.xiaoyuan.web.course.model.CourseModel;
import com.xiaoyuan.web.course.service.CourseService;
import com.xiaoyuan.web.user.dto.UserInfoDto;
import com.xiaoyuan.web.user.service.UserService;

@Service
public class CourseServiceImpl implements CourseService {
	private LogTool log = LogTool.getInstance(CourseServiceImpl.class);
	@Autowired
	private CourseTypeMapper courseTypeMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisBean redisBean;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private OrgUserMapper orgUserMapper;
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Autowired
	private CourseUserMapper courseUserMapper;	 
	
	@Autowired
	private UserFansMapper userFansMapper;
	
	@Override
	public Result allCourseType() {
		// TODO Auto-generated method stub
		Result result = new Result();
		log.debug("查询课堂类别");
		List<CourseType> list = courseTypeMapper.findAll();
		List<String> courseTypes = new ArrayList<String>();
		if(Help.isNotNull(list)){
			for(CourseType courseType:list){
				courseTypes.add(courseType.getcTypeName());
			}
		}
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("dataList", courseTypes);
		result.setData(data);
		return result;
	}
	@Override
	public Result lecturerApply(String IDNum, String courseTypeName,
			String intro, String authImg) {
		// TODO Auto-generated method stub
		Result result = new Result();
		String sessionId = SysContent.getSession().getId();
		User user = getSessionUser();
				
		if(Help.isNull(user)){
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		
		if(user.getnAuthStatus() == FinVal.AUTH_STATUS_SUBMIT || user.getnAuthStatus() == FinVal.AUTH_STATUS_SUCCESS){
			result.setStatus(Status.auth_error_status);
			result.setInfo(Status.auth_error_info);
			return result;
		}
				
		if(Help.isNull(IDNum)||!MUtil.toJudgeInput(IDNum, 7)){
			result.setStatus(Status.id_error_status);
			result.setInfo(Status.id_error_info);
			return result;
		}
		if(Help.isNull(courseTypeName)){
			result.setStatus(Status.course_type_empty_status);
			result.setInfo(Status.course_type_empty_info);
			return result;
		}
		if(Help.isNull(intro)){
			result.setStatus(Status.intro_empty_status);
			result.setInfo(Status.intro_empty_info);
			return result;
		}
		if(Help.isNull(authImg)){
			result.setStatus(Status.authImg_empty_status);
			result.setInfo(Status.authImg_empty_info);
			return result;
		}
		user.setcAuthImgs(authImg);
		user.setcUserIntroduce(intro);
		user.setcUserCode(IDNum);
		user.setcCourseType(courseTypeName);
		user.setnAuthStatus(FinVal.AUTH_STATUS_SUBMIT);
		userMapper.updateByPrimaryKeySelective(user);
		redisBean.set(sessionId, FinVal.SESSION_EXPIRECE, GsonUtil.objectToJson(user));
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		return result;
	}
	public User getSessionUser(){
		User user= null;
		String sessionId = SysContent.getSession().getId();
		String userStr = redisBean.get(sessionId);		
		if(Help.isNotNull(userStr)){
			
			 try{
				 user = (User) GsonUtil.jsonToObject(userStr, User.class);
				 if(Help.isNotNull(user)){
					 user=userMapper.selectByPrimaryKey(user.getnUserId());
				 }
			 }catch(Exception ex){
				 log.error(ex);
			 }
		}
		return user;
	}
	@Override
	public Result pubCourse(CourseModel courseModel) {
		// TODO Auto-generated method stub
		Result result = new Result();
		
		if(Help.isNull(courseModel.getTitle())){
			log.debug("课堂名称未填");
			result.setStatus(Status.courseName_empty_status);
			result.setInfo(Status.courseName_empty_info);
			return result;
		}
		if(Help.isNull(courseModel.getIntro())){
			log.debug("课堂介绍未填");
			result.setStatus(Status.courseIntro_empty_status);
			result.setInfo(Status.courseIntro_empty_info);
			return result;
		}
		if(Help.isNull(courseModel.getType())){
			log.debug("课堂类别未填");
			result.setStatus(Status.course_type_empty_status);
			result.setInfo(Status.course_type_empty_info);
			return result;
		}
		if(Help.isNull(courseModel.getBeginTime())){
			log.debug("开课时间未填");
			result.setStatus(Status.course_beginDate_empty_status);
			result.setInfo(Status.course_beginDate_empty_info);
			return result;
		}
		if(Help.isNull(courseModel.getEndTime())){
			log.debug("结束时间未填");
			result.setStatus(Status.course_endDate_empty_status);
			result.setInfo(Status.course_endDate_empty_info);
			return result;
		}
		
		Date begin = MyDateUtil.parseDate(courseModel.getBeginTime(), "yyyy-MM-dd HH:mm");
		Date end = MyDateUtil.parseDate(courseModel.getEndTime(), "yyyy-MM-dd HH:mm");
		if(Help.isNull(begin)||Help.isNull(end)){
			log.debug("时间格式错误");
			result.setStatus(Status.date_format_error_status);
			result.setInfo(Status.date_format_error_info);
			return result;
		}
		Date now = new Date();
		if(begin.getTime()<now.getTime()){
			log.debug("课程开始时间小于当前时间");
			result.setStatus(Status.course_beginDate_error_status);
			result.setInfo(Status.course_beginDate_error_info);
			return result;
		}
		if(end.getTime()<begin.getTime()){
			log.debug("课程结束时间小于开始时间");
			result.setStatus(Status.course_endDate_error_status);
			result.setInfo(Status.course_endDate_error_info);
			return result;
		}
		UserInfoDto user = userService.getCurrentUser();
		
		boolean equals = FinVal.USER_TYPE_ORG.equals(user.getUserType());
		if(equals && Help.isNull(courseModel.getPhoneNum())){
			log.debug("分配老师的手机号未填");
			result.setStatus(Status.orguserteach_error_status);
			result.setInfo(Status.orguserteach_error_info);
			return result;
		}
		
		if(Help.isNull(user)){
			log.info("获取用户信息失败");
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		Long userId = user.getUserId();
		log.debug("验证是否通过直播认证");
		Integer authStatus = user.getAuthStatus();
		if(!FinVal.AUTH_STATUS_SUCCESS.equals(authStatus)){
			result.setStatus(Status.auth_deny_status);
			result.setInfo(Status.auth_deny_info);
			return result;
		}
		Integer userType = user.getUserType();
		Course course = new Course();
		course.setcCourseImg(courseModel.getImgs());
		course.setcCourseName(courseModel.getTitle());
		course.setcCourseIntro(courseModel.getIntro());
		course.setcCourseType(courseModel.getType());
			
		List<OrgUser> orgUsers = orgUserMapper.findByUserId(userId);
		
		if(userType.equals(FinVal.USER_TYPE_PRIVATE)&&Help.isNotNull(orgUsers)){
			OrgUser orgUser = orgUsers.get(0);
			course.setnOrgId(orgUser.getnOrgId());
			course.setnTeacherId(orgUser.getnUserId());
			log.debug("用户所在机构ID=="+orgUser.getnOrgId());
		}else{
			String teacherPhone = courseModel.getPhoneNum();
			User findByAccount = userMapper.findByAccount(teacherPhone);
			if(Help.isNull(findByAccount)){
				log.debug("添加的老师不存在");
				result.setStatus(Status.orguser_not_exist_error_status);
				result.setInfo(Status.orguser_not_exist_error_info);
				return result;
			}
			
			List<OrgUser> findByUserId = orgUserMapper.findByUserId(findByAccount.getnUserId());
			if(Help.isNull(findByUserId)){
				log.debug("添加的老师可能还没有加入到机构老师中");
				result.setStatus(Status.orguserteach_not_error_status);
				result.setInfo(Status.orguserteach_not_error_info);
				return result;
			}else{
				OrgUser orgUser = findByUserId.get(0);
				course.setnTeacherId(orgUser.getnUserId());
				course.setnSchoolId(findByAccount.getnSchoolId());
				course.setnOrgId(orgUser.getnOrgId());
			}
		}
		
		
	
		course.settCreateTime(now);
		course.settUpdateTime(now);
		
		course.settBeginTime(begin);
		course.settEndTime(end);
		courseMapper.insertSelective(course);
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		
		return result;
	}
	
	@Override
	public Result userCourseList(Long userId, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		Result result = new Result();
		User sessionUser = userService.getCurUser();
		if(Help.isNull(sessionUser)){
			log.info("获取用户信息失败");
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		if(Help.isNull(userId)){
			log.debug("用户ID为空");
			result.setStatus(Status.user_not_exist_error_status);
			result.setInfo(Status.user_not_exist_error_info);
			return result;
		}
		log.debug("userId=="+userId);
		User user = userMapper.selectByPrimaryKey(userId);
		
		if(user.getnUserType() == FinVal.USER_TYPE_ORG){
			return orgCourseList(userId, pageSize, page);
		}
		return userCourseList(user, pageSize, page);
	}
	
	private Result userCourseList(User user,Integer pageSize ,Integer page){
		Result result = new Result();
		page=page==null?FinVal.DEFAULT_PAGE:page;
		pageSize=pageSize==null?FinVal.DEFAULT_PAGESIZE:pageSize;
		if(Help.isNull(user)){
			result.setStatus(Status.user_not_exist_error_status);
			result.setInfo(Status.user_not_exist_error_info);
			return result;
		}
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("teacherId", user.getnUserId());
		
		int countByParam = courseMapper.countByParam(param);
		Paginator paginator = new Paginator(0, pageSize);
		paginator.gotoPage(page);
		int pageCount = paginator.calcPageCount(countByParam); // 总页数
		
		param.put("startRow", paginator.getStartRow());
		param.put("pageSize", paginator.getPageSize());
		List<Course> list = courseMapper.findByParam(param);
		List<UserCourseListDto> userCourseListDtos = new ArrayList<UserCourseListDto>();
		if(Help.isNotNull(list)){
			Date now = new Date();
			for(Course course:list){
				UserCourseListDto userCourseListDto = new UserCourseListDto();
				userCourseListDto.setCount(course.getnCountUser());
				userCourseListDto.setCourseId(course.getnCourseId());
				userCourseListDto.setCourseName(course.getcCourseName());
				userCourseListDto.setImg(course.getcCourseImg());
				userCourseListDto.setCourseIntro(course.getcCourseIntro());
				Date beginTime = course.gettBeginTime();
				Date endTime = course.gettEndTime();
				if(now.getTime()<beginTime.getTime()){
					//未开始
					userCourseListDto.setStatu(FinVal.COURSE_STATU_BEFORE);
				}else if(now.getTime()>=beginTime.getTime()&&now.getTime()<=endTime.getTime()){
					//直播中
					userCourseListDto.setStatu(FinVal.COURSE_STATU_BGIN);
				}else if(now.getTime()>endTime.getTime()){
					//已结束
					userCourseListDto.setStatu(FinVal.COURSE_STATU_END);
				}
				userCourseListDto.setTeacherName(user.getcUserRealname());
				userCourseListDto.setBeginTime(beginTime);
				userCourseListDto.setCreateTime(course.gettCreateTime());
				userCourseListDto.setPptImgs(convertPPTImgs(course));
				userCourseListDtos.add(userCourseListDto);
			
			}
			
		}
		
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("dataList", userCourseListDtos);
		data.put("page", page);
		data.put("pageSize", pageSize);
		data.put("pageCount", pageCount);
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		result.setData(data);
		return result;
	}
	private Map<String,Object> convertPPTImgs(Course course){
		String imgsJSON = course.getcPptImgs();
		Map<String, Object> pptImgs = new HashMap<String,Object>();
		if(Help.isNotNull(imgsJSON)){
			pptImgs.put("title", course.getcCourseName());
			pptImgs.put("id", course.getnCourseId());
			List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
			
			JSONArray jsonArr = JSONArray.fromObject(imgsJSON);
			for(int i = 0;i<jsonArr.size();i++){
				Map<String,Object> photo = new HashMap<String,Object>();
				JSONObject jsonObj = (JSONObject) jsonArr.get(i);
				String imgPath = (String) jsonObj.get("imgPath");
				photo.put("src", imgPath);
				data.add(photo);
			} 
			pptImgs.put("data", data );
			
		}
		return pptImgs;
	}
	private Result orgCourseList(Long orgId,Integer pageSize ,Integer page){
		Result result = new Result();
		page=page==null?FinVal.DEFAULT_PAGE:page;
		pageSize=pageSize==null?FinVal.DEFAULT_PAGESIZE:pageSize;
		if(Help.isNull(orgId)){
			result.setStatus(Status.user_not_exist_error_status);
			result.setInfo(Status.user_not_exist_error_info);
			return result;
		}
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("orgId", orgId);
		
		int countByParam = courseMapper.countByParam(param);
		Paginator paginator = new Paginator(0, pageSize);
		paginator.gotoPage(page);
		int pageCount = paginator.calcPageCount(countByParam); // 总页数
		
		param.put("startRow", paginator.getStartRow());
		param.put("pageSize", paginator.getPageSize());
		List<Course> list = courseMapper.findByParam(param);
		List<UserCourseListDto> userCourseListDtos = new ArrayList<UserCourseListDto>();
		if(Help.isNotNull(list)){
			Date now = new Date();
			for(Course course:list){
				UserCourseListDto userCourseListDto = new UserCourseListDto();
				userCourseListDto.setCount(course.getnCountUser());
				userCourseListDto.setCourseId(course.getnCourseId());
				userCourseListDto.setCourseName(course.getcCourseName());
				userCourseListDto.setImg(course.getcCourseImg());
				userCourseListDto.setCourseIntro(course.getcCourseIntro());
				Date beginTime = course.gettBeginTime();
				Date endTime = course.gettEndTime();
				if(now.getTime()<beginTime.getTime()){
					//未开始
					userCourseListDto.setStatu(FinVal.COURSE_STATU_BEFORE);
				}else if(now.getTime()>=beginTime.getTime()&&now.getTime()<=endTime.getTime()){
					//直播中
					userCourseListDto.setStatu(FinVal.COURSE_STATU_BGIN);
				}else if(now.getTime()>endTime.getTime()){
					//已结束
					userCourseListDto.setStatu(FinVal.COURSE_STATU_END);
				}
				userCourseListDto.setBeginTime(beginTime);
				userCourseListDto.setCreateTime(course.gettCreateTime());
				userCourseListDto.setPptImgs(convertPPTImgs(course));
				Long teacherId = course.getnTeacherId();
				User teacher = userMapper.selectByPrimaryKey(teacherId);
				if(Help.isNotNull(teacher)){
					userCourseListDto.setTeacherName(teacher.getcUserRealname());
				}
				userCourseListDtos.add(userCourseListDto);
			}
			
		}
		
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("dataList", userCourseListDtos);
		data.put("page", page);
		data.put("pageSize", pageSize);
		data.put("pageCount", pageCount);
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		result.setData(data);
		return result;
	}
	
	@Override
	public Result courseDetail(Long courseId) {
		// TODO Auto-generated method stub
		Result result = new Result();
		User user = userService.getCurUser();
		if(Help.isNull(user)){
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		Long userId = user.getnUserId();
		if(Help.isNull(courseId)){
			log.info("courseId为空");
			result.setStatus(Status.course_id_empty_status);
			result.setInfo(Status.course_id_empty_info);
			return result;
		}
		Course course = courseMapper.selectByPrimaryKey(courseId);
		if(Help.isNull(course)){
			log.info("课堂不存在，courseId="+courseId);
			result.setStatus(Status.course_not_exist_status);
			result.setInfo(Status.course_not_exist_info);
			return result;
		}
		Long teacherId = course.getnTeacherId();
		CourseDetailDto courseDetailDto = new CourseDetailDto();
		if(userId.equals(teacherId)){
			courseDetailDto.setIsTeacher(FinVal.DEFAULT_YES);
		}else{
			courseDetailDto.setIsTeacher(FinVal.DEFAULT_NO);
		}
		courseDetailDto.setImgs(MUtil.convertStringToList(course.getcCourseImg()));
		courseDetailDto.setBegin(course.gettBeginTime());
		courseDetailDto.setCountUser(course.getnCountUser());
		courseDetailDto.setCourseId(courseId);
		courseDetailDto.setCourseImg(course.getcCourseImg());
		courseDetailDto.setCourseName(course.getcCourseName());
		courseDetailDto.setCourseIntro(course.getcCourseIntro());
		courseDetailDto.setCourseType(course.getcCourseType());
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("courseId", courseId);
		
		param.put("startRow", 0);
		param.put("pageSize", 10);
		List<CourseUser> list = courseUserMapper.findByParam(param);
		courseDetailDto.setCourseUsers(converCourseUsers(list));
		courseDetailDto.setEnd(course.gettEndTime());
		
		log.debug("查看是否已关注");
		param.clear();
		param.put("userId", teacherId);
		param.put("fansId", userId);
		List<UserFans> userFans = userFansMapper.findByParam(param);
		if(Help.isNotNull(userFans)){
			courseDetailDto.setIsAttention(FinVal.DEFAULT_YES);
		}else{
			courseDetailDto.setIsAttention(FinVal.DEFAULT_NO);
		}
		param.clear();
		param.put("userId", userId);
		param.put("courseId", courseId);
		List<CourseUser> couseUsers = courseUserMapper.findByParam(param);
		if(Help.isNotNull(couseUsers)){
			courseDetailDto.setIsSignup(FinVal.DEFAULT_YES);
		}else{
			courseDetailDto.setIsSignup(FinVal.DEFAULT_NO);
		}
		User teacher = userMapper.selectByPrimaryKey(teacherId);
		if(Help.isNotNull(teacher)){
			Integer userType = teacher.getnUserType();
			if(FinVal.USER_TYPE_ORG.equals(userType)){
				//老师为机构用户
				courseDetailDto.setOrgId(teacherId);
				courseDetailDto.setOrgIntro(teacher.getcUserIntroduce());
				courseDetailDto.setOrgName(teacher.getcUserRealname());
			}else if(FinVal.USER_TYPE_PRIVATE.equals(userType)){
				List<OrgUser> orgUsers = orgUserMapper.findByUserId(teacherId);
				if(Help.isNotNull(orgUsers)){
					OrgUser orgUser = orgUsers.get(0);
					Long orgId = orgUser.getnOrgId();
					User org = userMapper.selectByPrimaryKey(orgId);
					if(Help.isNotNull(org)){
						courseDetailDto.setOrgId(orgId);
						courseDetailDto.setOrgIntro(org.getcUserIntroduce());
						courseDetailDto.setOrgName(org.getcUserRealname());
					}
				}
				
			}
			courseDetailDto.setTeacherId(teacherId);
			courseDetailDto.setTeacherIntro(teacher.getcUserIntroduce());
			courseDetailDto.setTeacherMajor(teacher.getcMajor());
			courseDetailDto.setTeacherName(teacher.getcUserRealname());
			courseDetailDto.setTeacherSchoolName(teacher.getcSchoolName());
		}
		
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("courseDetail", courseDetailDto);
		result.setData(data);
		return result;
	}
	
	private List<CourseUsersDto> converCourseUsers(List<CourseUser> list){
		List<CourseUsersDto> courseUsersDtos = new ArrayList<CourseUsersDto>();
		if(Help.isNotNull(list)){
			for(CourseUser courseUser:list){
				Long userId = courseUser.getnUserId();
				CourseUsersDto courseUsersDto = new CourseUsersDto();
				User fans = userMapper.selectByPrimaryKey(userId);
				if(Help.isNotNull(fans)){
					courseUsersDto.setHeadImg(fans.getcUserPortrait());
					courseUsersDto.setMajor(fans.getcMajor());
					courseUsersDto.setSchoolName(fans.getcSchoolName());
					courseUsersDto.setUserId(userId);
					courseUsersDto.setUserName(fans.getcUserRealname());
					courseUsersDtos.add(courseUsersDto);
				}
			}
		}
		return courseUsersDtos;
			
	}
	@Override
	public Result modifyCourse(CourseModel courseModel) throws Exception {
		Result result = new Result();
		User user = userService.getCurUser();
		if(Help.isNull(user)){
			log.info("获取用户信息失败");
			result.setStatus(Status.oldtoken_error_status);
			result.setInfo(Status.oldtoken_error_info);
			return result;
		}
		if(Help.isNull(courseModel.getCourseId())){
			log.info("课程不存在！");
			result.setStatus(Status.course_not_exist_status);
			result.setInfo(Status.course_not_exist_info);
			return result;
		}
		Course course = courseMapper.selectByPrimaryKey(courseModel.getCourseId());
		if(Help.isNull(course)){
			log.info("课程不存在！");
			result.setStatus(Status.course_not_exist_status);
			result.setInfo(Status.course_not_exist_info);
			return result;
		}
		if(Help.isNull(courseModel.getTitle())){
			log.debug("课堂名称未填");
			result.setStatus(Status.courseName_empty_status);
			result.setInfo(Status.courseName_empty_info);
			return result;
		}
		if(Help.isNull(courseModel.getIntro())){
			log.debug("课堂介绍未填");
			result.setStatus(Status.courseIntro_empty_status);
			result.setInfo(Status.courseIntro_empty_info);
			return result;
		}
		if(Help.isNull(courseModel.getType())){
			log.debug("课堂类别未填");
			result.setStatus(Status.course_type_empty_status);
			result.setInfo(Status.course_type_empty_info);
			return result;
		}
		if(Help.isNull(courseModel.getBeginTime())){
			log.debug("开课时间未填");
			result.setStatus(Status.course_beginDate_empty_status);
			result.setInfo(Status.course_beginDate_empty_info);
			return result;
		}
		if(Help.isNull(courseModel.getEndTime())){
			log.debug("结束时间未填");
			result.setStatus(Status.course_endDate_empty_status);
			result.setInfo(Status.course_endDate_empty_info);
			return result;
		}
		Date begin = MyDateUtil.parseDate(courseModel.getBeginTime(), "yyyy-MM-dd HH:mm");
		Date end = MyDateUtil.parseDate(courseModel.getEndTime(), "yyyy-MM-dd HH:mm");
		if(Help.isNull(begin)||Help.isNull(end)){
			log.debug("时间格式错误");
			result.setStatus(Status.date_format_error_status);
			result.setInfo(Status.date_format_error_info);
			return result;
		}
		Date now = new Date();
		if(begin.getTime()<now.getTime()){
			log.debug("课程开始时间小于当前时间");
			result.setStatus(Status.course_beginDate_error_status);
			result.setInfo(Status.course_beginDate_error_info);
			return result;
		}
		if(end.getTime()<begin.getTime()){
			log.debug("课程结束时间小于开始时间");
			result.setStatus(Status.course_endDate_error_status);
			result.setInfo(Status.course_endDate_error_info);
			return result;
		}
		Long userId = user.getnUserId();
		log.debug("验证是否通过直播认证");
		Integer authStatus = user.getnAuthStatus();
		if(!FinVal.AUTH_STATUS_SUCCESS.equals(authStatus)){
			result.setStatus(Status.auth_deny_status);
			result.setInfo(Status.auth_deny_info);
			return result;
		}
		
		if(!user.getnUserType().equals(FinVal.USER_TYPE_ORG) && !userId.equals(course.getnTeacherId())){
			result.setStatus(Status.permission_error_status);
			result.setInfo(Status.permission_error_info);
			return result;
		}
		
		
		/*if(!userId.equals(course.getnTeacherId())){
			result.setStatus(Status.permission_error_status);
			result.setInfo(Status.permission_error_info);
			return result;
		}*/
		course.setcCourseImg(courseModel.getImgs());
		course.setcCourseName(courseModel.getTitle());
		course.setcCourseIntro(courseModel.getIntro());
		course.setcCourseType(courseModel.getType());
		course.settBeginTime(begin);
		course.settEndTime(end);
		course.settUpdateTime(new Date());
		courseMapper.updateByPrimaryKeySelective(course);
		
		result.setStatus(Status.success_status);
		result.setInfo(Status.success_info);
		return result;
	}
	@Override
	public Result uploadCourseFile(MultipartFile courseFile,
			HttpServletRequest request, HttpServletResponse response,
			Long courseId) throws Exception {

		Result result = new Result();
		log.info("准备上传课程ppt...");		
		if(Help.isNull(courseFile)){
			  log.info("上传的课程ppt不存在！");			
		}
		
		String courseFileName = courseFile.getOriginalFilename();		  
		
		JSONArray imgs = new JSONArray();
		String courseName = "";		
		String pptPath ="";
		if(Help.isNotNull(courseFile)){
			log.info("开始上传课程ppt文件...");	
			String bpformat = courseFileName.substring(courseFileName.lastIndexOf("."));
			if(!".ppt".equalsIgnoreCase(bpformat)&&!".pptx".equalsIgnoreCase(bpformat) &&!".doc".equalsIgnoreCase(bpformat)&&!".docx".equalsIgnoreCase(bpformat)&&!".pdf".equalsIgnoreCase(bpformat)){
				log.info("上传课程ppt文件格式错误...");				
			}else{
								
				courseName = ImageUtil.uploadFile(courseFile, courseFileName);
				log.info("上传课程ppt文件成功...");	
						
		    	try {
		    		log.info("课程ppt文件准备开始转图片操作...");	
		    		String docPath = Global.COURSE_FILE_PATH+courseName;
		    		String pdfPath = Global.COURSE_IMG_PATH;		    				    		
		    		if(".pdf".equalsIgnoreCase(bpformat)){ //pdf单独处理，word ppt 一起处理
		    			String bpPdfPath =String.format("%s%s.pdf",  FilenameUtils.getFullPath(docPath), FilenameUtils.getBaseName(docPath));
		    			imgs = ImageUtil.pdf2Imgs(bpPdfPath, docPath,courseId);		    			
		    		}else{ 
		    			imgs = ImageUtil.doc2Imags(docPath, pdfPath,courseId);  
		    		}		
		    		log.info("课程ppt文件转图片操作成功...");	
		    		log.debug("将ppt上传至阿里云");
		    		
		    		String tempPath = Global.COURSE_FILE_PATH+courseName;
		            pptPath = OSSUtil.uploadFile(tempPath, "courseppt");//山川之后会删除tempPath
				}catch(UploadException ue){
					throw new UploadException(ue.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					log.error("课程ppt文件转图片失败... ",e);	
					throw new RuntimeException("课程ppt文件转图片失败... ");
				}
			}		
		}

	    	//更新课堂的文件信息
			Course course = courseMapper.selectByPrimaryKey(courseId);
	    	String pptImgs = imgs.toString();
	    	course.setcPptPath(pptPath);
	    	course.setcPptImgs(pptImgs);
	    	courseMapper.updateByPrimaryKey(course);
	    	
	    	result.setStatus(Status.success_status);
			result.setInfo(Status.success_info);
			Map<String,Object> data = new HashMap<String,Object>();
			Map<String, Object> convertPPTImgs = convertPPTImgs(course);
			data.put("pptImgs", convertPPTImgs);
			data.put("courseId", courseId);
			result.setData(data);
			return result;
	}
	
}
