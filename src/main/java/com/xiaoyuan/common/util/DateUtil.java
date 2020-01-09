package com.xiaoyuan.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
  /**
   * 获取几个月前或几个月后的日期
   * i>0为i个月后的日期
   * i<0为i个月前的日期
   * */
	public static String getDate(int i){
		Calendar calendar = Calendar.getInstance();   
		calendar.add(Calendar.MONTH, i);    //得到前一个月   
		String year = calendar.get(Calendar.YEAR)+"";   
		String month = (calendar.get(Calendar.MONTH)+1)+"";  
		return year+"-"+month;
	}
	public static int getMonthDays(String strDate,String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format); 
		Calendar ca = new GregorianCalendar(); 
		Date date1 = sdf.parse(strDate); 
		ca.setTime(date1); //放入你的日期 
		return ca.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
}
