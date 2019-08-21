package com.zzmj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatDateUtil {

	/**
	 * 格式化当前日期 2019-06-08 13:14:56
	 * 将日期转换为String类型的字符串
	 * @param date
	 * @return
	 */
	public static String dataFormat(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = format.format(date);
		return result;
	}
	
	public static String dataFormattt(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		String result = format.format(date);
		return result;
	}
	
	/**
	 * 将String类型的字符串转换为日期格式
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date dataFormatt(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date result=format.parse(date);
        return result;
    }
	
	//将时间戳转换为Date类型的日期
	public static Date stampToDate(long time) {
        Date date=new Date(time);
		return date;
	}
    /* //日期转换为时间戳 
     * @param yyyy/MM/dd  yyyy-MM-dd 随意定义
     * */
    public static long timeToStamp(String timers) {
        Date d = new Date();
        long timeStemp = 0;
     try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
            d = sf.parse(timers);// 日期转换为时间戳
        } catch (ParseException e) {
        // TODO Auto-generated catch block
             e.printStackTrace();
        }
        timeStemp = d.getTime();
        return timeStemp;
   }
    
	
	
	/* //时间戳转换成String日期 */
    public static String stampToTime(long stamp) {
        String sd = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sd = sdf.format(new Date(stamp)); // 时间戳转换日期
        return sd;
    }
     
    
    //往后推迟一个月
    public static String month(String time) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try{
            date = sdf.parse(time);//初始日期
        }catch(Exception e){

        }
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH,1);//在日历的月份上增加一个月
        String strDate = sdf.format(c.getTime());//的到你想要得1个月后的日期
        return strDate;
    }
    
    //往后推迟一年
    public static String year(String time) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try{
            date = sdf.parse(time);//初始日期
        }catch(Exception e){

        }
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH,12);//在日历的月份上增加12个月
        String strDate = sdf.format(c.getTime());//的到你想要得12个月后的日期
        return strDate;
    }
    

}
