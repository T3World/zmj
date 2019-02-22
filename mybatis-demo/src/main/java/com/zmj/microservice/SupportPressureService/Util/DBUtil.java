package com.zmj.microservice.SupportPressureService.Util;

import com.alibaba.fastjson.JSON;
import com.zmj.microservice.SupportPressureService.constant.DatabaseConstant;
import com.zmj.microservice.SupportPressureService.pojo.DO.HistoryDO;

import java.util.List;

public class DBUtil {

    /* 解析theme 返回map中,key-value:"dbName"-数据库名;"dataValue"-查询条件(表中DataName的取值)*/
    public static String[] parseTheme(String theme,Integer n){
        String[] split = checkTheme(theme);
        if (split == null)
            return null;

        String[] result = new String[2];
        StringBuffer dataNameBuffer = new StringBuffer(DatabaseConstant.DATABASE_NAME_PREFIX);
        StringBuffer dvBuffer = new StringBuffer(split[DatabaseConstant.THEME_DATANAME_BEGIN_INDEX]);
        String dbName ;
        String dataName ;

        for (int i = DatabaseConstant.THEME_DATABASE_BEGIN_INDEX;i <= DatabaseConstant.THEME_DATABASE_END_INDEX;i++){
            dataNameBuffer.append("_").append(split[i]);
        }
        dbName= dataNameBuffer.toString();

        if (n == null){
            for(int i = DatabaseConstant.THEME_DATANAME_BEGIN_INDEX+1; i <= split.length;i++){
                dvBuffer.append(".").append(split[i]);
            }
            dataName = dvBuffer.toString();
        }else {
            if (split.length-DatabaseConstant.THEME_DATANAME_BEGIN_INDEX<2)
                return null;
            dvBuffer.append(".").append(split[DatabaseConstant.THEME_DATANAME_BEGIN_INDEX+1])
                    .append("[").append(n).append("]");
            for (int i = DatabaseConstant.THEME_DATANAME_BEGIN_INDEX+2;i<split.length;i++){
                dvBuffer.append(".").append(split[i]);
            }

            dataName = dvBuffer.toString();
        }
        result[0] = dbName;
        result[1] = dataName;

        return result;
    }

    public static String toJson(List<HistoryDO> list){
        return JSON.toJSONString(list);
    }


    public static String formatTime(String time,boolean isss){
        String YY = "2020";
        String MM = "12";
        String DD = "31";
        String hh = "00";
        String mm = "00";
        String ss = "00";
        String ms = "000";

        int l = time.length();

        if (l>3)
            YY = time.substring(0, 4);
        if (l>5)
            MM = time.substring(4, 6);
        if (l>7)
            DD = time.substring(6, 8);
        if (l>9)
            hh = time.substring(8, 10);
        if (l>11)
            mm = time.substring(10, 12);
        if (l>13)
            ss = time.substring(12, 14);
        if (l>14)
            ms = time.substring(14);
        StringBuffer buffer = new StringBuffer(YY)
                .append("-").append(MM)
                .append("-").append(DD)
                .append(" ").append(hh)
                .append(":").append(mm)
                .append(":").append(ss);
        if (isss)
            buffer.append(" ").append(ms);
        return buffer.toString();
    }

    public static String xuyimiao(String endTime){
        String YY = "2020";
        String MM = "12";
        String DD = "31";
        String hh = "23";
        String mm = "59";
        String ss = "59";

        int l = endTime.length();

        if (l>3)
            YY = endTime.substring(0, 4);
        if (l>5)
            MM = endTime.substring(4, 6);
        if (l>7)
            DD = endTime.substring(6, 8);

        StringBuffer buffer = new StringBuffer(YY)
                .append(MM)
                .append(DD)
                .append(hh)
                .append(mm)
                .append(ss);
        String s1 = buffer.toString();
        System.out.println("endTime: "+s1);
        return s1;
    }

    /* 检查theme长度,防止参数越界 */
    private static String[] checkTheme(String theme){
        String[] split = theme.split("/");
        if (split.length<DatabaseConstant.THEME_MIN_LENGTH)
            return null;
        return split;
    }

    public static String getRedisIndexKey(String databaseName,String dataValue){
        return new StringBuffer("index-").append(databaseName).append("-").append(dataValue).toString();
    }




}
