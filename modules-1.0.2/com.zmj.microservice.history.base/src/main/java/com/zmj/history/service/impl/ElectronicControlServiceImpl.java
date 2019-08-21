package com.zmj.history.service.impl;

import com.zmj.history.service.ElectronicControlService;
import com.zmj.microservice.common.history.cenum.DataValueTypeEnum;
import com.zmj.microservice.common.history.constant.SqlFragmentConstant;
import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.DTO.SupportPressureDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.PressureData;
import com.zmj.microservice.common.history.util.DbUtil;
import com.zmj.microservice.common.mybatis.service.HistoryService;
import com.zmj.microservice.common.redis.util.RedisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @description 电控
 * @author umr
 * @date 2019/6/5
 */
@Service
public class ElectronicControlServiceImpl implements ElectronicControlService {

    private static final Logger LOGGER = Logger.getLogger(ElectronicControlServiceImpl.class);

    private static final String SUPPORT_SCU_NO = "Support/Scu/Pressure";
    private static final String SUPPORT_AUTO_RUNNING = "Support/IsAutoRunning";
    private static final String SUPPORT_INF_SHEARER_POS = "Support/InfraredShearerPos";
    @Autowired
    private HistoryService historyService;

    /**
     * 当 ScuNoList 是多个支架号时 例如：scuNoList = (1,2,3,4)
     * @return  返回结果 是一个list集合
     * @throws Exception 异常 表示数据库异常操作
     * 查询支架压力历史数据  ScuNoList 为 1个值时。
     * @param sp 支架压力实体类
     */
    @Override
    public List<PressureData> getPressureData(SupportPressureDTO sp) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception {
        String dataSourceName = sp.getDataSourceName();
        String dbName = historyService.getDbName(dataSourceName);
        String dataNameFix = SUPPORT_SCU_NO + sp.getPressureType();
        SqlSession session = historyService.getSession(dbName);
        String[] number = sp.getScuNoList().split(",");
        ArrayList<PressureData> result = new ArrayList<>();

        for (String n:number){
            if ("".equals(n)){
                continue;
            }
            String dateName = DbUtil.parseDateName(dataNameFix, Integer.valueOf(n));
            List<CommonVO> list = historyService.listHistory(session, sp, dateName, DataValueTypeEnum.DOUBLE, SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
            result.add(new PressureData(n,list));
        }
        historyService.sessionClose(session);
        return result;
    }

    /**
     * 根据表名查找数据  自动根据状态（isAutoRunning）
     */
    @Override
    public List<CommonVO> getIsAutoRunningData(BaseUNDTO sp) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception {
        List<CommonVO> result = new ArrayList<>();
        List<CommonVO> list = historyService.listHistory(sp, SUPPORT_AUTO_RUNNING, DataValueTypeEnum.BOOLEAN, SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
        boolean f = false;
        if (!RedisUtil.ifListNull(list)){
            CommonVO first = list.get(0);
            result.add(first);
            f = (Boolean)first.getValue();
        }
        for (CommonVO l:list){
            Boolean v = (Boolean) l.getValue();
            if (f == v)
                continue;
            l.setValue(v);
            result.add(l);
            f = v;
        }
        return result;
    }


    /**
     * 查询在指定时间段内煤机的红外位置
     *
     * @param sp 参数，实体类
     * @return 返回值
     */
    @Override
    public List<CommonVO> getInfraredPosData(BaseUNDTO sp) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception{
        return historyService.listHistory(sp,SUPPORT_INF_SHEARER_POS,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
    }

}
