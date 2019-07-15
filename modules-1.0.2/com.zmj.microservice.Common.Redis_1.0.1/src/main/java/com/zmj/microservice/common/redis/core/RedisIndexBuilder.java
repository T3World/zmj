package com.zmj.microservice.common.redis.core;

import com.zmj.microservice.common.history.exception.MysqlAccessDeniedException;

/**
 * @description:    接口
 * @author:         umr
 * @date:           2019/4/25
 */
public interface RedisIndexBuilder {

    /**
     * redis 建立表名索引
     * @param dbNames 数据库名
     * @param flag 是否强制刷新,用于例如,查询时间为当日的情况
     * @exception Exception throw by redisTemplate
     */
    void buildIndex(boolean flag,String... dbNames)throws MysqlAccessDeniedException;


    /**
     * 作用:刷新索引
     */
    void buildIndex()throws MysqlAccessDeniedException;
    /**
    * @return      当前缓存的数据库数组
    * @date        2019/4/26 14:05
    */
    String[] getDatabaseName();
}
