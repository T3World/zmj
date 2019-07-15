package com.zmj.microservice.shearersystem.config;

import com.zmj.microservice.common.redis.core.RedisIndexBuilder;
import com.zmj.microservice.common.redis.util.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @description:    定时任务,专用
 * @author:         umr
 * @date:           2019/4/28
 */

@Component
@Configuration
@EnableScheduling
public class Mission {

    private static final Logger LOGGER = Logger.getLogger(Mission.class);

    @Autowired
    public Mission(@Qualifier("stringRedisTemplate2") StringRedisTemplate template2, RedisIndexBuilder redisIndexBuilder) {
        this.template2 = template2;
        this.redisIndexBuilder = redisIndexBuilder;
    }

    private RedisIndexBuilder redisIndexBuilder;

    private StringRedisTemplate template2;

    @Scheduled(cron = "0 0 0 * * ?")
    private void autoBuildRedisTableIndex(){
        LOGGER.info("定时刷新RedisTableIndex");
        try {
            redisIndexBuilder.buildIndex();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }
    /**
     * 方法实现说明

     * @return
     * @exception
     * @date        2019/5/15 14:40
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    private void autoFlushRedisDb(){
        try {
            RedisUtil.flushdb(template2);
            LOGGER.info("dang dang dang flush db2 dang dang dang!");
        }catch (Throwable e){
            LOGGER.error("redis db2 flush error!",e);
        }
    }
}
