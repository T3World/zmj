package com.zmj.microservice.common.redis.factory;

import com.zmj.microservice.common.mybatis.core.SessionFactory;
import com.zmj.microservice.common.mybatis.service.HistoryService;
import com.zmj.microservice.common.redis.core.HistoryEnvironment;
import com.zmj.microservice.common.redis.core.RedisIndexBuilder;

/**
* @description:    java类作用描述
* @author:         umr
* @date:           2019/4/26
*/
public interface HistoryBeansFactory {

    SessionFactory getSessionFactory();

    RedisIndexBuilder getRedisIndexBuilder();

    HistoryService getHistoryService();
}
