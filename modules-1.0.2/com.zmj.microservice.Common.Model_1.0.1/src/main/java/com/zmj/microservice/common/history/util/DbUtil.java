package com.zmj.microservice.common.history.util;

import com.zmj.microservice.common.history.constant.DatabaseConstant;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;
/**
* @author:         umr
* @date:           2019/3/30
*/
public class DbUtil {
    private static final Logger logger = Logger.getLogger("DBUtil");

    /** 解析theme 返回map中,key-value:"dbName"-数据库名;"dataValue"-查询条件(表中DataName的取值)*/
    public static String parseTheme(String theme){
        String[] split = checkTheme(theme);
        if (split == null)
            return null;

        StringBuffer dataNameBuffer = new StringBuffer(DatabaseConstant.DATABASE_NAME_PREFIX);
        String dbName ;

        for (int i = DatabaseConstant.THEME_DATABASE_BEGIN_INDEX; i <= DatabaseConstant.THEME_DATABASE_END_INDEX; i++){
            dataNameBuffer.append("_").append(split[i]);
        }
        dbName= dataNameBuffer.toString();

        return dbName;
    }

    /**
     * 根据 dataName常量和 n 值合成 dataName作为数据库查询参数
     * */
    public static String parseDateName(String dataName, Integer n){
        String result = null;
        String[] split = dataName.split("/");

        StringBuffer dvBuffer = new StringBuffer(split[0]);

        if (n == null){
            for(int i = DatabaseConstant.THEME_DATANAME_BEGIN_INDEX+1; i < split.length; i++){
                dvBuffer.append(".").append(split[i]);
            }
            result = dvBuffer.toString();
        }else {
            if (split.length-DatabaseConstant.THEME_DATANAME_BEGIN_INDEX<2)
                return null;
            dvBuffer.append(".").append(split[DatabaseConstant.THEME_DATANAME_BEGIN_INDEX+1])
                    .append("[").append(n).append("]");
            for (int i = DatabaseConstant.THEME_DATANAME_BEGIN_INDEX+2; i<split.length; i++){
                dvBuffer.append(".").append(split[i]);
            }
            result = dvBuffer.toString();
        }
        return result;
    }


    /**
     * 将时间格式化为yyyy-MM-dd HH:mm:ss,
     * 参数为时间戳
     * isss 表示是否含有毫秒值
     * */
    public static String formatTime(long time){
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    /**
     * 将时间格式化为yyyy-MM-dd HH:mm:ss SSS ,
     * 参数格式yyyyMMddHHmmss
     * isss 表示是否含有毫秒值
     * */
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
    public static String xuyimiao(String endTime,boolean sss){
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
                .append(":").append(ss);
        if (sss)
            buffer.append(" ").append(ms);
        return buffer.toString();
    }
    public static String xuyimiao(String endTime){
        return xuyimiao(endTime,true);
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
        if (0 == longs.length)
            return null;

        int lastIndex = longs.length - 1;

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = format.format(date);
        for (int i = 0;i < lastIndex;i++){
            List<String> list = getDateBetweenLong(String.valueOf(longs[i]), String.valueOf(longs[i+1]));
            map.put(String.valueOf(longs[i]),list);
        }
        map.put(String.valueOf(longs[lastIndex]),getDateBetweenLong(String.valueOf(longs[lastIndex]), now));
        return map;
    }


    /** 筛选符合条件的表名并排序
     *  如果传入 null 或 没有符合田间的表名,返回长度为0的数组
     * */
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
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String s1 = format.format(new Date(p1));
        String s2 = format.format(new Date(p2));
        return getDateBetweenLong(s1,s2);
    }

    /**
     *  Param:
     *      startTime:yyyyMMdd
     *      endTime:yyyyMMdd
     * */
    public static List<String> getDateBetweenLong(String startTime,String endTime) throws ParseException {
        String s1 = startTime.substring(0,8);
        String s2 = endTime.substring(0,8);

        List<String> list = new LinkedList<>();

        list.add(s1);
        while (!s1.equals(s2)){
            s1 = getNextDay(s1);
            list.add(s1);
        }
        return list;
    }

    public static List<CommonVO> sortListByTime(List<CommonVO> list) throws ParseException {
        long s = System.currentTimeMillis();
        int size = list.size();
        CommonVO[] arr = list.toArray(new CommonVO[size]);

        long[] model = new long[size];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        long x;
        CommonVO y;
        List<CommonVO> result = new LinkedList<>();
        for (int i = 0;i<size;i++){
            model[i] = format.parse(arr[i].getTime()).getTime();
        }

        for (int m = size-1; m>0; m--){
            for (int i = 0;i<m;i++){
                if (model[i]>model[i+1]){
                    x = model[i];
                    y = arr[i];
                    model[i] = model[i+1];
                    arr[i] = arr[i+1];
                    model[i+1] = x;
                    arr[i+1] = y;
                }
            }
        }

        for(int i = 0;i<size;i++){
            result.add(arr[i]);
        }

        long l = System.currentTimeMillis();
        logger.info("sort cost : "+(l-s));
        return result;
    }



    /**
     *
     * @param day yyyyMMdd
     * */
    public static String getNextDay(String day) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date d = format.parse(day);
        calendar.setTime(d);
        calendar.add(Calendar.DAY_OF_YEAR,1);
        return format.format(calendar.getTime());
    }

    public static String getPreDay(String day) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date d = format.parse(day);
        calendar.setTime(d);
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        return format.format(calendar.getTime());
    }
}
