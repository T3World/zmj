package com.zmj.mapper;

import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonValueMapper {


    String[] selectTables();


    /**
     * 支架号历史记录 查询根据拼接好的Sql 语句
     */

    List<CommonVO> getPressureDataBySql(@Param("tableList") List tableList);

    /**
     * 查询在指定时间段内煤机的红外位置
     * @param tableList 拼接出来的sql 语句
     * @return 返回值
     */
    List<CommonVO> getInfraredShearerPosData(@Param("tableList") List tableList);


}


