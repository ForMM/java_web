package com.xiaoyuan.test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;


public class TestHttps {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		KeyStore clientStore = KeyStore.getInstance("PKCS12");
		  clientStore.load(new FileInputStream("D:\\keystore\\clientkey.p12"), "password".toCharArray());
		  KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		  kmf.init(clientStore, "password".toCharArray());
		  KeyManager[] kms = kmf.getKeyManagers();
		  KeyStore trustStore = KeyStore.getInstance("JKS");
		  
		  trustStore.load(new FileInputStream("D:\\keystore\\private.keystore"), "password".toCharArray());
		  TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		  tmf.init(trustStore);
		  System.setProperty("javax.net.ssl.trustStore", "D:\\keystore\\private.keystore"); 
		  System.setProperty("javax.net.ssl.trustStorePassword", "password"); 
		  System.setProperty("org.jboss.security.ignoreHttpsHost", "true");
		  TrustManager[] tms = tmf.getTrustManagers();
		  SSLContext sslContext = null;
		  sslContext = SSLContext.getInstance("TLS");
		  sslContext.init(kms, tms, new SecureRandom());
		
		  HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		  URL url = new URL("https://112.74.67.239:8443");
		  HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
		//取得该连接的输入流，以读取响应内容 
		  InputStreamReader insr = new InputStreamReader(urlConn.getInputStream());

		  //读取服务器的响应内容并显示
		  int respInt = insr.read();
		  while( respInt != -1){
		  System.out.print((char)respInt);
		  respInt = insr.read();
		  }
	}

}
