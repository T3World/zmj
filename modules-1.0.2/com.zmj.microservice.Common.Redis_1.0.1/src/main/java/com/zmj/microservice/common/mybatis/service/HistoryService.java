package com.zmj.microservice.common.mybatis.service;

import com.zmj.microservice.common.history.cenum.DataValueTypeEnum;
import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.exception.MysqlAccessDeniedException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import org.apache.ibatis.session.SqlSession;

import java.text.ParseException;
import java.util.List;

/**
 * @description:    HistoryService 历史查询
 * @author:         umr
 * @date:           2019/4/26
 */
public interface HistoryService {

    /**
     * 获取数据库连接
     * */
    SqlSession getSession(String dbName) throws IllegalParamException, ConnectDBException,Exception;

    /**
     * 获取数据库名
     * */
    String getDbName(String dataSourceName) throws IllegalParamException;
    /**
     * 关闭数据库连接
     * 这两个方法以后应该改为AOP编程
     * */
    void sessionClose(SqlSession session);

    List<CommonVO> listHistory(BaseUNDTO sp,Integer n, String dataValue, DataValueTypeEnum ee, String sqlFragment, String[] tables,String consumerSql) throws IllegalParamException, ConnectDBException, ParseException, EmptyResultException,Exception ;

    default List<CommonVO> listHistory(BaseUNDTO sp, String dataValue,  DataValueTypeEnum ee, String sqlFragment, String[] tables, String consumerSql) throws IllegalParamException, ConnectDBException, ParseException, EmptyResultException, Exception {
        return listHistory( sp, null, dataValue,   ee,  sqlFragment,  tables, consumerSql);
    }

    default List<CommonVO> listHistory(BaseUNDTO sp,Integer n, String dataValue,  DataValueTypeEnum ee, String sqlFragment) throws IllegalParamException, ConnectDBException, ParseException, EmptyResultException, Exception {
        return listHistory( sp,n,  dataValue,   ee,  sqlFragment,null,null);
    }
    default List<CommonVO> listHistory(BaseUNDTO sp, String dataValue,  DataValueTypeEnum ee, String sqlFragment) throws IllegalParamException, ConnectDBException, ParseException, EmptyResultException, Exception {
        return listHistory( sp, null,  dataValue,  ee,  sqlFragment);
    }


    List<CommonVO> listHistory(SqlSession sqlSession, BaseUNDTO sp,  String dataName, DataValueTypeEnum ee, String sqlFragment, String[] tables,String consumerSql) throws IllegalParamException, ConnectDBException, EmptyResultException, Exception;

    default List<CommonVO> listHistory(SqlSession sqlSession, BaseUNDTO sp, String dataName, DataValueTypeEnum ee, String sqlFragment) throws IllegalParamException, ConnectDBException, EmptyResultException, Exception {
        return listHistory( sqlSession,  sp,   dataName,  ee,  sqlFragment,null,null) ;
    }


    /**
     * 查询当天的第一条数据
     */
    CommonVO selectTheFirstOne(SqlSession sqlSession, String dbName, String dataName, String day, DataValueTypeEnum ee,String sql, String[] tables) throws Exception ;
    default CommonVO selectTheFirstOne(SqlSession sqlSession, String dbName, String dataName, String day, DataValueTypeEnum ee,String sql) throws Exception{
        return selectTheFirstOne(sqlSession,  dbName,  dataName,  day,  ee, sql,null);
    }

    /**
     * 查询当天的最后一条数据
     */
    CommonVO selectTheLastOne(SqlSession sqlSession, String dbName, String dataName, String day, DataValueTypeEnum ee,String sql, String[] tables) throws Exception ;
    default CommonVO selectTheLastOne(SqlSession sqlSession, String dbName, String dataName, String day, DataValueTypeEnum ee,String sql) throws Exception{
        return selectTheLastOne(sqlSession,  dbName,  dataName,  day,  ee, sql,null);
    }

    /**
     * 按天查询,会有缓存
     * */
    List<CommonVO> selectHistoryByDay(SqlSession sqlSession, String dataSourceName, String dataName, String day, DataValueTypeEnum ee, String sqlFragment,String[] tables,String consumerSql) throws ParseException, IllegalParamException, MysqlAccessDeniedException;

    default List<CommonVO> selectHistoryByDay(SqlSession sqlSession, String dataSourceName, String dataName, String day, DataValueTypeEnum ee, String sqlFragment) throws ParseException, IllegalParamException, MysqlAccessDeniedException {
        return selectHistoryByDay( sqlSession,  dataSourceName,  dataName,  day,  ee,  sqlFragment, null, null);
    }

    /**
     *
     * */
    List<CommonVO> selectHistory(SqlSession sqlSession,String dataSourceName,String dataName,Long startTime,Long endTime,DataValueTypeEnum ee ,String sql,String[] tables) throws MysqlAccessDeniedException, ParseException ;
}
