package com.zmj.mapper;

import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AutoRunningMapper  {

    String[] selectTables();

    // 查询 自动根机状态的 sql语句
    List<CommonVO> getIsautoRunningByDatasource(@Param("tableList") List tableList);
}
