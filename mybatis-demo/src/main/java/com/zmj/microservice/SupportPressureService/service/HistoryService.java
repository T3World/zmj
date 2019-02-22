package com.zmj.microservice.SupportPressureService.service;


import com.zmj.microservice.SupportPressureService.pojo.DO.HistoryDO;
import com.zmj.microservice.SupportPressureService.pojo.DTO.SupportPressureDTO;
import com.zmj.microservice.SupportPressureService.pojo.VO.SysResult;

import java.util.List;

public interface HistoryService {
    /**
     * 用于提供支架压力的历史查询
     * 参数说明:
     *  databaseParam: 数据库相关参数,格式为:集团/公司/工作面,用于数据库查询时需要再拼接
     *  pressureType: 查询的支架压力类型,分为1,2.默认为1
     *  startTime: 查询历史范围的开始时间,单位为天,格式为时间戳
     *  endTime: 查询历史范围的结束时间,单位为天,格式为时间戳
     *  scuNoList: 查询支架的序号,从1开始的整数,多个用","隔开.例如:"1,2,3"
     *
     * */
    SysResult getSupportPressureData(SupportPressureDTO sp);


    /*原来用于查询表名的方法,暂时废弃使用*/
    String searchAll(String database,String tableName);

    /* 主接口方法,根据传递的主题等查询煤机支架压力历史记录 */
    List<HistoryDO> selectCoalCutterTrackHistory(String theme,String startTime,String endTime,Integer n) throws Exception;



    //下面的都是测试方法
    String testSet(String key,Object obj);

    String testGet(String key);

    String testHget(String key,String field);
}
