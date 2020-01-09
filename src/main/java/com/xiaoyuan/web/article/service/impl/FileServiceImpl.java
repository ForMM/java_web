package com.xiaoyuan.web.article.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.oss.util.OSSUtil;
import com.xiaoyuan.common.content.Status;
import com.xiaoyuan.common.log.LogTool;
import com.xiaoyuan.common.util.FileUtil;
import com.xiaoyuan.common.util.Global;
import com.xiaoyuan.common.util.Help;
import com.xiaoyuan.common.util.Result;
import com.xiaoyuan.web.article.service.FileService;


@Service
public class FileServiceImpl implements FileService {

	LogTool log=LogTool.getInstance(FileServiceImpl.class);
	
	public Result uploadOSSFile(MultipartFile file,String remotePath)
			throws Exception {
		// TODO Auto-generated method stub
		
		Result result = new Result();
		String filename = file.getOriginalFilename();
		if(Help.isNull(filename)){
			   result.setStatus(Status.file_empty_status);
			   result.setInfo(Status.file_empty_info);
			   return result;
		   }
		String suffixStr = filename.substring(filename.lastIndexOf("."));
		
		if(!".jpg".equalsIgnoreCase(suffixStr)&&!".jpeg".equalsIgnoreCase(suffixStr)&&!".png".equalsIgnoreCase(suffixStr)&&!".gif".equalsIgnoreCase(suffixStr)){
			log.info("图片文件格式错误");
			result.setStatus(Status.img_type_error_status);
			result.setInfo(Status.img_type_error_info);
		}else{
			
			String tempPath="";
			String filePath="";
			try {
				tempPath = uploadFile(file, filename);
				filePath = OSSUtil.uploadFile(tempPath, remotePath);
				result.setStatus(Status.success_status);
		        result.setInfo(Status.success_info);
				Map<String,Object> data = new HashMap<String, Object>();
				data.put("filePath", filePath);
				result.setData(data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.setStatus(Status.upload_error_status);
				result.setInfo(Status.upload_error_info);
				return result;
			}
	    	log.info("上传文件成功");
	    
		}	
		
		return result;
	}

	@Override
	public void downLoadFile(String filename, String filetype,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	private String uploadFile(MultipartFile file,String filename) throws Exception{
		String filePath="";
		InputStream is= file.getInputStream();
        CommonsMultipartFile cf= (CommonsMultipartFile)file; 
        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        File f = fi.getStoreLocation();
        FileUtil fileUtil = new FileUtil();
        /*if(FinVal.FILE_TYPE_HEAD.equals(fileType)){
        fileUtil.saveFileToTemp(is,filename,Global.HEAD_IMG_PATH);
        }else if(FinVal.FILE_TYPE_BP.equals(fileType)){
        fileUtil.saveFileToTemp(is,filename,Global.BP_FILE_PATH);	
        }else if(FinVal.FILE_TYPE_INDUSTRY.equals(fileType)){
        fileUtil.saveFileToTemp(is,filename,Global.INDUSTRY_IMG_PATH);	
        }else if(FinVal.FILE_TYPE_LICENSE.equals(fileType)){
        fileUtil.saveFileToTemp(is,filename,Global.LICENSE_FILE_PATH);	
        }else if(FinVal.FILE_TYPE_IDENTIFY.equals(fileType)){
        fileUtil.saveFileToTemp(is,filename,Global.IDENTIFY_FILE_PATH);	
        }else if(FinVal.FILE_TYPE_BPIMG.equals(fileType)){
        fileUtil.saveFileToTemp(is,filename,Global.BP_IMG_PATH);	
        }else{
        	throw new Exception("文件类型错误！！");
        }*/
        fileUtil.saveFileToTemp(is,filename,Global.TEMP_FILE_PATH);
       // filePath=Global.DOWNLOAD_URL+"?filename="+fileUtil.getLastFileName()+"&filetype="+fileType;
        filePath= fileUtil.getFilePath();
        return filePath;
	}
}
