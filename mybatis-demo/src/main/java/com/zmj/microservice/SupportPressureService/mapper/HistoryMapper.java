package com.zmj.microservice.SupportPressureService.mapper;

import com.zmj.microservice.SupportPressureService.pojo.DO.HistoryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryMapper {
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
