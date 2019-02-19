package com.example.mybatisdemo.service;

import com.example.mybatisdemo.Util.DBUtil;
import com.example.mybatisdemo.Util.MUtil;
import com.example.mybatisdemo.constant.DatabaseConstant;
import com.example.mybatisdemo.mapper.MyMapper;
import com.example.mybatisdemo.pojo.HistoryDO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyServiceImpl implements MyService {

    @Autowired
    private MUtil mUtil;

    @Override
    public String searchAll(String database, String tableName) {
        Class[] mappers = new Class[]{MyMapper.class};
        String[] xmlMappers = new String[]{"mappers/myMapper.xml"};
        SqlSession sqlSession = null;
        try {
            sqlSession = mUtil.openSession(database, mappers, xmlMappers);

            MyMapper mapper = sqlSession.getMapper(MyMapper.class);
            return mapper.selectTheFirst(tableName);


        } catch (Exception e){
            e.printStackTrace();
            System.out.println("factory build failure");
        }finally {
            sqlSession.close();
        }
        return null;
    }

    private String[] selectTables(SqlSession sqlSession,String database,String startTime, String endTime) throws Exception{
        //验证参数合法
        if(ifNull(database)||ifNull(startTime)||ifNull(endTime))
            throw new Exception("selectTables参数不能为空!");

        String[] tables;
        try {
            MyMapper mapper = sqlSession.getMapper(MyMapper.class);

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

    @Override
    public List<HistoryDO> selectCCTrackHistoryInTable(SqlSession session,String tableName, String dataName, String startTime, String endTime) throws Exception {
        MyMapper mapper = session.getMapper(MyMapper.class);
        List<HistoryDO> result = mapper.selectCCTrackHistoryInTable(tableName, dataName, DBUtil.formatTime(startTime,true), DBUtil.formatTime(endTime,true));
        return result;
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

        Class[] mappers = new Class[]{MyMapper.class};
        String[] xmlMappers = new String[]{"mappers/myMapper.xml"};
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

            MyMapper mapper = sqlSession.getMapper(MyMapper.class);

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

    private boolean ifNull(String s){
        if (s == null||s.trim() == "")
            return true;
        return false;
    }



}
