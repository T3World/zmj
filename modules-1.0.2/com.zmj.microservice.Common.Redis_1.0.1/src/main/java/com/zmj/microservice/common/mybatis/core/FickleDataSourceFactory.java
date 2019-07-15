package com.zmj.microservice.common.mybatis.core;

import javax.sql.DataSource;

/**
 * @description:    自定义的数据源工厂
 * @author:         umr
 * @date:           2019/4/26
 */
public interface FickleDataSourceFactory {

    /**
     * @param database 数据库名
     * @return 数据源
     * @date        2019/4/26 11:44
     */
    DataSource getDataSource(String database);
}
