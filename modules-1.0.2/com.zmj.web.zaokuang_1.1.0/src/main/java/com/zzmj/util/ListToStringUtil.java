package com.zzmj.util;

import java.util.List;

public class ListToStringUtil {
	/**
	 * 将list转换为字符串 并且加入一些分隔符  注意：该list泛型为String类型
	 * @param list
	 * @param separator
	 * @return
	 */
  public static String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
}
