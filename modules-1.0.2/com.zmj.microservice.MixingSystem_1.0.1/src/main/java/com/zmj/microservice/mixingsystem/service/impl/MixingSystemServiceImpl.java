package com.zmj.microservice.mixingsystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.zmj.microservice.common.history.constant.SqlFragmentConstant;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.redis.util.RedisUtil;
import com.zmj.microservice.mixingsystem.config.RedisIndexAutoBuilding;
import com.zmj.microservice.mixingsystem.constant.DatabaseConstant;
import com.zmj.microservice.mixingsystem.exception.IllegalParamException;
import com.zmj.microservice.mixingsystem.mapper.CommonValueMapper;
import com.zmj.microservice.mixingsystem.service.MixingSystemService;
import com.zmj.microservice.mixingsystem.utils.DBUtil;
import com.zmj.microservice.mixingsystem.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class MixingSystemServiceImpl implements MixingSystemService {

    @Autowired
    private MybatisUtil mybatisUtil;

    @Resource(name = "stringRedisTemplate2")
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisIndexAutoBuilding redisIndexAutoBuilding;

    /**
     * 查询某个时间段乳化液浓度变化
     *
     * @param md 实体类，用来接收参数
     * @return 返回值
     */
    @Override
    public List<CommonVO> getConcentrationData(BaseUNDTO md) throws IllegalParamException,EmptyResultException,Exception {
        //参数验证
        String[] split = md.getDataSourceName().split("/");

        if (split.length<3){
            throw new IllegalParamException("非法主题参数");
        }

        // 得到 相应的 数据点 如：Support.Scu[x].Pressure1
        String themeOfDataName = new StringBuffer(DatabaseConstant.THEME_DATANAME_PUMPSTATION_EMULSIONTANK_CONCENTRATION).toString();

        // 替换数据点连接符
        String  dataName []  = themeOfDataName.replace("/",".").split("\\.");
        String keyRedis = dataName[1] + "." + dataName[2] + "." +dataName[3];
        StringBuffer buffer = new StringBuffer();
        StringBuffer append = new StringBuffer();

        // 参数处理
        String theme = new StringBuffer(md.getDataSourceName()).append(themeOfDataName).toString();
        List<String> dates = DBUtil.getDateBetweenLong(md.getStartTime(), md.getEndTime());
        List<CommonVO> list = new LinkedList<>();

        List<CommonVO> listson = null;
        Iterator<String> iterator = dates.iterator();
        this.getWhile(iterator,md,listson,keyRedis,theme,list);

        if (RedisUtil.ifListNull(list))
            throw new EmptyResultException("查询结果为空!");

        return list;
    }

    /**
     * 查询某个时间段华液浓度变化
     *
     * @param md 实体类，用来接收参数
     * @return 返回值
     */
    @Override
    public List<CommonVO> getHuaYeConcentrationData(BaseUNDTO md) throws IllegalParamException,EmptyResultException, Exception {
        //参数验证
        String[] split = md.getDataSourceName().split("/");

        if (split.length<3){
            throw new IllegalParamException("非法主题参数");
        }
        // 得到 相应的 数据点 如：Support.Scu[x].Pressure1
        String themeOfDataName = new StringBuffer(DatabaseConstant.THEME_DATANAME_PUMPSTATION_HUAYE_CONCENTRATION).toString();
        String  dataName []  = themeOfDataName.replace("/",".").split("\\.");
        StringBuffer buffer = new StringBuffer();
        StringBuffer append = new StringBuffer();
        for (int i = 1; i <dataName.length ; i++) {
            append = buffer.append(dataName[i]).append(".");
        }
        String redisKey= append.substring(0,append.length()-1).toString();
        // 参数处理
        String theme = new StringBuffer(md.getDataSourceName()).append(themeOfDataName).toString();
        List<String> dates = DBUtil.getDateBetweenLong(md.getStartTime(), md.getEndTime());
        List<CommonVO> list = new LinkedList<>();

        List<CommonVO> listson = null;
        Iterator<String> iterator = dates.iterator();
        this.getWhile(iterator,md,listson,redisKey,theme,list);

        if (RedisUtil.ifListNull(list))
            throw new EmptyResultException("查询结果为空!");

        return list;
    }



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
            List<String> list1 = new ArrayList<>();
            redisIndexAutoBuilding.buildIndex(strings[0]);
        }else {
            //检查是否是当天
            boolean ifToday = RedisUtil.ifToday(endTime);
            if (ifToday)
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

        result = mapper.getCommonValueData(list);
        sqlSession.close();
        return result;
    }


    private void  getWhile(Iterator<String> iterator,BaseUNDTO md,List<CommonVO> listson
            ,String redisKey,String theme, List<CommonVO> list) throws Exception {
        while (iterator.hasNext()){
            String day = iterator.next();
            String json = RedisUtil.getFromRedisByDay(redisTemplate, md.getDataSourceName(), redisKey, day);
            if (json != null){
                listson = JSON.parseArray(json, CommonVO.class);
            }else {
                listson = this.getCommonValueData(theme, day, day, null);
                RedisUtil.setInRedisByDay(redisTemplate,md.getDataSourceName(),redisKey,day,RedisUtil.toJson(listson));
            }
            list.addAll(listson);
        }
    }

}
