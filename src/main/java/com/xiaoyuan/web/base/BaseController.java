package com.xiaoyuan.web.base;

import java.io.IOException;
import java.io.PrintWriter;

import com.xiaoyuan.common.content.SysContent;

public class BaseController {
	   
	   public PrintWriter getPrintWriter(){
		   PrintWriter pw=null;
		   try {
			pw=SysContent.getResponse().getWriter();
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
		   return pw;
	   }
	}
