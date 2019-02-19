package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.pojo.HistoryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyMapper {
    String selectTheFirst(@Param("tablename") String tablename);

    String[] selectTables(@Param("database") String database,
                          @Param("startTime") String startTime,
                          @Param("endTime") String endTime);

    List<HistoryDO> selectCCTrackHistoryInTable(@Param("tableName") String tableName,
                                                @Param("dataName") String dataName,
                                                @Param("startTime") String startTime,
                                                @Param("endTime") String endTime);

    List<HistoryDO> selectCoalCutterTrackHistory(@Param("tableList") List tableList);
}
