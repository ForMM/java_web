package com.xiaoyuan.common.util;

//*****************************************************************************
/**
 * <p>Title:Global</p>
 * <p>Description:静态常量类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author 汤清
 * @version v1.0 2016-03-14
 */
//*****************************************************************************
public final class Global {
	//**********************日志TAG,区分不同的日志类型*****************************
	//*************************************************************************
	
	
    public static final String JSON_SUCCESS = "{\"status\":\"10000\",\"info\":\"SUCCESS\",\"data\":{}}";
    public static final String JSON_ERROR = "{\"status\":\"99999\",\"info\":\"ERROR\",\"data\":{}}";
    
    public static final String APP_KEY = SystemGlobals.getInstance().getProperty("APP_KEY");
	
	
	public static final String SMS_URL = SystemGlobals.getInstance().getProperty("sms.api.url");
	public static final String SMS_PORT = SystemGlobals.getInstance().getProperty("sms.api.port");
	public static final String SMS_ACCOUNT_ID = SystemGlobals.getInstance().getProperty("sms.api.accountSid");
	public static final String SMS_ACCOUNT_TOKEN = SystemGlobals.getInstance().getProperty("sms.api.accountToken");
	public static final String SMS_APP_ID = SystemGlobals.getInstance().getProperty("sms.api.AppId");
	public static final String SMS_TEMPLATE_ID = SystemGlobals.getInstance().getProperty("sms.api.templateId");
	public static final String SMS_NOTIFY_TEMPLATE_ID = SystemGlobals.getInstance().getProperty("sms.api.notifytempId");
	
	public static final String HEAD_IMG_PATH=SystemGlobals.getInstance().getProperty("portrait");
	public static final String ARTICLE_IMG_PATH=SystemGlobals.getInstance().getProperty("article");
	public static final String AUTH_IMG_PATH=SystemGlobals.getInstance().getProperty("auth");
	
	public static final String TEMP_FILE_PATH=SystemGlobals.getInstance().getProperty("tempPath");
	
	public static final String APP_STATU=SystemGlobals.getInstance().getProperty("APP_STATU");
	public static final String NEWS_DETAIL_URL=SystemGlobals.getInstance().getProperty("newsdetail.url");
	
	//***********************************文件上传下载*************************************
	public static final String DOWNLOAD_URL = SystemGlobals.getInstance().getProperty("download.url");
	public static final String DOWNLOAD_FILE_URL = SystemGlobals.getInstance().getProperty("downloadfile.url");
	public static final String UPLOAD_URL = SystemGlobals.getInstance().getProperty("upload.url");
	public static final String COURSE_FILE_PATH=SystemGlobals.getInstance().getProperty("courseFile");		
	public static final String COURSE_IMG_PATH=SystemGlobals.getInstance().getProperty("courseImg");
	
}