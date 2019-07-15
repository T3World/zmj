package com.zmj.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

public class RedisUtil {

    private static final Logger logger = Logger.getLogger("RedisUtil");

    /**
     * 以天为单位,缓存数据到redis
     * 要求数据格式为json
     * Param:
     *      template:
     *      databaseName: 数据库名
     *      dataName: 存储的数据点名称
     *      dateStr: 日期 格式为数字yyyyMMdd
     * */
    public static void setInRedisByDay(StringRedisTemplate template, String dataSourceName, String dataName, String yyyyMMdd,String data){
        String key = formatRedisKey(dataSourceName, dataName);
        buildRedisIndex(template,key,yyyyMMdd);
        template.opsForHash().put(key,yyyyMMdd,data);
//        logger.info(new StringBuffer("key: ").append(key).append(" date: ").append(yyyyMMdd).append(" data: ").append(data).toString());
    }

    /**
     * 以天为单位,从redis中取出json格式的数据
     * 如果没有返回null
     * Param:
     *  template:
     *  databaseName: 数据库名
     *  dataName: 查询的数据名
     *  yyyyMMdd: 查询日期,格式遵照yyyyMMdd
     * */
    public static String getFromRedisByDay(StringRedisTemplate template, String dataSourceName, String dataName, String yyyyMMdd){
        String key = formatRedisKey(dataSourceName, dataName);
        boolean b = buildRedisIndex(template, key, yyyyMMdd);
        if (b)
            return null;
        String data = (String) template.opsForHash().get(key, yyyyMMdd);
//        logger.info(new StringBuffer("key: ").append(key).append(" flag ").append(b).append(" data: ").append(data).toString());
        return data;
    }

    /**
     * 用于select 2
     * 根据传入的集团/公司/工作面 和 数据名称
     * 形成redis中存储使用的key
     * 格式为{集团}_{公司}_{工作面}_{字段值}
     * */
    private static String formatRedisKey(String dataSourceName,String dataName){
        String[] split = dataSourceName.split("/");
        return new StringBuffer(split[0]).append("_").append(split[1])
                .append("_").append(split[2])
                .append("_").append(dataName).toString();
    }

    public static boolean buildRedisIndex(StringRedisTemplate template,String key,String date){
        String indexKey = new StringBuffer("index_").append(key).toString();
        Long flag = template.opsForSet().add(indexKey, date);
        return flag == 0?false:true;
    }
    /**
     * 用于判断之前有没有相关工作面的数据库索引信息
     * */
    public static boolean ifDbUsed(String[] dbNames , String dbName,String endTime){
        for (int i = 0;i<dbNames.length;i++){
            logger.info(JSON.toJSONString(dbNames));
            if(dbNames[i].equals(dbName))
                return true;
        }
        return false;
    }
    /**
     * 用于判断是不是今天
     * */
    public static boolean ifToday(String endTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        //先判断是不是当天,如果是,true,要求重新查询数据库建立索引
        String endstr = format.format(new Date());
        if (endstr.equals(endTime))
            return true;
        return false;
    }
}
