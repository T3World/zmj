package com.zmj.microservice.SupportPressureService.service;

import com.zmj.microservice.SupportPressureService.Util.DBUtil;
import com.zmj.microservice.SupportPressureService.Util.MUtil;
import com.zmj.microservice.SupportPressureService.constant.DatabaseConstant;
import com.zmj.microservice.SupportPressureService.mapper.HistoryMapper;
import com.zmj.microservice.SupportPressureService.pojo.DO.HistoryDO;
import com.zmj.microservice.SupportPressureService.pojo.DTO.SupportPressureDTO;
import com.zmj.microservice.SupportPressureService.pojo.VO.SysResult;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private MUtil mUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public SysResult getSupportPressureData(SupportPressureDTO sp) {
        //参数验证
        String[] split = sp.getDataSourceName().split("/");

        if (split.length<3)
            return new SysResult(201,"databaseParam参数不合法!");

        //参数处理
        String s = new StringBuffer(sp.getDataSourceName()).append(DatabaseConstant.THEME_DATANAME_SUPPORT_PRESSURE)
                .append(sp.getPressureType()).toString();

        Long startTime = Long.getLong(sp.getStartTime());
        Long endTime = Long.getLong(sp.getEndTime());
        new SimpleDateFormat("yyyyMMdd000000");
        Date START = new Date(startTime);

        return null;
    }

    @Override
    public String searchAll(String database, String tableName) {
        Class[] mappers = new Class[]{HistoryMapper.class};
        String[] xmlMappers = new String[]{"mappers/HistoryMapper.xml"};
        SqlSession sqlSession = null;
        try {
            sqlSession = mUtil.openSession(database, mappers, xmlMappers);

            HistoryMapper mapper = sqlSession.getMapper(HistoryMapper.class);
            return mapper.selectTheFirst(tableName);


        } catch (Exception e){
            e.printStackTrace();
            System.out.println("factory build failure");
        }finally {
            sqlSession.close();
        }
        return null;
    }

    @Override
    public List<HistoryDO> selectCoalCutterTrackHistory(String theme, String startTime, String endTime, Integer n) throws Exception{
        //验证参数不为空
        //验证参数合法
        if(ifNull(theme)||ifNull(startTime)||ifNull(endTime)||n == null) {
            throw new Exception("selectCoalCutterTrackHistory参数不能为空!");
        }
        //解析主题
        String[] strings = DBUtil.parseTheme(theme, n);

        Class[] mappers = new Class[]{HistoryMapper.class};
        String[] xmlMappers = new String[]{"mappers/HistoryMapper.xml"};
        SqlSession sqlSession = null;
        List<String> list = new ArrayList<>();
        List<HistoryDO> result;


        try {
            sqlSession = mUtil.openSession(strings[0], mappers, xmlMappers);

            String[] tables = this.selectTables(sqlSession,strings[0],startTime,endTime);

            if (tables !=null) {
                System.out.println("表名查询成功!: "+tables.length);
            }

            startTime = new StringBuffer("'").append(DBUtil.formatTime(startTime,true)).append("'").toString();
            endTime = new StringBuffer("'").append(DBUtil.formatTime(endTime,true)).append("'").toString();
            String s = new StringBuffer("'").append(strings[1]).append("'").toString();
            String sql = DatabaseConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT
                    .replaceAll("DataNameParam",s)
                    .replaceAll("StartTimeParam",startTime)
                    .replaceAll("EndTimeParam",endTime);
            for (int i = 0;i<tables.length;i++){
                String sss = sql.replaceAll("TableNameParam", tables[i]).replaceAll("Alias", "Var" + i);
                System.out.println(sss);
                list.add(sss);
                System.out.println("list size : "+list.size());
            }

            HistoryMapper mapper = sqlSession.getMapper(HistoryMapper.class);

            result = mapper.selectCoalCutterTrackHistory(list);

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("factory build failure");
            throw new Exception("do sql faild!",e.getCause());
        }finally {
            sqlSession.close();
        }
        return result;
    }

    @Override
    public String testSet(String key, Object obj) {
        redisTemplate.opsForHash().put(key,"a",obj);
        return "ok";
    }

    @Override
    public String testGet(String key) {
        redisTemplate.opsForValue().set("test","ok");
        String o = redisTemplate.opsForValue().get(key);
        return o;
    }

    @Override
    public String testHget(String key, String field) {
        String o = (String)redisTemplate.opsForHash().get(key, field);
        return o;
    }

    private boolean ifNull(String s){
        if (s == null||s.trim() == "")
            return true;
        return false;
    }


    //从redis中获取数据,如果没有拼接SQL去数据库查

    private String getJsonPreferRedis(String databaseName,String dataValue,String startTime,String endTime){
        String key = DBUtil.getRedisIndexKey(databaseName,dataValue);
//        String[] date =
//
//
//        redisTemplate.opsForSet().add(key,)
        return null;
    }



    private String[] selectTables(SqlSession sqlSession,String database,String startTime, String endTime) throws Exception{
        //验证参数合法
        if(ifNull(database)||ifNull(startTime)||ifNull(endTime))
            throw new Exception("selectTables参数不能为空!");

        String[] tables;
        try {
            HistoryMapper mapper = sqlSession.getMapper(HistoryMapper.class);

            startTime = new StringBuffer(DatabaseConstant.TABLE_NAME_PREFIX).append(startTime).toString();
            endTime = new StringBuffer(DatabaseConstant.TABLE_NAME_PREFIX).append(DBUtil.xuyimiao(endTime)).toString();
            tables = mapper.selectTables(database,startTime,endTime);

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("factory build failure");
            throw new Exception("do sql faild!",e.getCause());
        }
        return tables;
    }
}
