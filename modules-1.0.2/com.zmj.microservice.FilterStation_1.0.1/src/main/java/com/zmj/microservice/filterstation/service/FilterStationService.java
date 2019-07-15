package com.zmj.microservice.filterstation.service;


import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
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
    List<CommonVO> getInPressureData(BaseUNDTO fs) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;
    List<CommonVO> getOutPressureData(BaseUNDTO fs) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;
    List<FilterDataVO<CommonVO>> getInOutPressureData(FilterStationDTO filterStationDTO) throws Exception;
}
