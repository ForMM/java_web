package com.xiaoyuan.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import com.xiaoyuan.common.log.BKLogger;

/**
 * 网络操作类.
 * 
 * 
 * @Description 提供网络操作的相关方法.
 * @author 
 * @date 2013-5-29
 */
public class OperationNetUtil {
	
	private static BKLogger log = BKLogger.getLogger(OperationNetUtil.class);

	/** 返回结果 */
	private String result;
	/** 输入流 */
//	private BufferedInputStream input;
	private BufferedReader input;
	/** http链接 */
	private HttpURLConnection conn;
	/** 输出流 */
//	private DataOutputStream output;
	private BufferedWriter output;

	/**
	 * 像指定地址发送post请求提交数据.
	 * 
	 * @param path
	 *            数据提交路径.
	 * @param timeout
	 *            超时时间(毫秒).
	 * @param attribute
	 *            发送请求参数,key为属性名,value为属性值.
	 * @param encode
	 * 			  指定编码方式
	 * @return 服务器的响应信息,当发生错误时返回响应码.
	 * @throws IOException
	 *             网络连接错误时抛出IOException.
	 * @throws TimeoutException
	 *             网络连接超时时抛出TimeoutException.
	 * 
	 * @version 1.1
	 * @createTime 2013-3-5,下午4:33:20
	 * @updateTime 2013-4-17,下午3:21:56
	 * @createAuthor 
	 * @updateAuthor 
	 * @updateInfo 捕获非致命异常SocketTimeoutException同时抛出致命异常TimeoutException.
	 */
	public String sendPost(String path, String timeout, Map<String, String> attribute) throws IOException, TimeoutException {
		return sendPost(path, timeout, attribute, null);
	}

