package com.example.mybatisdemo.Util;

import com.example.mybatisdemo.constant.DatabaseConstant;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.regex.Pattern;

public class DBUtil {

    /* 筛选符合查询时间范围的表名
    *   表名后缀没做处理,如果位数不一致,会导致查询范围出现问题!*/
    public static String[] matchTableByTime(@NotNull String[] tablenames,@NotNull String start,@NotNull String end){
        int s = parseTime(start);
        int e = parseTime(end);
        return doMatchingTableName(tablenames,s,e);
    }

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

    /* 解析时间,从字符串到int,截取前TIME_LENGTH位 */
    public static int parseTime(String str){
        char[] chars = str.toCharArray();

        int min = Math.min(chars.length, DatabaseConstant.TIME_LENGTH);

        char[] copy = new char[DatabaseConstant.TIME_LENGTH];

        System.arraycopy(chars, 0, copy, 0, min);

        while (min<DatabaseConstant.TIME_LENGTH){
            copy[min] = 0;
            min++;
        }

        return Integer.parseInt(new String(chars));
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

    public static String setSqlParam(String sql, String sqlParam,String param){
        sql.replaceAll(sqlParam,param);
        return sql;
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

    /* 筛选符合查询时间范围的表名 */
    private static String[] doMatchingTableName(String[] tablenames,int param1,int param2){
        int i = 0;
        int s = 0;
        int e = 0;
        int[] ints = doRegextablename(tablenames);

        int length = ints.length;

        while (i <= length && param1 > ints[i]){
            i++;
        }

        if(i>length)
            return null;

        s = i;

        while (i <= length && param2 >= ints[i]){
            i++;
        }

        if (i == length){
            e = i;
        }else {
            e = i-1;
        }

        String[] a = new String[e-s+1];

        for (int n = 0, m = s; n<a.length;n++,m++){
            a[n] = new StringBuffer(DatabaseConstant.TABLE_NAME_PREFIX).append(ints[m]).toString();
        }

        return a;
    }

    /*筛选符合条件的表名*/
    private static int[] doRegextablename(String[] tablenames){
        String pattern = "^datavalues_+?\\d*";

        List<String> list = new ArrayList<String>();
        for(int i = 0;i<tablenames.length;i++){
            if(Pattern.matches(pattern, tablenames[i]))
                list.add(tablenames[i]);
        }
        String[] strings = (String[]) list.toArray();

        return parseSort(strings);
    }

    /*将 String[] 数组提取末位成为int[]并排序*/
    private static int[] parseSort(String[] tablenames){
        int[] ints = new int[tablenames.length];
        for(int i = 0;i<tablenames.length;i++){
            ints[i] = Integer.parseInt(tablenames[i].substring(11));
        }
        Arrays.sort(ints);
        return ints;
    }
}
