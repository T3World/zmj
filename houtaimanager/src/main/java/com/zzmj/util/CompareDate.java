package com.zzmj.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompareDate {
	public static int compare(String date1, String date2) {
		int result = 0;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {

			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				result = 1;

			} else if (dt1.getTime() < dt2.getTime()) {
				result = -1;
			} else {
				result = 0;
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}
}
