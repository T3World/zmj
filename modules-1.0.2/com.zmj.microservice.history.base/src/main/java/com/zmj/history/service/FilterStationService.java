package com.zmj.history.service;


import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.FilterStationDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.FilterDataVO;

import java.util.List;

/**
* @description:    FilterStationService
* @author:         umr
* @date:           2019/4/28
*/
public interface FilterStationService {
    List<FilterDataVO<CommonVO>> getInOutPressureData(FilterStationDTO filterStationDTO)  throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;
}
