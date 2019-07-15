package com.zmj.service;

import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.DTO.SupportPressureDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;

import java.util.List;

public interface CommonValueService {

    /**
     * 查询支架压力历史数据
     * @param sp 支架压力实体类
     * @return 返回list commonValueEntity 实体类
     */
    List<CommonVO> getPressureData(SupportPressureDTO sp) throws Exception;

    /**
     * 配合 查询支架历史数据时使用的方法，这个方法是保证，在redis里面没取到值的时候，拼接字符串去redis里面取值
     * @param theme 主题
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param n 支架号
     * @return
     */
    List<CommonVO> getCommonValueData(String theme, String startTime, String endTime, Integer n) throws Exception;

    /**
     * 自动跟机 历史状态查询
     * @return
     */
    List<CommonVO> getIsAutoRunningByDatasource(BaseUNDTO sp) throws Exception;


    /**
     *查询在指定时间段内煤机的红外位置
     * @param sp 参数，实体类
     * @return  返回值
     */
    List<CommonVO> getInfraredShearerPosData(BaseUNDTO sp) throws Exception;

}
