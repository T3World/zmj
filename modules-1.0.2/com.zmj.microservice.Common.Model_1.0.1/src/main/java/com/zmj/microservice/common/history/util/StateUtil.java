package com.zmj.microservice.common.history.util;

import java.util.LinkedList;

/**
* @author:         umr
* @date:           2019/3/30
*/
public class StateUtil {

    /**
     * 比较ids 和 dataIds,如果dataIds 包含ids,返回true
     * 否则,返回false
     * */
    public static boolean compare(String id,String dataIds){
        boolean[] phat = phat(dataIds);
            if (!phat[parseId(id)])
                return false;
        return true;
    }

    public static String[] parseIds(String ids){
        int length = ids.length() / 2;
        String[] result = new String[length];
        LinkedList<String> list = new LinkedList<>();
        for(int i = 0; i <length;i++){
            String id = ids.substring(i * 2, i * 2 + 2);
            list.add(id);
        }
        return list.toArray(result);
    }

    private static int parseId(String id){
        int index = 29;
        switch (id){
            case "01":
                index = 0;
                break;
            case "02":
                index = 1;
                break;
            case "03":
                index = 2;
                break;
            case "04":
                index = 3;
                break;
            case "05":
                index = 4;
                break;
            case "06":
                index = 5;
                break;
            case "07":
                index = 6;
                break;
            case "08":
                index = 7;
                break;
            case "09":
                index = 8;
                break;
            case "0A":
                index = 9;
                break;
            case "0B":
                index = 10;
                break;
            case "0C":
                index = 11;
                break;
            case "0D":
                index = 12;
                break;
            case "0E":
                index = 13;
                break;
            case "0F":
                index = 14;
                break;
            case "10":
                index = 15;
                break;
            case "11":
                index = 16;
                break;
            case "12":
                index = 17;
                break;
            case "13":
                index = 18;
                break;
            case "14":
                index = 19;
                break;
            case "15":
                index = 20;
                break;
            case "16":
                index = 21;
                break;
            case "17":
                index = 22;
                break;
            case "18":
                index = 23;
                break;
            case "19":
                index = 24;
                break;
            case "1A":
                index = 25;
                break;
            case "1B":
                index = 26;
                break;
            case "1C":
                index = 27;
                break;
            case "1D":
                index = 28;
                break;
            default:
        }
        return index;
    }

    /**
     * 解析 stateIDs ,将十六进制 编号 转变为 布尔值数组
     * */
    private static boolean[] phat(String stateIds) {
        boolean[] chars = {false,false,false,false,false,false,false,false,
                false,false,false,false,false,false,false,false,false,
                false,false,false,false,false,false,false,false,false,
                false,false,false,false};
        for(int i = 0; i < (stateIds.length()/2);i++){
            String id = stateIds.substring(i * 2, i * 2 + 2);
            chars[parseId(id)] = true;
        }
        return chars;
    }
}
