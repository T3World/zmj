package com.zmj.microservice.common.redis.util;

import com.alibaba.fastjson.JSON;
import com.zmj.microservice.common.history.constant.DatabaseConstant;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.util.DbUtil;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author:         umr
* @date:           2019/3/30
*/
public final class RedisUtil {

    private static final Logger LOGGER = Logger.getLogger(RedisUtil.class);

    private static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");

    /**
     * 以天为单位,缓存数据到redis
     * 要求数据格式为json
     * Param:
     *      template:
     *      databaseName: 数据库名
     *      dataName: 存储的数据点名称
     *      dateStr: 日期 格式为数字yyyyMMdd
     * */
    public static void setInRedisByDay(StringRedisTemplate template, String dataSourceName, String dataName, String yyyyMMdd, String data){
        String key = formatRedisKey(dataSourceName, dataName);
        buildRedisIndex(template,key,yyyyMMdd);
        //只有在数据不为空,也不是今天的情况,才会向redis中缓存数据
        if (data != null && !ifToday(yyyyMMdd)){
            template.opsForHash().put(key,yyyyMMdd,data);
        }else {
            String indexKey = "index_" + key;
            template.opsForSet().remove(indexKey,yyyyMMdd);
        }
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
     * 从redis索引中查询符合条件的表名
     * */
    public static String[] selectTables(StringRedisTemplate template,String databaseName,Long startTime,Long endTime) throws ParseException {
        String key = "";
        HashMap<String,String> map = new HashMap<>();
        List<String> dateBetweenLong =DbUtil.getDateBetweenLong(startTime,endTime);
        Iterator<String> iterator = dateBetweenLong.iterator();
        while (iterator.hasNext()){
            String date = iterator.next();
            key = new StringBuffer(databaseName).append("_").append(date).toString();
            Set<String> members = template.opsForSet().members(key);
            Iterator<String> i = members.iterator();
            while (i.hasNext()){
                map.put(i.next(),"");
            }
        }
        Set<String> strings = map.keySet();
        String[] array = strings.toArray(new String[strings.size()]);
        LOGGER.info("tables size: "+array.length);
        for (String i:array) {
            LOGGER.info(i);
        }
        return array;
    }

    /**
     * 建立redis日期与表名的索引
     *  格式为
     * key:
     * (prefix)_{日期}:{集团}_{公司}_{工作面}_{日期}
     * list:
     * 表名1(,表名2)
     * */
    public static void doBuildRedisIndex(RedisTemplate template, String prefix, String[] tables) throws ParseException {
        Map<String, List> map = DbUtil.matchTableAndDate(tables);

        if (null == map)
            return;
        Set<String> x = map.keySet();
        String[] keys = x.toArray(new String[x.size()]);
        String key = "";
        String value = "";
        for (int i = 0;i<keys.length;i++){
            System.out.println(key);
            List list = map.get(keys[i]);
            Iterator iterator = list.iterator();
            while (iterator.hasNext()){
                key = new StringBuffer(prefix).append("_").append(iterator.next()).toString();
                value = new StringBuffer(DatabaseConstant.TABLE_NAME_PREFIX).append(keys[i]).toString();
                template.opsForSet().add(key,value);
            }
        }
    }

    /**
     * 用于判断之前有没有相关工作面的数据库索引信息
     * */
    public static boolean ifDbUsed(String[] dbNames , String dbName){
        for (int i = 0;i<dbNames.length;i++){
            LOGGER.info(JSON.toJSONString(dbNames));
            if(dbNames[i].equals(dbName))
                return true;
        }
        return false;
    }
    /**
     * 用于判断是不是今天
     * */
    public static boolean ifToday(String endTime){
        //先判断是不是当天,如果是,true,要求重新查询数据库建立索引
        String endstr = yyyyMMdd.format(new Date());
        if (endstr.equals(endTime))
            return true;
        return false;
    }
    /**
     * 用于判断是不是今天
     * */
    public static boolean ifToday(Long endTime){
        String end = yyyyMMdd.format(new Date(endTime));
        return ifToday(end);
    }

    /**
     * 用于将查询出的历史记录List转化为json格式字符串,存储到redis
     * */
    public static String toJson(List<CommonVO> list){
        if (ifListNull(list))
            return null;
        return JSON.toJSONString(list);
    }


    /**
     * 用于 递推 判断 List是否为空
     * */
    public static boolean ifListNull(List list){
        if (list == null||list.size()==0)
            return true;
        Iterator iterator = list.iterator();

        while(iterator.hasNext()){
            Object next = iterator.next();
            if (next!=null){
                if((next instanceof List?ifListNull((List) next):false)?false:true)
                    return false;
            }
        }
        return true;
    }
    private static boolean ifListNull(Object[] a){
        if (a == null)
            return true;

        int n = 0;
        for (int i = 0;i<a.length;i++){
            if (a[i] == null)
                n++;
        }
        if (n == a.length)
            return true;
        return false;
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

    private static boolean buildRedisIndex(StringRedisTemplate template, String key, String date){
        String indexKey = "index_" + key;
        Long flag = template.opsForSet().add(indexKey, date);
        return flag != 0;
    }

    /**
     * 清除数据库缓存的方法
     * */
    public static void flushdb(StringRedisTemplate template) {
        template.execute((RedisCallback<Object>) connection -> {
            connection.flushDb();
            return "ok";
        });
    }

}
