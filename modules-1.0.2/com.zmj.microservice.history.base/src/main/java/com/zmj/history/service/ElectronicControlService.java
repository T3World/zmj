package com.zmj.history.service;

import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.DTO.SupportPressureDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.PressureData;

import java.util.List;
/**
 * @description 电控系统
 * @author umr
 * @date 2019/6/5
 */
public interface ElectronicControlService {

    /**
     * 查询支架压力历史数据
     * @param sp 支架压力实体类
     * @return 返回list commonValueEntity 实体类
     */
    List<PressureData> getPressureData(SupportPressureDTO sp) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;

    /**
     * 自动跟机 历史状态查询
     * @return
     */
    List<CommonVO> getIsAutoRunningData(BaseUNDTO sp) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;


    /**
     *查询在指定时间段内煤机的红外位置
     * @param sp 参数，实体类
     * @return  返回值
     */
    List<CommonVO> getInfraredPosData(BaseUNDTO sp) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;

}
