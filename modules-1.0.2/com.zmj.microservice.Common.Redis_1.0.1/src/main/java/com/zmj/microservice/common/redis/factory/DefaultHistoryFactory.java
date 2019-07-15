package com.zmj.microservice.common.redis.factory;

import com.zmj.microservice.common.mybatis.core.FickleDataSourceFactory;
import com.zmj.microservice.common.mybatis.core.SessionFactory;
import com.zmj.microservice.common.mybatis.service.HistoryService;
import com.zmj.microservice.common.mybatis.service.HistoryServiceImpl;
import com.zmj.microservice.common.redis.core.DefaultRedisIndexBuilder;
import com.zmj.microservice.common.redis.core.DefaultSessionFactory;
import com.zmj.microservice.common.redis.core.HistoryEnvironment;
import com.zmj.microservice.common.redis.core.RedisIndexBuilder;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @description:    java类作用描述
 * @author:         umr
 * @date:           2019/4/26
 */
public class DefaultHistoryFactory implements HistoryBeansFactory{

    private HistoryEnvironment environment;

    /**
     * 存储数据库表名索引
     */
    private StringRedisTemplate template1;
    /**
     * redisTemplate2 , 主缓存
     */
    private StringRedisTemplate template2;

    /**
     * dataSourceFactory
     */
    private FickleDataSourceFactory dataSourceFactory;

    /**
     * 用于获取mybatisSqlSession
     */
    private SessionFactory sessionFactory;

    /**
     * redisIndexBuilder
     */
    private RedisIndexBuilder redisIndexBuilder;

    /**
     * HistoryService
     */
    private HistoryService historyService;

    private boolean environmentOk ;


    public DefaultHistoryFactory(HistoryEnvironment environment) {
        this.environment = environment;
        this.template1 = environment.getTemplate1();
        this.template2 = environment.getTemplate2();
        this.dataSourceFactory = environment.getDataSourceFactory();
        this.environmentOk = environment.assertNotNull();
    }

    @Override
    public SessionFactory getSessionFactory() {
        assertOk();
        if (null == sessionFactory)
            this.sessionFactory = new DefaultSessionFactory(dataSourceFactory);
        return this.sessionFactory;
    }

    @Override
    public RedisIndexBuilder getRedisIndexBuilder() {
        if (null == this.redisIndexBuilder)
            this.redisIndexBuilder = new DefaultRedisIndexBuilder(template1, getSessionFactory());
        return this.redisIndexBuilder;
    }

    @Override
    public HistoryService getHistoryService() {
        if (null == this.historyService)
            this.historyService = new HistoryServiceImpl(template1, template2,getRedisIndexBuilder(),getSessionFactory());
        return this.historyService;
    }

    /** 确保不接受null
       不返回null
     */
    private void assertOk(){
        if (!environmentOk)
            throw new IllegalArgumentException("environment's property can not be null!");
    }

}
