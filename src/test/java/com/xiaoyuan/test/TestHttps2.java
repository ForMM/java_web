package com.xiaoyuan.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class TestHttps2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String httpsURL = "https://112.74.67.239:8443/";  
        String trustStor="D:/keystore/private.keystore";  
        System.setProperty("javax.net.ssl.trustStore", trustStor);  
        URL myurl = new URL(httpsURL);  
        HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();  
        con.setHostnameVerifier(hv);  
        InputStream ins = con.getInputStream();  
        InputStreamReader isr = new InputStreamReader(ins);  
        BufferedReader in = new BufferedReader(isr);  
        String inputLine=null;  
        while ((inputLine = in.readLine()) != null) {  
            System.out.println(inputLine);  
        }  
        
        in.close();  
	}
	private static HostnameVerifier hv = new HostnameVerifier() {  
        public boolean verify(String urlHostName, SSLSession session) {  
            return urlHostName.equals(session.getPeerHost());  
        }  
};  

}
