package com.zzmj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDateUtil {

	/**
	 * 格式化当前日期 2019-06-08 13:14:56
	 * 
	 * @param date
	 * @return
	 */
	public static String dataFormat(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = format.format(date);
		return result;
	}

}
