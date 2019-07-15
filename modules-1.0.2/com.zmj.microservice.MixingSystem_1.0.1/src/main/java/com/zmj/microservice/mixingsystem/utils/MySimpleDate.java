package com.zmj.microservice.mixingsystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MySimpleDate {
    /**
     * 日期转换成时间戳
     * @param s 日期
     * @return 返回值
     * @throws ParseException 异常
     */
    public   String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = simpleDateFormat.parse(s);
        Long ts = date.getTime();
        res = String.valueOf(ts);
        return  res;
    }
}
