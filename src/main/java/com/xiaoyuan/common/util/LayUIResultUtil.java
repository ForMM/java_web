package com.xiaoyuan.common.util;

import java.util.HashMap;
import java.util.Map;

import com.xiaoyuan.common.content.Status;
import com.xiaoyuan.web.article.dto.LayUploadResult;

public class LayUIResultUtil {

	public static LayUploadResult convertResult(Result result){
		 LayUploadResult layUploadResult = new LayUploadResult(); 
		 if(result.getStatus() == Status.success_status){
			 layUploadResult.setCode(0);
			 Map<String, Object> data = new HashMap<String, Object>();
			 Map<String, Object> ossData = (Map<String, Object>) result.getData();
			 data.put("src",ossData.get("filePath"));
			layUploadResult.setData(data );
		 }else{
			 layUploadResult.setCode(-1);
			 layUploadResult.setMsg(result.getInfo());
		 }
		 return layUploadResult;
	}
}
