package com.zmj.microservice.common.redis.core;

import com.alibaba.fastjson.JSON;
import com.zmj.microservice.common.history.exception.MysqlAccessDeniedException;
import com.zmj.microservice.common.mybatis.core.SessionFactory;
import com.zmj.microservice.common.mybatis.mapper.HistoryMapper;
import com.zmj.microservice.common.redis.util.RedisUtil;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;

/**
 * @description:    默认实现
 * @author:         umr
 * @date:           2019/4/25
 */
public class DefaultRedisIndexBuilder implements RedisIndexBuilder{
    private static Logger logger  = Logger.getLogger(DefaultRedisIndexBuilder.class);


    private RedisTemplate template;

    private SessionFactory sessionFactory;

    private String [] databaseNames;

    public DefaultRedisIndexBuilder(RedisTemplate template, SessionFactory sessionFactory) {
        this.template = template;
        this.sessionFactory = sessionFactory;
        this.databaseNames = new String[0];
    }

    /**
     * @param dbNames 数据库名数组,
     *                当为null为空数组时,调用buildIndex()刷新索引,
     *                当不为空时,将接受的参数与成员变量中的一一比较,唯不在成员变量中包含时,新建redis索引
     * @param flag 是否强制刷新
     */
    @Override
    public void buildIndex(boolean flag,String... dbNames) throws MysqlAccessDeniedException {
        String[] dbnms = this.databaseNames;
        //1.接受参数首先验null.为null时调用buildIndex()
        if (null == dbNames || 0 == dbNames.length){
            buildIndex();
        }else {
            //查看是否强制刷新
            if (flag)
                buildIndex();
            //2.然后接受参数判断成员变量中是否包含,若包含不作任何操作
            for (String n:dbNames){
                boolean ifDbUsed = RedisUtil.ifDbUsed(databaseNames, n);
                if (!ifDbUsed){
                    //不包含,redis建立该库的表格的索引
                    build(n);
                    //为数组添加新成员
                    int l1 = dbnms.length;
                    dbnms = Arrays.copyOf(dbnms,l1+1);
                    dbnms[l1] = n;
                }
            }
            this.databaseNames = dbnms;
        }

    }

    /**
     * 刷新索引时调用
     */
    @Override
    public void buildIndex() throws MysqlAccessDeniedException {
        for(String s : databaseNames){
            build(s);
        }
    }

    @Override
    public String[] getDatabaseName() {
        return this.databaseNames;
    }

    private void build(String dbName) throws  MysqlAccessDeniedException {
        if (null == dbName)
            return;
        SqlSession sqlSession;
        try {
            sqlSession = sessionFactory.openSession(dbName);
            HistoryMapper mapper = sqlSession.getMapper(HistoryMapper.class);
            String[] tables = mapper.selectTables();
            sqlSession.close();
            RedisUtil.doBuildRedisIndex(template,dbName,tables);
            logger.info("dbNames: "+ JSON.toJSONString(this.databaseNames));
        } catch (PersistenceException e){
            logger.error(e.getMessage());
            throw new MysqlAccessDeniedException("数据库访问受限!",e.getCause());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            sqlSession = null;
        }
    }
}
