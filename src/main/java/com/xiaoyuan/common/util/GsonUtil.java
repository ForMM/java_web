package com.xiaoyuan.common.util;
import com.google.gson.Gson;
//*****************************************************************************
/**
 * <p>Title:GsonUtil</p>
 * <p>Description:gson/p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: \</p>
 * @author 汤清
 * @version v1.0 Oct 3, 2013
 */
//*****************************************************************************
public class GsonUtil {

	//*************************************************************************
	/** 
	* 将类转换成json语句
	* @param obj
	* @return  
	*/
	//*************************************************************************
	public static String objectToJson(Object obj)
	{
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;
	}
	
	//*************************************************************************
	/** 
	* 将json数据转化成类
	* @param json
	* @param c
	* @return  
	*/
	//*************************************************************************
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object jsonToObject(String json,Class c)
	{
		Gson gson = new Gson();
		Object object = gson.fromJson(json,c);
		return object;
	}
}
