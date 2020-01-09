package com.xiaoyuan.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.xiaoyuan.common.log.BKLogger;

public class AutoRunClass {
     
    private static final BKLogger log = BKLogger.getLogger(AutoRunClass.class);

    private static ApplicationContext ctx = null;
    private static Map<String, Object> beans = new ConcurrentHashMap<String, Object>();

    public static ApplicationContext getCtx() {

        if (null == ctx) {
            init();
        }
        return ctx;
    }
 
	public static final void init(){
		log.debug("系统初始化开始......");

		// 加载配置文件
        ctx = new FileSystemXmlApplicationContext("classpath:spring.xml", "classpath:spring-mybatis.xml");// 没有classpath表示当前目录
		
        log.debug("系统初始化完毕");
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clz){
		String beanName = clz.getName();
		if(!beans.containsKey(beanName)){
			beans.put(beanName, AutoRunClass.getCtx().getBean(clz));
		}
		return (T)beans.get(beanName);
	}

}