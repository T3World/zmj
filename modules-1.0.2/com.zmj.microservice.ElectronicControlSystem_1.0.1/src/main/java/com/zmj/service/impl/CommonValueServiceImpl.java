package com.zmj.service.impl;

import com.alibaba.fastjson.JSON;
import com.zmj.config.RedisIndexAutoBuilding;
import com.zmj.constant.DatabaseConstant;
import com.zmj.exception.IllegalParamException;
import com.zmj.mapper.AutoRunningMapper;
import com.zmj.mapper.CommonValueMapper;
import com.zmj.microservice.common.history.constant.SqlFragmentConstant;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.DTO.SupportPressureDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.redis.util.RedisUtil;
import com.zmj.service.CommonValueService;
import com.zmj.utils.DBUtil;
import com.zmj.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CommonValueServiceImpl implements CommonValueService {

    private static final Logger LOGGER = Logger.getLogger(CommonValueServiceImpl.class);

    @Autowired
    private MybatisUtil mybatisUtil;

    @Resource(name = "stringRedisTemplate2")
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisIndexAutoBuilding redisIndexAutoBuilding;


    /**
     * 当 ScuNoList 是多个支架号时 例如：scuNoList = (1,2,3,4)
     * @param sp 实体类参数
     * @return  返回结果 是一个list集合
     * @throws Exception 异常 表示数据库异常操作
     */
    public  List<Map<String,Object>> getPressurceDaraByScuNoList(SupportPressureDTO sp) throws Exception {
        List<Map<String,Object>> result  =  new ArrayList<>();

        List<CommonVO> dataList  = new ArrayList<>();
        String  scuNo = sp.getScuNoList();
        String [] scuNoList = scuNo.split(",");
        for (int i = 0; i <scuNoList.length; i++) {
            Map<String,Object> map = new HashMap<>();
            sp.setScuNoList(scuNoList[i]);
            dataList =  this.getPressureData(sp);
            map.put("scuNo",scuNoList[i]);
            map.put("data", dataList);
            result.add(map);
        }
        return result;

    }


    /**
     * 查询支架压力历史数据  ScuNoList 为 1个值时。
     *
     * @param sp 支架压力实体类
     * @return 返回list commonValueEntity 实体类
     */
    @Override
    public List<CommonVO> getPressureData(SupportPressureDTO sp) throws IllegalParamException ,Exception {

        long t1 = System.currentTimeMillis();
        //参数验证
        String[] split = sp.getDataSourceName().split("/");

        if (split.length<3){
            throw new IllegalParamException("非法主题参数");
        }

        // 得到 相应的 数据点 如：Support.Scu[x].Pressure1
        String themeOfDataName = new StringBuffer(DatabaseConstant.THEME_DATANAME_SUPPORT_PRESSURE).append(sp.getPressureType()).toString();
        // 把相应的支架号 填充到scu[] 中
        String dataName = themeOfDataName.replace("/",".").substring(1)+sp.getScuNoList();

        // 参数处理
        String theme = new StringBuffer(sp.getDataSourceName()).append(themeOfDataName).toString();
        List<String> dates = DBUtil.getDateBetweenLong(sp.getStartTime(), sp.getEndTime());

        List<CommonVO> list = new LinkedList<>();

        List<CommonVO> listson = null;

        Iterator<String> iterator = dates.iterator();
        while (iterator.hasNext()){
            String day = iterator.next();
            String json = RedisUtil.getFromRedisByDay(redisTemplate, sp.getDataSourceName(), dataName, day);
            if (json != null){
                listson = JSON.parseArray(json, CommonVO.class);
            }else {
                long a = System.currentTimeMillis();
                listson = this.getCommonValueData(theme, day, day, Integer.parseInt(sp.getScuNoList()));
                long b = System.currentTimeMillis();
                LOGGER.info("select "+theme+" "+day + ""+Integer.parseInt(sp.getScuNoList())+ "cost :"+(b-a)+"ms");

                RedisUtil.setInRedisByDay(redisTemplate,sp.getDataSourceName(),dataName,day,RedisUtil.toJson(listson));
            }
            list.addAll(listson);
        }
        long t2 = System.currentTimeMillis();
        LOGGER.info("getPressureData cost :"+(t2-t1)+" ms");
        return list;
    }

    /**
     * 配合 查询支架历史数据时使用的方法，这个方法是保证，在redis里面没取到值的时候，拼接SQL语句去数据库里面取值   getPressureData接口
     *
     * @param theme 主题
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param n 支架号
     * @return
     */
    @Override
    public List<CommonVO> getCommonValueData(String theme, String startTime, String endTime, Integer n) throws Exception {
        // 解析主题
        String [] strings = DBUtil.parseTheme(theme,n);
        // 得到mappers
        Class [] mappers  = new  Class[]{CommonValueMapper.class};
        // 得到mappers 对应的 xml
        String [] xmlMappers  = new  String[]{"mappers/CommonValueMapper.xml"};
        SqlSession sqlSession = null;
        List<String> list =  new ArrayList<String>();
        List<CommonVO> result;
        // 打开数据库链接
        sqlSession = mybatisUtil.openSession(strings[0],mappers,xmlMappers);

        //检查该数据库是否使用过
        boolean ifDbUsed = RedisUtil.ifDbUsed(redisIndexAutoBuilding.getDatabaseNames(), strings[0]);
        //首次调用,建立索引
        if (!ifDbUsed){
            redisIndexAutoBuilding.buildIndex(strings[0]);
        }else {
            //检查是否是当天
            if (RedisUtil.ifToday(endTime))
                redisIndexAutoBuilding.buildIndex();
        }

        // 项目启动的时候，自动去数据中，查找复合条件的表名
        String [] tables = mybatisUtil.selectTables(strings[0],startTime,endTime);

        if (null == tables||0 == tables.length)
            return new LinkedList<CommonVO>();
        // 拼接开始时间和结束时间
        startTime = new  StringBuffer("'").append(DBUtil.formatTime(startTime,true)).append("'").toString();
        endTime = new  StringBuffer("'").append(DBUtil.xuyimiao(endTime)).append("'").toString();
        // 查询名称
//        String s = new  StringBuffer("'").append(strings[1]).append("'").toString();
        String s = new  StringBuffer(strings[1]).toString();
        // 拼接Sql 语句
        String sql = SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT
                .replaceAll("DataNameParam",s)
                .replaceAll("StartTimeParam",startTime)
                .replaceAll("EndTimeParam",endTime);
        // 循环得到最后拼接成功的sql 语句，放到mapper.xml 中查
        for (int i = 0; i < tables.length; i++) {
            System.out.println(tables[i]);
            String sqlAll = sql.replaceAll("TableNameParam", tables[i]).replaceAll("Alias", "Var" + i);
            System.out.println(sqlAll);
            list.add(sqlAll);
        }
        CommonValueMapper mapper = sqlSession.getMapper(CommonValueMapper.class);

        result = mapper.getPressureDataBySql(list);
        sqlSession.close();
        return result;
    }

    /**
     * 根据查询出来结果进行一次去重排序，因为发现了一个bug
     * 比如：15，16，17号，都是只有false，那么 输出的结果是 false，false，false，所以需要再次 进行去重，
     * 这就是去重的方法
     * @param sp 实体类
     * @return 返回结果
     * @throws Exception 异常处理
     */
    public  List<CommonVO> getSortAutoRunning(BaseUNDTO sp) throws Exception {
        List<CommonVO> data =  this.getIsAutoRunningByDatasource(sp);
        List<CommonVO> list = new ArrayList<>();
        if(data.size()>0){
            boolean flag = (boolean) data.get(0).getValue();
            list.add(data.get(0));
            for (int i = 0; i < data.size(); i++) {
                if(flag == (boolean)data.get(i).getValue()){
                }else{
                    list.add(data.get(i));
                    flag = (boolean)data.get(i).getValue();
                }
            }
        }
        return list;
    }

    /**
     * 根据表名查找数据  自动根据状态（isAutoRunning）
     */
    @Override
    public List<CommonVO> getIsAutoRunningByDatasource(BaseUNDTO sp) throws Exception {
        //参数验证
        String[] split = sp.getDataSourceName().split("/");

        if (split.length<3){
            throw new IllegalParamException("非法主题参数");
        }
        // 得到 相应的 数据点 /Support/Scu/Pressure1
        String themeOfDataName = new StringBuffer(DatabaseConstant.THEME_DATANAME_IS_AUTO_RUNNING).toString();

        // 把/替换成 . 进行下部分处理操作 .Support.Scu.Pressure1
        String dataName = themeOfDataName.replace("/",".");

        // 获取数据点 Support.isAutoRunning
        String [] strings = themeOfDataName.split("/");
        String dataPointName  =  strings[1]+"."+strings[2];

        // 得到需要查询的数据源 zzmj_db_zaokuang_fucunmeidian_3s1008

        String dataSource = new StringBuffer(DatabaseConstant.DATABASE_NAME_PREFIX).append("_").append(sp.getDataSourceName().replaceAll("/","_")).toString();

        // 参数处理 zaokuang/fucunmeidian/3s1008/Support/Scu/Pressure1
        String theme = new StringBuffer(sp.getDataSourceName()).append(themeOfDataName).toString();
        List<String> dates = DBUtil.getDateBetweenLong(sp.getStartTime(), sp.getEndTime());

        List<CommonVO> list = new LinkedList<>();

        List<CommonVO> listson = null;
        Iterator<String> iterator = dates.iterator();
        while (iterator.hasNext()){
            String day = iterator.next();
            String json = RedisUtil.getFromRedisByDay(redisTemplate, sp.getDataSourceName(), dataPointName, day);
            if (json != null){
                listson = JSON.parseArray(json, CommonVO.class);
            }else {
                listson = this.getIsAutoInfrared(theme, day, day,dataSource ,dataPointName);
                RedisUtil.setInRedisByDay(redisTemplate,sp.getDataSourceName(),dataPointName,day,RedisUtil.toJson(listson));
            }
            list.addAll(listson);
        }
        return list;
    }

    /**
     * 查询在指定时间段内煤机的红外位置
     *
     * @param sp 参数，实体类
     * @return 返回值
     */
    @Override
    public List<CommonVO> getInfraredShearerPosData(BaseUNDTO sp) throws Exception {
        //参数验证
        String[] split = sp.getDataSourceName().split("/");

        if (split.length<3){
            throw new IllegalParamException("非法主题参数");
        }

        // 得到 相应的 数据点 如：Support.Scu[x].Pressure1
        String themeOfDataName = new StringBuffer(DatabaseConstant.THEME_DATANAME_INFRARED_SHEARER_POS_DATA).toString();
        // 把相应的支架号 填充到scu[] 中
        String [] dataName = themeOfDataName.replace("/",".").split("\\.");

        String redisKey = dataName[1] + "." + dataName[2];

        // 参数处理
        String theme = new StringBuffer(sp.getDataSourceName()).append(themeOfDataName).toString();
        List<String> dates = DBUtil.getDateBetweenLong(sp.getStartTime(), sp.getEndTime());

        List<CommonVO> list = new ArrayList<>();

        List<CommonVO> listson = null;

        Iterator<String> iterator = dates.iterator();
        while (iterator.hasNext()){
            String day = iterator.next();
            String json = RedisUtil.getFromRedisByDay(redisTemplate, sp.getDataSourceName(), redisKey, day);
            if (json != null){
                listson = JSON.parseArray(json, CommonVO.class);
            }else {
                listson = this.getCommonValueData(theme, day, day, null);
                RedisUtil.setInRedisByDay(redisTemplate,sp.getDataSourceName(),redisKey,day,RedisUtil.toJson(listson));
            }
            list.addAll(listson);
        }
        return list;
    }


    /**
     * 配合 自动根机，红外位置方法使用，这个方法是保证，在redis里面没取到值的时候，拼接SQL语句去数据库里面取值   getPressureData接口
     *
     * @param theme 主题
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 返回值
     */
    public List<CommonVO> getIsAutoInfrared(String theme, String startTime, String endTime,String dataSource, String dataPointName ) throws Exception {
        // 得到mappers
        Class [] mappers  = new  Class[]{AutoRunningMapper.class};
        // 得到mappers 对应的 xml
        String [] xmlMappers  = new  String[]{"mappers/AutoRunningMapper.xml"};
        SqlSession sqlSession = null;
        List<String> list =  new ArrayList<String>();
        List<CommonVO> result = new ArrayList<>();
        List<CommonVO> disResult = new ArrayList<>();
        // 打开数据库链接
        sqlSession = mybatisUtil.openSession(dataSource,mappers,xmlMappers);
        //检查该数据库是否建立过索引
        boolean ifDbUsed = RedisUtil.ifDbUsed(redisIndexAutoBuilding.getDatabaseNames(), dataSource);
        //首次调用,建立索引
        if (!ifDbUsed){
            redisIndexAutoBuilding.buildIndex(dataSource);
        }else {
            //检查是否是当天
            boolean ifToday = RedisUtil.ifToday(endTime);
            if (ifToday)
                redisIndexAutoBuilding.buildIndex();
        }
        // 项目启动的时候，自动去数据中，查找复合条件的表名
        String [] tables = mybatisUtil.selectTables(dataSource,startTime,endTime);
        //如果redis数据库中没有符合条件的表名,返回空的实例
        if (null == tables||0 == tables.length)
            return result;
        // 拼接开始时间和结束时间
        startTime = new  StringBuffer("'").append(DBUtil.formatTime(startTime,true)).append("'").toString();
        endTime = new  StringBuffer("'").append(DBUtil.xuyimiao(endTime)).append("'").toString();
        // 查询名称
//        String s = new  StringBuffer("'").append(dataPointName).append("'").toString();
        String s = new  StringBuffer(dataPointName).toString();

        // 拼接Sql 语句
        String sql = SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT
                .replaceAll("DataNameParam",s)
                .replaceAll("StartTimeParam",startTime)
                .replaceAll("EndTimeParam",endTime);
        // 循环得到最后拼接成功的sql 语句，放到mapper.xml 中查
        for (int i = 0; i < tables.length; i++) {
            String sqlAll = sql.replaceAll("TableNameParam", tables[i]).replaceAll("Alias", "Var" + i);
            list.add(sqlAll);
        }
        AutoRunningMapper mapper = sqlSession.getMapper(AutoRunningMapper.class);
        result = mapper.getIsautoRunningByDatasource(list);
        if(result.isEmpty()){  // 等于空的时候，什么操作都不做
        }else{
            boolean flag = (boolean)result.get(0).getValue();
            disResult.add(result.get(0));
            for (int i = 0; i <result.size() ; i++) {
                if(flag == (boolean)result.get(i).getValue()){
                    // 啥事也不做
                }else{
                    disResult.add(result.get(i));
                    flag = (boolean)result.get(i).getValue();
                }
            }
            sqlSession.close();

        }
        return disResult;
    }





}
