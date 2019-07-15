package com.zmj.microservice.filterstation.config;

import com.zmj.microservice.common.redis.core.RedisIndexBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
    private RedisIndexBuilder redisIndexBuilder;

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
}
