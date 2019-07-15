package com.zmj.history.service.impl;

import com.zmj.history.service.ShearerSystemService;
import com.zmj.microservice.common.history.cenum.DataValueTypeEnum;
import com.zmj.microservice.common.history.constant.SqlFragmentConstant;
import com.zmj.microservice.common.history.constant.TableNameConstant;
import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.*;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.DrumData;
import com.zmj.microservice.common.mybatis.service.HistoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
* @author:         umr,hushixian
* @date:           2019/4/2
*/
@Service
public class ShearerSystemServiceImpl implements ShearerSystemService {

    private static Logger LOGGER = Logger.getLogger(ShearerSystemServiceImpl.class);

    private static final String SHEARER_TS = "Shearer/TractionSpeed";
    private static final String SHEARER_LDH = "Shearer/LeftDrumHeight";
    private static final String SHEARER_RDH = "Shearer/RightDrumHeight";
    private static final String SHEARER_P = "Shearer/Position";
    private static final String SHEARER_RS = "Shearer/RunningStateIDs";

    @Autowired
    private HistoryService historyService;



    @Override
    public List<CommonVO> getTractionSpeedData(TractionSpeedDataDTO fs) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception {
        return historyService.listHistory(fs,SHEARER_TS,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_SPREED_ABS);
    }

    @Override
    public List<DrumData> getDrumHeightData(DrumHeightDataDTO fs) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception {
        LinkedList<DrumData> list = new LinkedList<>();
        DrumData left = new DrumData("left");
        DrumData right = new DrumData("right");
        switch (fs.getDrumType()){
            case "left":
                List<CommonVO> historyDOS = historyService.listHistory(fs,SHEARER_LDH,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
                left.setData(historyDOS);
                list.add(left);
                break;
            case "right":
                List<CommonVO> historyDOS1 = historyService.listHistory(fs, SHEARER_RDH,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
                right.setData(historyDOS1);
                list.add(right);
                break;
            default:
            case "both":
                List<CommonVO> r = historyService.listHistory(fs, SHEARER_RDH,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
                right.setData(r);
                List<CommonVO> l = historyService.listHistory(fs, SHEARER_LDH,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
                left.setData(l);
                list.add(right);
                list.add(left);
        }
        return list;
    }

    @Override
    public List<CommonVO> getPositionData(PositionDataDTO fs) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception {
        return historyService.listHistory(fs,null,SHEARER_P,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT,new String[]{TableNameConstant.SHEARER_ENCODER_POSITION_TABLE_NAME},SqlFragmentConstant.SQL_SELECT_HISTORY_CONSUMER_TABLENAME);
    }

    @Override
    public List<CommonVO> getRunningStateData(RunningStateDataDTO fs) throws IllegalParamException,ConnectDBException,Exception {
        return historyService.listHistory(fs, null,SHEARER_RS,DataValueTypeEnum.STRING,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
    }

}
