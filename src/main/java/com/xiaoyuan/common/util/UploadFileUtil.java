package com.xiaoyuan.common.util;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadFileUtil {
	
	public static Map<String, Object> updateFile(MultipartFile file,HttpServletRequest request,HttpServletResponse response
	,String type,String suffix,String fileType) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
	    if(!file.isEmpty()){
	        
	        /*
			 * 保存文件到本地
			 */
	    	InputStream is= file.getInputStream();
	        CommonsMultipartFile cf= (CommonsMultipartFile)file; 
	        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
	        File f = fi.getStoreLocation();
	        String filename = file.getOriginalFilename();    //得到上传时的文件名
	        FileUtil fileUtil = new FileUtil();
	        fileUtil.saveFileToTemp(is,filename,"");
	        //
	       
	        //
	      
	        /*
			 * 文件上传至文件服务器
			 */
	        String saveFilePath = fileUtil.getFilePath();
			File localFile = new File(saveFilePath);
			if(saveFilePath == null){
				
			}
			
				
				response.setCharacterEncoding("utf8");
				
				String jsonStr = "";
				
				HttpPostUtil u = new HttpPostUtil("http");
				
				u.addFileParameter("fileData", localFile);  
				u.addTextParameter("ft", type);  
				u.addTextParameter("lt", suffix);  
		        
				    byte[] b = u.send();
				
				    jsonStr = new String(b);  
				    System.out.println(jsonStr);
				    String result[] = jsonStr.split(":");
			        jsonStr = jsonStr.replace("\r", "").replace("\n", "");

			        
			       String status =result[0];
//			       System.out.println(result[2]);
					if("10000".equals(status)){
						
						resultMap.put("uploadFileURL",result[1]);
						resultMap.put("size", result[2]);
						resultMap.put("status", "10000");
						resultMap.put("originName", filename);
						
						
						
					}else{
						
						throw new Exception("uploadFile Error,errorInfo:文件上传失败");
					}
					
		        
			
			if (localFile.exists()) {
				localFile.delete();
			}
	       
	    }    
	    return resultMap;
	       
	}
}
