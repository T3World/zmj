package com.zmj.microservice.common.redis.core;

import com.zmj.microservice.common.history.util.DbUtil;
import com.zmj.microservice.common.mybatis.core.FickleDataSourceFactory;
import com.zmj.microservice.common.mybatis.core.SessionFactory;
import com.zmj.microservice.common.mybatis.mapper.HistoryMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.sql.DataSource;
import java.text.ParseException;
import java.util.*;

/**
* @description:    sessionFactory的默认实现
* @author:         umr
* @date:           2019/4/26
*/
public class DefaultSessionFactory implements SessionFactory {

    private static final Logger logger = Logger.getLogger(DefaultSessionFactory.class);

    private FickleDataSourceFactory factory;


    public DefaultSessionFactory(FickleDataSourceFactory factory) {
        this.factory = factory;
    }

    @Override
    public SqlSession openSession(String database, Class[] mappers, String[] xmlmappers) throws Exception{
        DataSource dataSource = factory.getDataSource(database);
        Environment environment = new Environment("development",new JdbcTransactionFactory(),dataSource);
        SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();

        //如果为null,附默认值
        if (null == mappers)
            mappers = new Class[]{HistoryMapper.class};
        if (null != xmlmappers){
            Resource[] rs = new ClassPathResource[xmlmappers.length];
            for(int i = 0;i<xmlmappers.length;i++){
                rs[i] = new ClassPathResource(xmlmappers[i]);
            }
            ssf.setMapperLocations(rs);
        }

        Configuration configuration = new Configuration(environment);
        for (Class mapper : mappers) {
            configuration.addMapper(mapper);
        }
        ssf.setDataSource(dataSource);
        ssf.setConfiguration(configuration);
        SqlSessionFactory sqlSessionFactory = ssf.getObject();
        return sqlSessionFactory.openSession();
    }


}
