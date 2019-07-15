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
public class ConveyorSystemServiceImpl implements ConveyorSystemService{
    //刮板机机头高速电机电流
    private static final String CONVEYOER_HHS = "Conveyor/Head/HighSpeadMotor/Current";
    //刮板机机头低速电机电流
    private static final String CONVEYOER_HLS = "Conveyor/Head/HighSpeadMotor/Current";
    //刮板机机尾高速电机电流
    private static final String CONVEYOER_THS = "Conveyor/Head/HighSpeadMotor/Current";
    //刮板机机尾低速电机电流
    private static final String CONVEYOER_TLS = "Conveyor/Head/HighSpeadMotor/Current";


    @Autowired
    private HistoryService historyService;


    @Override
    public List<CommonVO> getConveyorHeadHighSpeedMoterCurrent(BaseUNDTO dto) throws IllegalParamException, ConnectDBException, EmptyResultException, Exception {
        return historyService.listHistory(dto,CONVEYOER_HHS,DataValueTypeEnum.DOUBLE, SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
    }

    @Override
    public List<CommonVO> getConveyorHeadLowSpeedMoterCurrent(BaseUNDTO dto) throws IllegalParamException, ConnectDBException, EmptyResultException, Exception {
        return historyService.listHistory(dto,CONVEYOER_HLS,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);

    }

    @Override
    public List<CommonVO> getConveyorTailHighSpeedMoterCurrent(BaseUNDTO dto) throws IllegalParamException, ConnectDBException, EmptyResultException, Exception {
        return historyService.listHistory(dto,CONVEYOER_THS,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
    }

    @Override
    public List<CommonVO> getConveyorTailLowSpeedMoterCurrent(BaseUNDTO dto) throws IllegalParamException, ConnectDBException, EmptyResultException, Exception {
        return historyService.listHistory(dto,CONVEYOER_TLS,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
    }
}
