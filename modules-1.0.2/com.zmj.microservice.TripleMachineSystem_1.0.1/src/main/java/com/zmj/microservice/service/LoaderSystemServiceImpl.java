package com.zmj.microservice.service;

import com.zmj.microservice.common.history.cenum.DataValueTypeEnum;
import com.zmj.microservice.common.history.constant.SqlFragmentConstant;
import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.mybatis.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description:    java类作用描述
* @author:         umr
* @date:           2019/4/20
*/

@Service
public class LoaderSystemServiceImpl implements LoaderSystemService{
    //转载机低速电机电流
    private static final String LOADER_LC = "Loader/LowSpeadMotor/Current";
    //转载机高速电机电流
    private static final String LOADER_HC = "Loader/LowSpeadMotor/Current";
    @Autowired
    private HistoryService historyService;

    @Override
    public List<CommonVO> getLoaderHighSpeadMotorCurrent(BaseUNDTO dto) throws IllegalParamException, ConnectDBException, EmptyResultException, Exception {
        return historyService.listHistory(dto,LOADER_HC,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
    }

    @Override
    public List<CommonVO> getLoaderHLowSpeadMotorCurrent(BaseUNDTO dto) throws IllegalParamException, ConnectDBException, EmptyResultException, Exception {
        return historyService.listHistory(dto,LOADER_LC,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
    }
}
