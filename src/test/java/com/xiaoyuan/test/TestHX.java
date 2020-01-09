package com.xiaoyuan.test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class TestHX {
  
	public static void main(String[] args) throws Exception {
		URL url = new URL("https://a1.easemob.com/vtime/owlinverst/token");
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");  
		conn.setRequestProperty("Content-Type", "application/json");
		conn.connect();  
		String boundary = "--------http post-------"; 
		DataOutputStream ds = new DataOutputStream(conn.getOutputStream());  
		 Map<String,String> textParams=new HashMap<String,String>();
		 Set<String> keySet = textParams.keySet();  
	        for (Iterator<String> it = keySet.iterator(); it.hasNext();) {  
	            String name = it.next();  
	            String value = textParams.get(name);  
	            ds.writeBytes("--" + boundary + "\r\n"); 
	            ds.writeBytes("Content-Disposition: form-data; name=\"" + name  
	                    + "\"\r\n");  
	            ds.writeBytes("\r\n");  
	            ds.writeBytes(encode(value) + "\r\n");  
	        }  
	        InputStream in = conn.getInputStream();  
	        ByteArrayOutputStream out = new ByteArrayOutputStream();  
	        int b;  
	        while ((b = in.read()) != -1) {  
	            out.write(b);  
	        }  
	        conn.disconnect();  
	        byte[] byteArray = out.toByteArray(); 
	        String jsonStr = new String(byteArray);  
	        System.out.println(jsonStr);
		
	}
	// 对包含中文的字符串进行转码，此为UTF-8。服务器那边要进行一次解码  
    private static String  encode(String value) throws Exception{  
        return URLEncoder.encode(value, "UTF-8");  
    } 
}
