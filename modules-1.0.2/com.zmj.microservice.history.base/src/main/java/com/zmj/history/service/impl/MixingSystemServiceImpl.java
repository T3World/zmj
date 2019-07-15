package com.zmj.history.service.impl;

import com.zmj.history.service.MixingSystemService;
import com.zmj.microservice.common.history.cenum.DataValueTypeEnum;
import com.zmj.microservice.common.history.constant.SqlFragmentConstant;
import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.mybatis.service.HistoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 乳化液
 * @author umr
 * @date 2019/6/6
 */
@Service
public class MixingSystemServiceImpl implements MixingSystemService {

    private static final Logger LOGGER  = Logger.getLogger(MixingSystemServiceImpl.class);

    private static final String PUMPSTATION_EMU = "PumpStation/EmulsionTank/Concentration";
    private static final String PUMPSTATION_HUAYE = "PumpStation/Huaye/Concentration";

    @Autowired
    HistoryService historyService;

    /**
     * 查询某个时间段乳化液浓度变化
     *
     * @param dto 实体类，用来接收参数
     * @return 返回值
     */
    @Override
    public List<CommonVO> getConcentrationData(BaseUNDTO dto) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception{
        return historyService.listHistory(dto,PUMPSTATION_EMU,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
    }

    /**
     * 查询某个时间段华液浓度变化
     *
     * @param dto 实体类，用来接收参数
     * @return 返回值
     */
    @Override
    public List<CommonVO> getHuaYeConcentrationData(BaseUNDTO dto) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception {
        return historyService.listHistory(dto,PUMPSTATION_HUAYE,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
    }

}
