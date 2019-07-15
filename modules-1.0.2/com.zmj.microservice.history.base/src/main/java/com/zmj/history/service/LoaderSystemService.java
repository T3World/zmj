package com.zmj.history.service;

import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;

import java.util.List;

/**
* @description:    转载机
* @author:         umr
* @date:           2019/4/20
*/
public interface LoaderSystemService {


    /**
     * 获得转载机高速电机电流
     * @exception
     * @date        2019/4/20 10:13
     */
    List<CommonVO> getLoaderHighSpeadMotorCurrent(BaseUNDTO dto) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;
    /**
     * 获得转载机低速电机电流
     * @exception
     * @date        2019/4/20 10:13
     */
    List<CommonVO> getLoaderHLowSpeadMotorCurrent(BaseUNDTO dto) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;

}
