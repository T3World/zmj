package com.zmj.config;

import com.zmj.microservice.common.mybatis.core.FickleDataSourceFactory;
import com.zmj.microservice.common.mybatis.core.SessionFactory;
import com.zmj.microservice.common.mybatis.service.HistoryService;
import com.zmj.microservice.common.redis.core.HistoryEnvironment;
import com.zmj.microservice.common.redis.core.RedisIndexBuilder;
import com.zmj.microservice.common.redis.factory.DefaultHistoryFactory;
import com.zmj.microservice.common.redis.factory.HistoryBeansFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
* @description:    spring beans
* @author:         umr
* @date:           2019/4/28
*/
@Configuration
public class RedisConfig {

    @Bean(name = "stringRedisTemplate0")
    public StringRedisTemplate stringRedisTemplate0(){
        return getstringRedisTemplate(0);
    }

    @Bean(name = "stringRedisTemplate1")
    public StringRedisTemplate stringRedisTemplate1(){
        return getstringRedisTemplate(1);
    }

    @Bean(name = "stringRedisTemplate2")
    public StringRedisTemplate stringRedisTemplate2(){
        return getstringRedisTemplate(2);
    }

    @Bean
    public HistoryBeansFactory getHistoryBeansFactory(@Qualifier("stringRedisTemplate1") StringRedisTemplate template1,
                                                      @Qualifier("stringRedisTemplate2") StringRedisTemplate template2,
                                                      FickleDataSourceFactory dataSourceFactory){
        HistoryEnvironment historyEnvironment = new HistoryEnvironment(template1, template2, dataSourceFactory);
        return new DefaultHistoryFactory(historyEnvironment);
    }

    @Bean
    public RedisIndexBuilder getRedisIndexBuilder(HistoryBeansFactory historyBeansFactory){
        return historyBeansFactory.getRedisIndexBuilder();
    }
    @Bean
    public SessionFactory getSessionFactory(HistoryBeansFactory historyBeansFactory){
        return historyBeansFactory.getSessionFactory();
    }

    @Bean
    public HistoryService getHistoryService(HistoryBeansFactory historyBeansFactory){
        return historyBeansFactory.getHistoryService();
    }

    private StringRedisTemplate getstringRedisTemplate(int selectIndex){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("127.0.0.1", 6379);
        redisStandaloneConfiguration.setDatabase(selectIndex);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        //初始化实例之后
        factory.afterPropertiesSet();
        return new StringRedisTemplate(factory);
    }
}
