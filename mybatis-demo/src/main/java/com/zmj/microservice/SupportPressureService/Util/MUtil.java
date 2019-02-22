package com.zmj.microservice.SupportPressureService.Util;

import com.zmj.microservice.SupportPressureService.config.JdbcDataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MUtil {

    private JdbcDataSourceFactory factory;

    @Autowired
    public MUtil(JdbcDataSourceFactory factory) {
        this.factory = factory;
    }

    public SqlSession openSession(String database, Class[] mappers, String[] xmlmappers) throws Exception{
        DataSource dataSource = factory.getJdbcDataSource(database);
        Environment environment = new Environment("development",new JdbcTransactionFactory(),dataSource);

        Configuration configuration = new Configuration(environment);
        for(int i = 0 ; i<mappers.length ; i++){
            configuration.addMapper(mappers[i]);
        }

        Resource[] rs = new ClassPathResource[xmlmappers.length];
        for(int i = 0;i<xmlmappers.length;i++){
            rs[i] = new ClassPathResource(xmlmappers[i]);
        }

        SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
        ssf.setDataSource(dataSource);
        ssf.setConfiguration(configuration);
        ssf.setMapperLocations(rs);

        SqlSessionFactory sqlSessionFactory = ssf.getObject();
        return sqlSessionFactory.openSession();
    }


}
