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
public class CrusherSystemServiceImpl implements CrusherSystemService{


    //破碎机高速电机电流
    private static final String CRUSHER_HC = "Crusher/HighSpeadMotor/Current";
    //破碎机低速电机电流
    private static final String CRUSHER_LC = "Crusher/LowSpeadMotor/Current";

    @Autowired
    private HistoryService historyService;

    @Override
    public List<CommonVO> getCrusherHighSpeadMotorCurrent(BaseUNDTO dto) throws IllegalParamException, ConnectDBException, EmptyResultException, Exception {
        return historyService.listHistory(dto,CRUSHER_HC,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
    }

    @Override
    public List<CommonVO> getCrusherLowSpeadMotorCurrent(BaseUNDTO dto) throws IllegalParamException, ConnectDBException, EmptyResultException, Exception {
        return historyService.listHistory(dto,CRUSHER_LC,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
    }
}
