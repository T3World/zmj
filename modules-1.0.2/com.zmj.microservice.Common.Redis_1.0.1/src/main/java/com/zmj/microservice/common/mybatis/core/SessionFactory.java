package com.zmj.microservice.common.mybatis.core;

import com.zmj.microservice.common.mybatis.mapper.HistoryMapper;
import org.apache.ibatis.session.SqlSession;

/**
* @description:    java类作用描述
* @author:         umr
* @date:           2019/4/26
*/
public interface SessionFactory {

    /**
     * 基于默认配置获取数据库连接
     */
    default SqlSession openSession(String database) throws Exception {
        return openSession(database,new Class[]{HistoryMapper.class});
    }

    /**
     * 基于默认配置获取数据库连接
     */
    default SqlSession openSession(String database, Class[] mappers) throws Exception {
        return openSession(database,mappers,null);
    }

    /**
     * 可扩展,获取数据库连接
     */
    SqlSession openSession(String database, Class[] mappers, String[] xmlMappers) throws Exception;
}
