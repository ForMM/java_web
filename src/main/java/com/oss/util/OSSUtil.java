package com.oss.util;

import java.io.File;

import com.xiaoyuan.common.log.BKLogger;
import com.xiaoyuan.common.log.LogTool;


public class OSSUtil {
	BKLogger logger = BKLogger.getLogger(OSSUtil.class);
	static LogTool log=LogTool.getInstance(OSSUtil.class);
	private static OSSConfigure ossConfigure;
	/**
	 * 上传到阿里云OSS
	 * @param filepath 上传路径
	 * @param filename 上传文件的名字
	 * @return
	 */
	static{
		try {
			ossConfigure = new OSSConfigure("config.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * filepath 本地文件路径
	 * OSSFilePath 上传到阿里云的路径
	 * */
	public static String uploadFile(String filepath,String OSSFilePath){
		
		String uploadpath = "";
		File file = null;
		try {
			
			 file = new File(filepath);			
			uploadpath = OSSManageUtil.uploadFile(ossConfigure, file, OSSFilePath);			
		} catch (Exception e) {
			 log.error("上传头像文件到阿里云OSS失败...",e);
		}finally{
			if(file.exists()){
				file.delete();
			}
		}
		
		return uploadpath;
	}
	
	public static void main(String[] args) {
		OSSUtil ss = new OSSUtil();	
		String uploadPorait = ss.uploadFile("E:\\banner-one1.png","test");
		System.out.println("##########################3");
		System.out.println(uploadPorait);
	}
}
