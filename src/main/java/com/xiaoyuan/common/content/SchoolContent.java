package com.xiaoyuan.common.content;

import java.util.LinkedHashMap;
import java.util.Map;

public class SchoolContent {
	private static  SchoolContent school=null;
	public static Map<String,String> schoolMap = new LinkedHashMap<String, String>();
    private SchoolContent(){
	   
   }
   public static  SchoolContent getInstance(){
	   if(school==null){
	     school = new SchoolContent();
	   }
	   return school;
   }
}
