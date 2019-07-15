package com.zmj.microservice.common.redis.core;

import com.zmj.microservice.common.mybatis.core.FickleDataSourceFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.validation.constraints.NotNull;

/**
 * @description:    java类作用描述
 * @author:         umr
 * @date:           2019/4/25
 */
public class HistoryEnvironment {
    /**
     * redisTemplate1 , 用于数据库表名存储查询
     */
    @NotNull
    private StringRedisTemplate template1;
    /**
     * redisTemplate2 , 主缓存
     */
    @NotNull
    private StringRedisTemplate template2;

    /**
     * dataSourceFactory
     */
    @NotNull
    private FickleDataSourceFactory dataSourceFactory;


    public HistoryEnvironment(StringRedisTemplate template1, StringRedisTemplate template2, FickleDataSourceFactory dataSourceFactory) {
        this.template1 = template1;
        this.template2 = template2;
        this.dataSourceFactory = dataSourceFactory;
    }

    public StringRedisTemplate getTemplate1() {
        return template1;
    }

    public void setTemplate1(StringRedisTemplate template1) {
        this.template1 = template1;
    }

    public StringRedisTemplate getTemplate2() {
        return template2;
    }

    public void setTemplate2(StringRedisTemplate template2) {
        this.template2 = template2;
    }

    public FickleDataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    public void setDataSourceFactory(FickleDataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    public boolean assertNotNull(){
        if (null == this.template1||null == this.template2||null == this.dataSourceFactory)
            return false;
        return true;
    }
}
