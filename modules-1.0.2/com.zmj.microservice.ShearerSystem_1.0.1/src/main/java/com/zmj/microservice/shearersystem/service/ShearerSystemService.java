package com.zmj.microservice.shearersystem.service;


import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.DrumHeightDataDTO;
import com.zmj.microservice.common.history.pojo.DTO.PositionDataDTO;
import com.zmj.microservice.common.history.pojo.DTO.RunningStateDataDTO;
import com.zmj.microservice.common.history.pojo.DTO.TractionSpeedDataDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.DrumData;

import java.util.List;
/**
* @description:    java类作用描述
* @author:         umr
* @date:           2019/4/26
*/
public interface  ShearerSystemService {
    List<CommonVO> getTractionSpeedData(TractionSpeedDataDTO fs) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;
    List<DrumData> getDrumHeightData(DrumHeightDataDTO fs) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;
    List<CommonVO> getPositionData(PositionDataDTO fs) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;
    /**
     * 查询采煤机状态码
     * */
    List<CommonVO> getRunningStateData(RunningStateDataDTO fs) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;
}
