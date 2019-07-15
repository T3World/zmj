package com.zmj.history.service;

import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;

import java.util.List;
/**
 * @description 乳化液,华液
 * @author umr
 * @date 2019/6/6
 */
public interface MixingSystemService {

    /**
     * 查询某个时间段乳化液浓度变化
     * @param dto 实体类，用来接收参数
     * @return 返回值
     */
    List<CommonVO>  getConcentrationData(BaseUNDTO dto) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;

    /**
     * 查询某个时间段华液浓度变化
     * @param dto 实体类，用来接收参数
     * @return 返回值
     */
    List<CommonVO> getHuaYeConcentrationData(BaseUNDTO dto) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;

}
