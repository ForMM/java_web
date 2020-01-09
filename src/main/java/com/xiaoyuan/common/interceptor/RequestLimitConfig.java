package com.xiaoyuan.common.interceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求限制配置类
 * 
 * */
public class RequestLimitConfig {
   public static int defaultCounts=30;//30次
   public static int defaultTime=60;//60秒内访问超过30次，将被限制；
   public static int defautIpLimitTime=60*5;//默认IP限制时间5分钟
   public static Map<String,Integer> requestLimitCountMap=new HashMap<String,Integer>();
   public static Map<String,Integer> requestTimeMap=new HashMap<String,Integer>();
   public static Map<String,Integer> ipLimitTimeMap=new HashMap<String,Integer>();
   private static RequestLimitConfig requestLimitConfig;
   private RequestLimitConfig(){
	   //发送验证码，30秒内发送超过10次，将被限制30天内禁止访问
	   requestLimitCountMap.put("/user/sendCheckCode.do", 5);
	   requestTimeMap.put("/user/sendCheckCode.do", 60);
	   ipLimitTimeMap.put("/user/sendCheckCode.do", 60*60*24*30);
	 //登录，60秒内发送超过20次，将被限制1小时禁止访问
	   requestLimitCountMap.put("/user/login.do", 20);
	   requestTimeMap.put("/user/login.do", 60);
	   ipLimitTimeMap.put("/user/login.do", 60*60);
   }
   public static RequestLimitConfig  getInstance(){
	   if(requestLimitConfig==null){
		   requestLimitConfig = new RequestLimitConfig();
	   }
	   return requestLimitConfig;
   }
}
