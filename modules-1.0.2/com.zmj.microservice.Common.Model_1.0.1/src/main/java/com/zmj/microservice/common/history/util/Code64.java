package com.zmj.microservice.common.history.util;
/**
 * 将long类型数字转换成64进制字符串,
 * long类型数字大小不能超过64的八次方(即281,474,976,710,656)
 * 64进制表示法参考Base64
 * */
public class Code64 {
    private static final long L1 = 64L;
    /**
     * 4,096
     */
    private static final long L2 = L1*64L;
    /**
     * 262,144
     */
    private static final long L3 = L2*64L;
    /**
     * 16,777,216
     */
    private static final long L4 = L3*64L;
    /**
     * 1,073,741,824
     */
    private static final long L5 = L4*64L;
    /**
     * 68,719,476,736
     */
    private static final long L6 = L5*64L;
    /**
     * 4,398,046,511,104
     */
    private static final long L7 = L6*64L;
    /**
     * 281,474,976,710,656
     */
    private static final long L8 = L7*64L;

    private static final char[] base64= new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};

}
