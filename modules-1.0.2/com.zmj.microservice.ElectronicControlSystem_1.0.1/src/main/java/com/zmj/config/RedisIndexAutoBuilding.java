package com.zmj.config;

import com.zmj.mapper.CommonValueMapper;
import com.zmj.microservice.common.history.exception.MysqlAccessDeniedException;
import com.zmj.microservice.common.redis.util.RedisUtil;
import com.zmj.utils.MybatisUtil;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.logging.Logger;

@Component
public class RedisIndexAutoBuilding {

    private static Logger logger  = Logger.getLogger("RedisIndexAutoBuilding");

    private RedisTemplate template;

    private MybatisUtil mUtil;

    private String [] databaseNames;


    @Autowired
    public RedisIndexAutoBuilding(MybatisUtil mUtil,@Qualifier("stringRedisTemplate1") RedisTemplate template) throws Exception {
        this.mUtil = mUtil;
        this.template = template;
        this.databaseNames = new String[0];
    }

    /**
     * 建立索引
     * 参数为数据库名字组成的数组
     * */
    public void buildIndex(String... dbNames)throws Exception{
        Class[] mappers = new Class[]{CommonValueMapper.class};
        String[] xmlMappers = new String[]{"mappers/CommonValueMapper.xml"};
        SqlSession sqlSession = null;
        String[] dbnms = this.databaseNames;
        try {
            //判断是否为null,为null,项目启动或日期为当天
            if (null == dbNames||0 == dbNames.length) {
                for (int i = 0; i<dbnms.length;i++){
                    sqlSession = mUtil.openSession(dbnms[i], mappers, xmlMappers);
                    CommonValueMapper mapper = sqlSession.getMapper(CommonValueMapper.class);
                    String[] tables = mapper.selectTables();
                    RedisUtil.doBuildRedisIndex(template,dbnms[i],tables);
                    sqlSession.close();
                }
                //新数据库
            }else {
                //验证数据库是否存在
                for (int i = 0; i<dbNames.length;i++){
                    sqlSession = mUtil.openSession(dbNames[i], mappers, xmlMappers);
                    CommonValueMapper mapper = sqlSession.getMapper(CommonValueMapper.class);
                    String[] tables = mapper.selectTables();
                    RedisUtil.doBuildRedisIndex(template,dbNames[i],tables);

                    int l1 = dbnms.length;
                    dbnms = Arrays.copyOf(dbnms,l1+1);
                    dbnms[l1] = dbNames[i];
                    sqlSession.close();
                }
                this.databaseNames = dbnms;
            }
        } catch (PersistenceException e){
            throw new MysqlAccessDeniedException("数据库访问受限!",e.getCause());
        } catch (Exception e) {
        } finally {
            sqlSession = null;
        }

    }

    public String[] getDatabaseNames() {
        return databaseNames;
    }

    public void setDatabaseNames(String[] databaseNames) {
        this.databaseNames = databaseNames;
    }
}