package com.xiaoyuan.test;

import java.security.MessageDigest;

public class TestMD5 {
	 public final static String MD5(String s) {  
	        char hexDigits[] = { '0', '1', '2', '3', '4',  
	                             '5', '6', '7', '8', '9',  
	                             'A', 'B', 'C', 'D', 'E', 'F' };  
	        try {  
	            byte[] btInput = s.getBytes();  
	     //获得MD5摘要算法的 MessageDigest 对象  
	            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
	     //使用指定的字节更新摘要  
	            mdInst.update(btInput);  
	     //获得密文  
	            byte[] md = mdInst.digest();  
	     //把密文转换成十六进制的字符串形式  
	            int j = md.length;  
	            char str[] = new char[j * 2];  
	            int k = 0;  
	            for (int i = 0; i < j; i++) {  
	                byte byte0 = md[i];  
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
	                str[k++] = hexDigits[byte0 & 0xf];  
	            }  
	            return new String(str);  
	        }  
	        catch (Exception e) {  
	            e.printStackTrace();  
	            return null;  
	        }  
	    }  
	     
	public static void main(String[] args) {
		System.out.println(MD5("appid=wxb14d804bd2c92f26&body=test&detail=detail&mch_id=1310608101&nonce_str=1234567890&notify_url=112.74.67.239/wxNotify&out_trade_no=1234567890&spbill_create_ip=112.74.67.239&total_fee=1&trade_type=APP&key=1VTIMEROWLINVERST201618806663064"));
	}
}
