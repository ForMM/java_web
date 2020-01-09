package com.xiaoyuan.common.interceptor;

import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiaoyuan.common.content.Status;
import com.xiaoyuan.common.content.SysContent;
import com.xiaoyuan.common.log.BKLogger;
import com.xiaoyuan.common.redis.RedisBean;
import com.xiaoyuan.common.util.Result;

/**
 * 登陆拦截类
 * 
 * */
public class LoginInterceptor {
	 public Log log=LogFactory.getLog(getClass());
	 BKLogger logger = BKLogger.getLogger(LoginInterceptor.class);
	 @Autowired
	 private RedisBean redisBean;
	 
	 public void doAfter(JoinPoint jp) {
	    	log.info("log Ending method: "
	                + jp.getTarget().getClass().getName() + "."
	                + jp.getSignature().getName());
	    	
	    	
	    }

	    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
	    	        String sessionId = SysContent.getSession().getId();
	    	        Result result = null;
	    	        if(redisBean.exists(sessionId)){
	    	        	long time = System.currentTimeMillis();
				        Object retVal = pjp.proceed();
				        time = System.currentTimeMillis() - time;
				        log.info("process time: " + time + " ms");
				        return retVal;
	    	        }else{
	    	        	PrintWriter pw=SysContent.getResponse().getWriter();
	    	        	result = new Result();
	    	        	result.setInfo(Status.oldtoken_error_info);
	    	        	result.setStatus(Status.oldtoken_error_status);
		    	    	pw.print(logger.infobk("校验登录输出列表",result));
		    	    	SysContent.getRequest().setAttribute("toLogin", "login");
		    	    	return "redirect";
	    	        }
	    }

	    public void doBefore(JoinPoint jp) {
	    	 log.info("log Begining method: "
	                + jp.getTarget().getClass().getName() + "."
	                + jp.getSignature().getName());
	    }

	    public void doThrowing(JoinPoint jp, Throwable ex) {
	    	 log.info("method " + jp.getTarget().getClass().getName()
	                + "." + jp.getSignature().getName() + " throw exception");
	    	 log.error(ex);
	    	 ex.printStackTrace();
	    }
}
