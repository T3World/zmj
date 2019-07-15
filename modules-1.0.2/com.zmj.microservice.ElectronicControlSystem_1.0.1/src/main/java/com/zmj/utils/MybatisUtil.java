package com.zmj.utils;
import com.zmj.config.JdbcDataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class MybatisUtil {

    private static final Logger logger = Logger.getLogger("MybatisUtil");

    private JdbcDataSourceFactory factory;

    private StringRedisTemplate template;

    @Autowired
    public MybatisUtil(JdbcDataSourceFactory factory, @Qualifier("stringRedisTemplate1") StringRedisTemplate template) {
        this.factory = factory;
        this.template = template;
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

    /**
     * 从redis索引中查询符合条件的表名
     * */
    public String[] selectTables(String databaseName,String startTime,String endTime) throws ParseException {
        String key = "";
        HashMap<String,String> map = new HashMap<>();
        MySimpleDate simpleDate = new MySimpleDate();

        List<String> dateBetweenLong = DBUtil.getDateBetweenLong(simpleDate.dateToStamp(startTime), simpleDate.dateToStamp(endTime));
        Iterator<String> iterator = dateBetweenLong.iterator();
        while (iterator.hasNext()){
            String date = iterator.next();
            key = new StringBuffer(databaseName).append("_").append(date).toString();
            Set<String> members = template.opsForSet().members(key);
            Iterator<String> i = members.iterator();
            while (i.hasNext()){
                map.put(i.next(),"");
            }
        }
        Set<String> strings = map.keySet();
        String[] array = strings.toArray(new String[strings.size()]);
        logger.info("tables: ");
        logger.info("size: "+array.length);
        for (String i:array) {
            logger.info(i);
        }
        return array;
    }


}
