package com.zmj.microservice.service;

import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;

import java.util.List;
/**
* @description:    破碎机
* @author:         umr
* @date:           2019/4/20
*/
public interface CrusherSystemService {
    /**
     * 获得破碎机高速电机电流
     * @exception
     * @date        2019/4/20 10:13
     */
    List<CommonVO> getCrusherHighSpeadMotorCurrent(BaseUNDTO dto) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;
    /**
     * 获得破碎机低速电机电流
     * @return
     * @exception
     * @date        2019/4/20 10:13
     */
    List<CommonVO> getCrusherLowSpeadMotorCurrent(BaseUNDTO dto) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;

}
