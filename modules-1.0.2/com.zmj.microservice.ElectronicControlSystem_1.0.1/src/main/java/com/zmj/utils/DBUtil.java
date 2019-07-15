package com.zmj.utils;

import com.alibaba.fastjson.JSON;
import com.zmj.constant.DatabaseConstant;
import com.zmj.pojo.entity.CommonValueEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class DBUtil {
    private static final Logger logger = Logger.getLogger("DBUtil");

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
            if(split.length==6){
                for(int i = DatabaseConstant.THEME_DATANAME_BEGIN_INDEX+1; i <= split.length;i++){
                    dvBuffer.append(".").append(split[i]);
                }
            }else{
                    dvBuffer.append(".").append(split[4]);
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

    /**
     * 用于将查询出的历史记录List转化为json格式字符串,存储到redis
     * */
    public static String toJson(List<CommonValueEntity> list){
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
        String ms = "999";
        int l = endTime.length();

        if (l>3)
            YY = endTime.substring(0, 4);
        if (l>5)
            MM = endTime.substring(4, 6);
        if (l>7)
            DD = endTime.substring(6, 8);

        StringBuffer buffer = new StringBuffer(YY)
                .append("-").append(MM)
                .append("-").append(DD)
                .append(" ").append(hh)
                .append(":").append(mm)
                .append(":").append(ss)
                .append(" ").append(ms);
        String s1 = buffer.toString();
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


    /**
     * 需要一个方法,解析表名,将每个表名匹配一个日期list,做一个map<String,List>
     * */
    public static Map<String,List> matchTableAndDate(String[] tables) throws ParseException {
        HashMap<String, List> map = new HashMap<>();
        long[] longs = doRegextablename(tables);
        //如果没有符合条件的表名,返回null
        if (longs.length==0)
            return null;

        int lastIndex = longs.length - 1;

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = format.format(date);
        for (int i = 0;i <lastIndex;i++){
            List<String> list = getDateBetweenLong(longs[i], longs[i+1]);
            map.put(String.valueOf(longs[i]),list);
        }
        map.put(String.valueOf(longs[lastIndex]),getDateBetweenLong(longs[lastIndex], Long.parseLong(now)));
        return map;
    }


    /** 筛选符合条件的表名并排序 */
    public static long[] doRegextablename(String[] tablenames){
        String pattern = "^datavalues_+?\\d*";

        List<String> list = new ArrayList<String>();
        for(int i = 0;i<tablenames.length;i++){
            if(Pattern.matches(pattern, tablenames[i])){
                list.add(tablenames[i]);
            }
        }
        String[] strings = new String[list.size()];
        Iterator<String> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()){
            strings[i] = iterator.next();
            i++;
        }
        return parseSort(strings);
    }

    /** 解析时间,转换格式,从字符串到long,截取前TIME_LENGTH位 */
    public static long parseTime(String str){
        return parseTime(str,DatabaseConstant.TIME_LENGTH);
    }

    public static long parseTime(String str,int timeLength){
        char[] chars = str.toCharArray();

        int min = Math.min(chars.length, timeLength);

        char[] copy = new char[timeLength];

        System.arraycopy(chars, 0, copy, 0, min);

        while (min<timeLength){
            copy[min] = 0;
            min++;
        }

        return Long.parseLong(new String(chars));
    }

    /** 将 String[] 数组提取末位成为long[]并排序 */
    private static long[] parseSort(String[] tablenames){
        //截取的位置
        int index = DatabaseConstant.TABLE_NAME_PREFIX.length();

        long[] ints = new long[tablenames.length];
        for(int i = 0;i<tablenames.length;i++){
            ints[i] = Long.parseLong(tablenames[i].substring(index));
        }
        Arrays.sort(ints);
        return ints;
    }

    /** 根据两个long日期范围获取其中的具体天数,例如201801011111,201801031111
     * 会得到20180101,20180102,20180103 */
    public static List<String> getDateBetweenLong(long p1,long p2) throws ParseException{

        String s1 = String.valueOf(p1);
        String s2 = String.valueOf(p2);

        s1 = s1.substring(0, 8);
        s2 = s2.substring(0, 8);


        p1 = Long.parseLong(s1);
        p2 = Long.parseLong(s2);

        List<String> list = new LinkedList<>();

        list.add(s1);
        while (!s1.equals(s2)){
            p1 = getNextDay(p1);
            s1 = String.valueOf(p1);
            list.add(s1);
        }
        return list;
    }

    /**
     *  Param:
     *      startTime:时间戳
     *      endTime:时间戳
     * */
    public static List<String> getDateBetweenLong(String startTime,String endTime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String rest;
        Long starts = new Long(startTime);  // 把开始时间戳 String 类型转换成 long
        Date sTime = new Date(starts);  // 开始时间 把long类型的时间戳转换成data日期格式数据
        rest = simpleDateFormat.format(sTime); // 开始时间 把data类型日期格式化为String类型字符串
        Long p1 = Long.parseLong(rest);  // 开始时间

        String endt;
        Long ends = new Long(endTime); // 把结束时间戳 String 类型转换成 long
        Date eTime = new Date(ends);    // 结束时间 把long类型的时间戳转换成data日期格式数据
        endt = simpleDateFormat.format(eTime);  // 结束 时间 把data类型日期格式化为String类型字符串
        Long p2 = Long.parseLong(endt);  // 结束时间

        return  getDateBetweenLong(p1,p2);
    }

    private static long getNextDay(long now) throws ParseException {
        long nextDay = 0L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date d = sdf.parse(String.valueOf(now));
        calendar.setTime(d);
        calendar.add(Calendar.DAY_OF_YEAR,1);
        nextDay = Long.parseLong(sdf.format(calendar.getTime()));
        return nextDay;
    }
}