	/**
	 * 像指定地址发送post请求提交数据.
	 * 
	 * @param path
	 *            数据提交路径.
	 * @param timeout
	 *            超时时间(毫秒).
	 * @param attribute
	 *            发送请求参数,key为属性名,value为属性值.
	 * @param encode
	 * 			  指定编码方式
	 * @return 服务器的响应信息,当发生错误时返回响应码.
	 * @throws IOException
	 *             网络连接错误时抛出IOException.
	 * @throws TimeoutException
	 *             网络连接超时时抛出TimeoutException.
	 * 
	 * @version 1.1
	 * @createTime 2013-3-5,下午4:33:20
	 * @updateTime 2013-4-17,下午3:21:56
	 * @createAuthor 
	 * @updateAuthor 
	 * @updateInfo 捕获非致命异常SocketTimeoutException同时抛出致命异常TimeoutException.
	 */
	public String sendPost(String path, String timeout, Map<String, String> attribute, String encode) throws IOException, TimeoutException {
		try {
			URL url = new URL(path);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true); 					// 设置输出,post请求必须设置.
			conn.setDoInput(true); 						// 设置输入,post请求必须设置.
			conn.setUseCaches(false); 					// 设置是否启用缓存,post请求不能使用缓存.
			conn.setConnectTimeout(Integer.parseInt(timeout));
			conn.setReadTimeout(Integer.parseInt(timeout));
			conn.setRequestMethod("POST");
			conn.connect(); 									// 打开网络链接.
			if(encode == null || "".equals(encode)){
				output = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			}else{
				output = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), encode));
			}
			String params = "";
			if(attribute != null && attribute.keySet().size() > 0){
				params = getParams(attribute, encode);
				output.write(params); 				// 将请求参数写入网络链接.
			}
			log.debugbk("发送post请求输入参数<==", path + params);
			output.flush();
			return readResponse(encode);
		} catch (SocketTimeoutException e) {
			throw new TimeoutException(e.getMessage());
		}
	}
	
	/**
	 * 像指定地址发送get请求.
	 * 
	 * @param path
	 *            数据提交路径.
	 * @param timeout
	 *            超时时间,单位为毫秒.
	 * @return 服务器的响应信息,当发生错误时返回响应码.
	 * @throws IOException
	 *             网络连接错误时抛出IOException.
	 * @throws TimeoutException
	 *             网络连接超时时抛出TimeoutException.
	 * 
	 * @version 1.1
	 * @createTime 2013-3-5,下午4:40:14
	 * @updateTime 2013-4-17,下午3:28:56
	 * @createAuthor 
	 * @updateAuthor 
	 * @updateInfo 捕获非致命异常SocketTimeoutException同时抛出致命异常TimeoutException.
	 */
	public String sendGet(String path, int timeout) throws IOException, TimeoutException {
		return sendGet(path, timeout);
	}

	/**
	 * 像指定地址发送get请求.
	 * 
	 * @param path
	 *            数据提交路径.
	 * @param timeout
	 *            超时时间,单位为毫秒.
	 * @return 服务器的响应信息,当发生错误时返回响应码.
	 * @throws IOException
	 *             网络连接错误时抛出IOException.
	 * @throws TimeoutException
	 *             网络连接超时时抛出TimeoutException.
	 * 
	 * @version 1.1
	 * @createTime 2013-3-5,下午4:40:14
	 * @updateTime 2013-4-17,下午3:28:56
	 * @createAuthor 
	 * @updateAuthor 
	 * @updateInfo 捕获非致命异常SocketTimeoutException同时抛出致命异常TimeoutException.
	 */
	public String sendGet(String path, int timeout, String encode) throws IOException, TimeoutException {
		try {
			URL url = new URL(path);
			conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false); // 设置是否启用缓存,post请求不能使用缓存.
			conn.setConnectTimeout(timeout);
			conn.setRequestMethod("GET");
			conn.connect(); // 打开网络链接.
			return readResponse(encode);
		} catch (SocketTimeoutException e) {
			throw new TimeoutException(e.getMessage());
		}
	}

	/**
	 * 读取服务器响应信息.
	 * 
	 * @return 服务器的响应信息,当发生错误时返回响应码.
	 * @throws IOException
	 *             读取信息发生错误时抛出IOException.
	 * 
	 * @version 1.0
	 * @createTime 2013-3-5,下午4:48:00
	 * @updateTime 2013-3-5,下午4:48:00
	 * @createAuthor 
	 * @updateAuthor 
	 * @updateInfo
	 */
	private String readResponse(String encode) throws IOException {
		String code = Integer.toString(conn.getResponseCode());
		if (code.startsWith("2")) { // 若响应码以2开头则读取响应头总的返回信息
			if(encode == null || "".equals(encode)){
				input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			}else{
				input = new BufferedReader(new InputStreamReader(conn.getInputStream(), encode));
			}
			
			char[] charBuffer = new char[1024];
			StringBuffer sb = new StringBuffer();
			int length = -1;
			while ((length = input.read(charBuffer)) != -1) {
				sb.append(charBuffer.length == length ? charBuffer : Arrays.copyOf(charBuffer, length));
			}
			result = sb.toString().trim();
		} else { // 若响应码不以2开头则返回错误信息.
			return "error";
		}
		closeConnection();
		return result;
	}

	/**
	 * 将发送请求的参数构造为指定格式.
	 * 
	 * @param attribute
	 *            发送请求的参数,key为属性名,value为属性值.
	 * @param encode
	 * 			  指定编码
	 * @return 指定格式的请求参数.
	 * 
	 * @version 1.0
	 * @throws UnsupportedEncodingException 
	 * @createTime 2013-3-5,下午4:49:45
	 * @updateTime 2013-3-5,下午4:49:45
	 * @createAuthor 
	 * @updateAuthor 
	 * @updateInfo
	 */
	@SuppressWarnings("deprecation")
	private static String getParams(Map<String, String> attribute, String encode) throws UnsupportedEncodingException {
		Set<String> keys = attribute.keySet(); 				// 获取所有参数名
		Iterator<String> iterator = keys.iterator(); 		// 将所有参数名进行跌代
		StringBuffer params = new StringBuffer();
		// 取出所有参数进行构造
		while (iterator.hasNext()) {
			String key = iterator.next();
			String param = "";
			if(encode == null || "".equals(encode)){
				param = key + "=" + URLEncoder.encode(MUtil.strObject(attribute.get(key))) + "&";
			}else{
				param = key + "=" + URLEncoder.encode(MUtil.strObject(attribute.get(key)), encode) + "&";
			}
			
			params.append(param);
		}
		// 返回构造结果
		return params.toString().substring(0, params.toString().length() - 1);
	}

	/**
	 * 关闭链接与所有从链接中获得的流.
	 * 
	 * @throws IOException
	 *             关闭发生错误时抛出IOException.
	 * 
	 * @version 1.0
	 * @createTime 2013-3-5,下午4:51:31
	 * @updateTime 2013-3-5,下午4:51:31
	 * @createAuthor 
	 * @updateAuthor 
	 * @updateInfo
	 */
	private void closeConnection() throws IOException {
		if (input != null) {
			input.close();
		}
		if (output != null) {
			output.close();
		}
		if (conn != null) {
			conn.disconnect();
		}
	}

	/**
	 * 下载文件,下载文件存储至指定路径.
	 * 
	 * @param path
	 *            下载路径.
	 * @param savePath
	 *            存储路径.
	 * @return 下载成功返回true,若下载失败则返回false.
	 * @throws MalformedURLException
	 *             建立连接发生错误抛出MalformedURLException.
	 * @throws IOException
	 *             下载过程产生错误抛出IOException.
	 * 
	 * @version 1.2
	 * @createTime 2013-3-6,下午4:10:13
	 * @updateTime 2013-5-29,下午4:56:43
	 * @createAuthor 
	 * @updateAuthor 
	 * @updateInfo 取消图片的下载后缀,取消文件下载（除.jpg文件外）的tmp流程.
	 */
	public boolean downloadFile(String path, String savePath) throws MalformedURLException, IOException {
		File file = null;
		InputStream input = null;
		OutputStream output = null;
		boolean isComplete = false;
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.connect();
			int code = conn.getResponseCode();
			if (code == 200) {
				input = conn.getInputStream();
				if (savePath.endsWith(".jpg")) {
					savePath = savePath.replace(".jpg", ".tmp");
				}
				file = new File(savePath);
				File parent = file.getParentFile();
				if (!parent.exists())
					parent.mkdirs();
				file.createNewFile(); // 创建文件
				output = new FileOutputStream(file);
				byte buffer[] = new byte[1024];
				int read = 0;
				while ((read = input.read(buffer)) != -1) { // 读取信息循环写入文件
					output.write(buffer, 0, read);
				}
				output.flush();
				isComplete = true;
			} else {
				isComplete = false;
			}
		} catch (MalformedURLException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			if (null != output) {
				output.close();
			}
			if (isComplete) {
				if (savePath.endsWith(".tmp")) {
					file.renameTo(new File(savePath.replace(".tmp", "")));
				}
			}
		}
		return isComplete;
	}
	
	public static void main(String[] args) {
		
	}

}
