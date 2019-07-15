package com.zmj.microservice.service;

import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;

import java.util.List;
/**
* @description:    刮板机
* @author:         umr
* @date:           2019/4/20
*/
public interface ConveyorSystemService {

    /**
     * 获取刮板机机头高速电机电流
     * @exception
     * @date        2019/4/20 10:13
     */
    List<CommonVO> getConveyorHeadHighSpeedMoterCurrent(BaseUNDTO dto) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;
    /**
     * 获取刮板机机头低速电机电流
     * @exception
     * @date        2019/4/20 10:13
     */
    List<CommonVO> getConveyorHeadLowSpeedMoterCurrent(BaseUNDTO dto) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;

    /**
    * 获取刮板机机尾高速电机电流
    * @exception
    * @date        2019/4/20 10:16
    */

    List<CommonVO> getConveyorTailHighSpeedMoterCurrent(BaseUNDTO dto) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;
    /**
     * 获取刮板机机尾低速电机电流
     * @exception
     * @date        2019/4/20 10:13
     */
    List<CommonVO> getConveyorTailLowSpeedMoterCurrent(BaseUNDTO dto) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;
}
