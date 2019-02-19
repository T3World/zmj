package com.example.mybatisdemo.service;


import com.example.mybatisdemo.pojo.HistoryDO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface MyService {
    String searchAll(String database,String tableName);

    List<HistoryDO> selectCCTrackHistoryInTable(SqlSession session,String tableName,String dataName,String startTime,String endTime)throws Exception;

    List<HistoryDO> selectCoalCutterTrackHistory(String theme,String startTime,String endTime,Integer n) throws Exception;
}
