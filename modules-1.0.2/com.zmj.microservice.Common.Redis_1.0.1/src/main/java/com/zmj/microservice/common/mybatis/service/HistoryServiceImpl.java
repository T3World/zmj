package com.zmj.microservice.common.mybatis.service;

import com.alibaba.fastjson.JSON;
import com.zmj.microservice.common.history.cenum.DataValueTypeEnum;
import com.zmj.microservice.common.history.constant.SqlFragmentConstant;
import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.exception.MysqlAccessDeniedException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.util.DbUtil;
import com.zmj.microservice.common.mybatis.core.SessionFactory;
import com.zmj.microservice.common.mybatis.mapper.HistoryMapper;
import com.zmj.microservice.common.redis.core.RedisIndexBuilder;
import com.zmj.microservice.common.redis.util.RedisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description:    通用历史查询类,提供通用方法
 *                  所有接受session的方法是不进行任何参数验证和返回值验证的,
 *                  所以在调用之前要确保传入参数正确,返回值要进行验证
 * @author:         umr
 * @date:           2019/4/25
 */
public class HistoryServiceImpl implements HistoryService {

    private static final Logger LOGGER = Logger.getLogger(HistoryService.class);

    private StringRedisTemplate redisTemplate1;
    private StringRedisTemplate redisTemplate2;
    private RedisIndexBuilder indexBuilder;
    private SessionFactory sessionFactory;


    public HistoryServiceImpl(StringRedisTemplate redisTemplate1, StringRedisTemplate redisTemplate2,RedisIndexBuilder indexBuilder, SessionFactory sessionFactory) {
        this.redisTemplate1 = redisTemplate1;
        this.redisTemplate2 = redisTemplate2;
        this.indexBuilder = indexBuilder;
        this.sessionFactory = sessionFactory;
    }




    /**
     * 查询历史记录通用方法
     * */
    @Override
    public List<CommonVO> listHistory(BaseUNDTO sp,Integer n, String dataValue, DataValueTypeEnum ee, String sqlFragment, String[] tables,String consumerSql) throws IllegalParamException, ConnectDBException, ParseException, EmptyResultException,Exception {
        //解析主题,并验证参数
        String  dbName = getDbName(sp.getDataSourceName());
        //合成dataName
        String dataName = DbUtil.parseDateName(dataValue, n);

        //开启数据库连接
        SqlSession sqlSession = getSession(dbName);

        List<CommonVO> result = this.listHistory(sqlSession, sp, dataName, ee, sqlFragment, tables,consumerSql);
        //验证数组非空
        assertListNotNull(result);
        //关闭数据库连接
        sessionClose(sqlSession);
        return result;
    }

    /**
    * 方法实现说明
     * @param sqlSession 数据库连接会话
     * @param sp dto
     * @param dataName 数据点名称 即查询条件
     * @param ee 查询的数据的类型 只有三种 double,string,boolean
     * @param tables 自定义表名,优先级高
     * @param sqlFragment 子查询语句模板
     * @param consumerSql 查询自定义表格使用的sql
     * @return 一天的历史数据
    * @return
    * @exception   
    * @date        2019/5/9 9:24
    */
    @Override
    public List<CommonVO> listHistory(SqlSession sqlSession, BaseUNDTO sp,  String dataName, DataValueTypeEnum ee, String sqlFragment, String[] tables,String consumerSql) throws IllegalParamException, ConnectDBException, EmptyResultException, Exception {
        List<CommonVO> result = new LinkedList<>();
        List<CommonVO> listSon = null;

        List<String> days = DbUtil.getDateBetweenLong(sp.getStartTime(), sp.getEndTime());
        for (String day :days ){
            listSon = selectHistoryByDay(sqlSession, sp.getDataSourceName(), dataName, day, ee, sqlFragment, tables,consumerSql);
            result.addAll(listSon);
        }
        return result;
    }


