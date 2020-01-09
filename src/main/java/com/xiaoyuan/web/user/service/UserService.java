package com.xiaoyuan.web.user.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xiaoyuan.common.entity.OrgUser;
import com.xiaoyuan.common.entity.User;
import com.xiaoyuan.common.util.Result;
import com.xiaoyuan.web.user.dto.UserInfoDto;
import com.xiaoyuan.web.user.model.OrgUserModel;

public interface UserService {
	/**
	 * 用户登录
	 * */
  public Result login(String account,String password);

   /**
    * 获取学校列表
    * */
   public Result school(String schoolName);
   /**
    * 初始化学校列表
    * */
   public Map<String,String> initSchool();
   /**
    * 发送验证码
    * */
   public Result sendCheckCode(String mobilePhone,String type);
   /**
    * 注册个人用户
    * */
   public Result register(String mobilePhone,String checkCode,String password,String userName,Long schoolId,String major,String grade);
   /**
    * 注册学校用户
    * */
   public Result registSchool(String mobilePhone,String checkCode,String password,Long schoolId);
   /**
    * 注册机构用户
    * */
   public Result registOrg(String mobilePhone,String checkCode,String password);
   /**
    * 意见反馈
    * */
   public Result advice(String content);

   /**
    * 退出
    * @return
    */
   public Result loginOut(HttpServletRequest request);
   
   /**
    * 修改密码
    * */
   public Result resetPwd(String mobilePhone, String checkCode,String newPwd);
   
   /**
    * 获取当前登录用户信息
    * */
   public Result getUserInfo();
   
   /**
    * 获取当前登录用户对象
    * */
   public UserInfoDto getCurrentUser(); 
   public User getCurUser();
   
   /**
    * 修改用户信息
    * */
   public Result modifyUserInfo(String userName, Long schoolId, String major,
		String grade,String potrait);
   
   /**
    * 机构认证
    * */
   public Result modifyUserOrg(OrgUserModel orgUserModel,boolean orgLisenceFlag);
   
   /**
    * 添加机构老师
    * */
   public Result addUserForOrg(String teacherName,String phoneNum,String teacherIntro); 
   
   /**
    * 获取机构用户列表
    * */
   public Result getOrgUsers(); 
   
   /**
    * 删除机构老师
    * */
   public Result delOrgUser(Long orgUserId); 
 
   /**
    * 学校认证
    * */
   public Result modifyUserSchool(OrgUserModel orgUserModel,boolean orgLisenceFlag);

}
