package com.zmj.microservice.mixingsystem.mapper;

import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonValueMapper {


    String[] selectTables();


    /**
     *
     */

    /**
     *查询华液的浓度/乳化液浓度，共用一个方法，拼接的sql不同，即tableList不同
     * @param tableList 拼接出来的sql参数
     * @return 返回值
     */
    List<CommonVO> getCommonValueData(@Param("tableList") List tableList);



}