    @Override
    public CommonVO selectTheFirstOne(SqlSession sqlSession, String dbName, String dataName, String day, DataValueTypeEnum ee, String sql,String[] tables) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        long startTime = format.parse(DbUtil.formatTime(day, true)).getTime();
        long endTime = format.parse(DbUtil.xuyimiao(day, true)).getTime();
        List<CommonVO> list = this.selectHistory(sqlSession, dbName, dataName, startTime, endTime, ee, SqlFragmentConstant.SQL_SELECT_THE_FIRST_ONE, tables);

        if (RedisUtil.ifListNull(list))
            return null;
        return list.get(0);
    }

    @Override
    public CommonVO selectTheLastOne(SqlSession sqlSession, String dbName, String dataName, String day, DataValueTypeEnum ee,String sql, String[] tables) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        long startTime = format.parse(DbUtil.formatTime(day, true)).getTime();
        long endTime = format.parse(DbUtil.formatTime(day, true)).getTime();
        List<CommonVO> list = this.selectHistory(sqlSession, dbName, dataName, startTime, endTime, ee, SqlFragmentConstant.SQL_SELECT_THE_LAST_ONE, tables);
        if (RedisUtil.ifListNull(list))
            return null;
        return list.get(0);
    }

    /**
     * 以天为单位返回历史数据
     * 有缓存的优先走缓存
     * @param sqlSession 数据库连接会话
     * @param dataSourceName 三级主题: 集团/公司/工作面
     * @param dataName 数据点名称 即查询条件
     * @param day 天 字符串类型 格式为yyyyMMdd 例20190501
     * @param ee 查询的数据的类型 只有三种 double,string,boolean
     * @param sqlFragment 子查询语句模板
     * @return 一天的历史数据
     * @exception ParseException simpleDateFormat 抛出的
     * @date        2019/5/8 13:45
     */
    @Override
    public List<CommonVO> selectHistoryByDay(SqlSession sqlSession, String dataSourceName, String dataName, String day, DataValueTypeEnum ee, String sqlFragment,String[] tables,String consumerSql) throws ParseException, IllegalParamException,MysqlAccessDeniedException {
        String dbName = getDbName(dataSourceName);

        List<CommonVO> result = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

        Long startTime = format.parse(DbUtil.formatTime(day,true)).getTime();
        Long endTime = format.parse(DbUtil.xuyimiao(day,true)).getTime();


        String json = RedisUtil.getFromRedisByDay(redisTemplate2, dataSourceName, dataName, day);
        if (json != null){
            result = JSON.parseArray(json, CommonVO.class);
        }else {
            long t1 = System.currentTimeMillis();
            //先判断tables是否为空,如果为空就不进行自定义查询,不为空,优先查询tables,如果返回值为空,再进行一般查询
            if (null != tables)
                //针对有自定义存储表的特殊情况
                result = selectHistory(sqlSession,dbName,dataName, startTime, endTime, ee,consumerSql,tables);

            if (RedisUtil.ifListNull(result))
                result = selectHistory(sqlSession,dbName,dataName, startTime, endTime, ee,sqlFragment,null);

            long t2 = System.currentTimeMillis();
            LOGGER.info("select cost "+(t2-t1)+" ms");
            RedisUtil.setInRedisByDay(redisTemplate2,dataSourceName,dataName,day,RedisUtil.toJson(result));
        }

        return result;
    }

    /**
     * 分表常规查询历史数据,时间条件精确到毫秒
     *
     * @param sqlSession 数据库连接
     * @param dbName 数据库名
     * @param dataName 数据点名,也是查询条件
     * @param startTime 时间戳 查询的开始时间范围
     * @param endTime 时间戳 时间范围
     * @param ee 查询的数据类型
     * @param sql 子查询语句模板
     * @param tables 非常规表名
     *
     * @return startTime 到 endTime之间的历史数据,如果查不到表名或者给的自定义表名在数据库中不存在,会返回null
     * @exception MysqlAccessDeniedException mysql访问受限
     * @exception ParseException simpleDateFormat 的异常
     * @date        2019/5/8 14:18
     */
    @Override
    public List<CommonVO> selectHistory(SqlSession sqlSession,String dbName,String dataName,Long startTime,Long endTime,DataValueTypeEnum ee ,String sql,String[] tables) throws MysqlAccessDeniedException, ParseException {

        List<CommonVO> result;
        HistoryMapper mapper = sqlSession.getMapper(HistoryMapper.class);
        List<String> list = new ArrayList<>();
        boolean selectTable;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        //建立索引
        indexBuilder.buildIndex(RedisUtil.ifToday(endTime),dbName);

        //判断适用哪种情况
        if (null == tables){
            //在redis索引中查询符合条件的表名
            tables = RedisUtil.selectTables(redisTemplate1,dbName,startTime,endTime);
        }else {
            if (!areTablesExist(mapper, tables))
                return null;
        }

        if (null == tables || 0 == tables.length) {
            LOGGER.warn("未查询到规定时间域内符合条件的表名,时间域: "+startTime+" - "+endTime);
            return null;
        }

        LOGGER.info("表名查询成功!: "+tables.length);

        String s1 = new StringBuffer("'").append(format.format(new Date(startTime))).append("'").toString();
        String s2 = new StringBuffer("'").append(format.format(new Date(endTime))).append("'").toString();
        String s = dataName;
        sql = sql
                .replaceAll("DataNameParam",s)
                .replaceAll("StartTimeParam",s1)
                .replaceAll("EndTimeParam",s2);
        for (int i = 0;i<tables.length;i++){
            String sss = sql.replaceAll("TableNameParam", tables[i]).replaceAll("Alias", "Var" + i);
            LOGGER.info(sss);
            list.add(sss);
            LOGGER.info("list size : "+list.size());
        }

        //打印sql
        LOGGER.info(sql);
        if (ee==null)
            ee = DataValueTypeEnum.STRING;
        switch (ee){
            case DOUBLE:
                result = mapper.selectDoubleHistory(list);
                break;
            default:
            case STRING:
                result = mapper.selectStringHistory(list);
                break;
            case BOOLEAN:
                result = mapper.selectBooleanHistory(list);
                break;
        }

        if(result == null)
            return new LinkedList<>();
        return result;
    }


    /**
     * 获取数据库连接
     * */
    @Override
    public SqlSession getSession(String dbName) throws IllegalParamException, ConnectDBException ,Exception{
        SqlSession sqlSession = null;

        //开启数据库连接
        try {
            sqlSession = sessionFactory.openSession(dbName);
        }catch (RuntimeException re){
            re.printStackTrace();
            LOGGER.warn(re.getMessage());
            throw new ConnectDBException("连接数据库异常!请检查网络并重试!");
        }

        return sqlSession;
    }

    /**
     * 关闭数据库连接
     * */
    @Override
    public void sessionClose(SqlSession session){
        //关闭数据库连接
        try {
            session.close();
        }catch (RuntimeException re){
            LOGGER.error(re.getMessage());
        }finally {
            session = null;
        }
    }
    private boolean areTablesExist(HistoryMapper mapper,String[] tableNames){
        for (String t:tableNames) {
            String[] strings = mapper.showTableLike(t);
            if (strings.length == 0)
                return false;
        }
        return true;
    }

    @Override
    public String getDbName(String dataSourceName) throws IllegalParamException {
        if (dataSourceName == null)
            throw new IllegalParamException("dataSourceName 不能为空!");
        //解析主题
        String  dbName = DbUtil.parseTheme(dataSourceName);
        //验证主题解析成果
        if (dbName == null) {
            throw new IllegalParamException("主题参数不合法!");
        }
        return dbName;
    }

    /**
    * 验证该list是否为空,如果为空,会抛出异常
    * @exception EmptyResultException list为空时抛出
    * @date        2019/5/9 8:57
    */
    private void assertListNotNull(List<CommonVO> list) throws EmptyResultException {
        if (RedisUtil.ifListNull(list))
            throw new EmptyResultException("查询结果为空!");
    }

    private long getStartTimeFromDay(String yyyyMMdd) throws ParseException {
        return new SimpleDateFormat("yyyyMMdd").parse(yyyyMMdd).getTime();
    }

    private long getEndTimeFromDay(String yyyyMMdd) throws ParseException {
        return new SimpleDateFormat("yyyyMMdd").parse(yyyyMMdd).getTime();
    }

}
