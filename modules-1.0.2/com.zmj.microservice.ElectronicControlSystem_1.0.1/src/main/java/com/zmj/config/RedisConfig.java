package com.zmj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

    @Bean(name = "stringRedisTemplate0")
    public StringRedisTemplate stringRedisTemplate0(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("127.0.0.1", 6379);
        redisStandaloneConfiguration.setDatabase(0);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        //初始化实例之后
        factory.afterPropertiesSet();
        return new StringRedisTemplate(factory);
    }

    @Bean(name = "stringRedisTemplate1")
    public StringRedisTemplate stringRedisTemplate1(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("127.0.0.1", 6379);
        redisStandaloneConfiguration.setDatabase(1);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        //初始化实例之后
        factory.afterPropertiesSet();
        return new StringRedisTemplate(factory);
    }

    @Bean(name = "stringRedisTemplate2")
    public StringRedisTemplate stringRedisTemplate2(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("127.0.0.1", 6379);
        redisStandaloneConfiguration.setDatabase(2);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        //初始化实例之后
        factory.afterPropertiesSet();
        return new StringRedisTemplate(factory);
    }


}
