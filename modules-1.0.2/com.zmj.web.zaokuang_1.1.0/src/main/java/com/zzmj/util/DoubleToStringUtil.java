package com.zzmj.util;

/**
 * double类型保留小数点后几位小数     该方法保留后五位
 * @author yangquan
 *
 */
public class DoubleToStringUtil {
	public static String doubleToString(double dd) {
		java.text.DecimalFormat df=new java.text.DecimalFormat("#.#####");
        String average=String.valueOf(df.format(dd));
        return average;
    }

}
