package com.xiaoyuan.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.xiaoyuan.common.util.DateUtil;

public class DateTest {

	public static void main(String[] args) throws ParseException {
		/*// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();   
		calendar.add(Calendar.DATE, -1);    //得到前一天   
		calendar.add(Calendar.MONTH, -6);    //得到前一个月   
		int year = calendar.get(Calendar.YEAR);   
		int month = calendar.get(Calendar.MONTH)+1;  
		System.out.println(year);
		System.out.println(month);
		String strDate = "2012-02"; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
		Calendar ca = new GregorianCalendar(); 
		Date date1 = sdf.parse(strDate); 
		ca.setTime(date1); //放入你的日期 
		System.out.println("天数为=" + ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		//方法2
		System.out.println("天数为=" + new Date(2007,02,0).getDate());*/
		System.out.println(DateUtil.getDate(-5));
		System.out.println(DateUtil.getMonthDays("2016-2", "yyyy-MM"));
	    for(int i=0;i>=-6;i--){
	    	String d = DateUtil.getDate(i);
	    	String beginDate = d+"-01";
	    	
	    	String endDate = d+"-"+DateUtil.getMonthDays(d, "yyyy-MM");
	    	System.out.println("开始日期："+beginDate+";结束日期："+endDate);
	    }
	}
	

}
