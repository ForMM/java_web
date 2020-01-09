package com.xiaoyuan.common.content;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.xiaoyuan.common.log.BKLogger;
import com.xiaoyuan.common.util.Global;
import com.cloopen.rest.sdk.CCPRestSDK;



public class SendSMS {
	
	private static final BKLogger log = BKLogger.getLogger(SendSMS.class);
	
	/**
	 * 发送短信
	 * @throws IOException
	 */
	
	public static void main(String[] args) throws IOException {

		Map<String, Object> result = null;

		
		result = sendSMS("18320792425", "6666", "5");
		System.out.println("SDKTestSendTemplateSMS result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
	
	}
	/**
	 * 发送验证码
	 * account：手机号码
	 * checkCode：验证码
	 * time：有效时限，分钟 
	 * 
	 * */
	public static Map<String,Object> sendSMS(String account,String checkCode,String time){
		HashMap<String, Object> result = null;

		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(Global.SMS_URL, Global.SMS_PORT);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(Global.SMS_ACCOUNT_ID, Global.SMS_ACCOUNT_TOKEN);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(Global.SMS_APP_ID);// 初始化应用ID
		result = restAPI.sendTemplateSMS(account,Global.SMS_TEMPLATE_ID ,new String[]{checkCode,time});
		return result;
	}
	
}