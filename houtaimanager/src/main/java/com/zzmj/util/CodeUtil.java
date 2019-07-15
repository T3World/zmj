package com.zzmj.util;

import java.util.UUID;

public class CodeUtil {
	public static String createUuid36() {
		return UUID.randomUUID().toString().trim();
	}

	public static String createUuid32() {
		return UUID.randomUUID().toString().replace("-", "").trim();
	}

	public static String createStrNum(int n) {
		String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		String str2 = "";
		int len = str1.length() - 1;
		double r;
		for (int i = 0; i < n; i++) {
			r = (Math.random()) * len;
			str2 = str2 + str1.charAt((int) r);
		}
		return str2;
	}

	public static String createRandomNum() {
		String random = (int) ((Math.random() * 9 + 1) * 100000) + "";
		return random;
	}
}
