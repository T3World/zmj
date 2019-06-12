package com.zmj.controller;

import com.bestvike.linq.Linq;
import com.zmj.microservice.arithmetic.shearer.pojo.ShearerRatio;
import com.zmj.microservice.arithmetic.shearer.pojo.ShearerStatisticsPeriod;
import com.zmj.microservice.common.history.cenum.DataValueTypeEnum;
import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.constant.SqlFragmentConstant;
import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.exception.MysqlAccessDeniedException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.common.history.util.DbUtil;
import com.zmj.microservice.common.mybatis.service.HistoryService;
import com.zmj.microservice.common.redis.util.RedisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description:    java类作用描述
 * @author:         umr
 * @date:           2019/4/30
 */
@RestController
public class TheOnlyController {

    private static final String SLCMC="Shearer.LeftCuttingMotorCurrent";
    private static final String SRCMC="Shearer.RightCuttingMotorCurrent";
    private static final String SLTMC="Shearer.LeftTractionMotorCurrent";
    private static final String SRTMC="Shearer.RightTractionMotorCurrent";
    private static final String LIKE="Shearer%MotorCurrent";

    private static final Logger LOGGER = Logger.getLogger(TheOnlyController.class);

    private static final String SQL_FOUR_CURRENT = "Select DataValue,GenerateTime From (" +
            "select DataValue,DATE_FORMAT(GenerateTime,'%Y-%m-%d %H:%i:%S') as GenerateTime from TableNameParam " +
            "where DataName like 'DataNameParam' and GenerateTime between StartTimeParam and EndTimeParam) Alias";
    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "/kaijilv",method = RequestMethod.POST,consumes = "application/json")
    public SysResult getRunRatio(@RequestBody BaseUNDTO dto){
        long t0 = System.currentTimeMillis();

        List<CommonVO> data = new LinkedList<>();


        String dbName = null;
        SqlSession session = null;
        try{
            dbName = historyService.getDbName(dto.getDataSourceName());
            session = historyService.getSession(dbName);

            List<ShearerRatio> result = new LinkedList<>();
            ShearerRatio ratioSon = null;

            Long startTime = dto.getStartTime();
            Long endTime = dto.getEndTime();

            List<String> days = DbUtil.getDateBetweenLong(startTime, endTime);

            for (String day : days){
                ratioSon = getRatioByDay(session,dto.getDataSourceName(),dbName, day);
                result.add(ratioSon);
            }
            historyService.sessionClose(session);
            return new SysResult<>(result);
        } catch (IllegalParamException e){
            return new SysResult<>(ResponseCodeEnum.C_400, e.getMessage());
        } catch (ConnectDBException e){
            return new SysResult<>(ResponseCodeEnum.C_503, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new SysResult<>(ResponseCodeEnum.C_500,"系统错误!请联系管理员");
        }
    }



    private ShearerRatio getRatioByDay(SqlSession sqlSession,String dataSourceName,String dbName,String day) throws ParseException {
        ShearerStatisticsPeriod ssp = new ShearerStatisticsPeriod();
        List<CommonVO> result = new LinkedList<>();
        //获取前一天最后一条
        long t0 = System.currentTimeMillis();
        CommonVO preOne = getPreOne(sqlSession, dbName, day);
        long t1 = System.currentTimeMillis();
        //获取后一天最后一条
        CommonVO nextOne = getNextOne(sqlSession, dbName, day);
        long t2 = System.currentTimeMillis();
//        result .addAll(getA(sqlSession,dataSourceName,day));
//        long t3 = System.currentTimeMillis();
//        result .addAll(getB(sqlSession,dataSourceName,day));
//        long t4 = System.currentTimeMillis();
//        result .addAll(getC(sqlSession,dataSourceName,day));
//        long t5 = System.currentTimeMillis();
//        result .addAll(getD(sqlSession,dataSourceName,day));
        result.addAll(getAllFourCurrent(sqlSession,dataSourceName,day));
        long t6 = System.currentTimeMillis();
        List<CommonVO> list = Linq.asEnumerable(result).orderBy(CommonVO::getTime).toList();
        long t7 = System.currentTimeMillis();

        if (!RedisUtil.ifListNull(result)){
            CommonVO first = ((LinkedList<CommonVO>) result).getFirst();
            CommonVO last = ((LinkedList<CommonVO>) result).getLast();

            if (isValid(preOne,first)){
                preOne.setTime(DbUtil.formatTime(day, false));
                ((LinkedList<CommonVO>) result).addFirst(preOne);
            }
            if (isValid(last,nextOne)){
                nextOne.setTime(DbUtil.xuyimiao(day, false));
                ((LinkedList<CommonVO>) result).addLast(nextOne);
            }
        }

        Iterator<CommonVO> iterator = list.iterator();

        while (iterator.hasNext()){
            CommonVO cur = iterator.next();
            ssp.addRecord(cur,getTime(cur));
        }

        ShearerRatio shearerRatio = ssp.getShearerRatio();
        shearerRatio.setDate(day);
        long t8 = System.currentTimeMillis();

        LOGGER.info("总耗时 : "+(t8-t0)+" ms"
                + "getPreOne耗时 : " + (t1 - t0) +" ms"
                + "getNextOne耗时: " + (t2 - t1) +" ms"
//                + "SLCMC耗时: " + (t3 - t2) +" ms"
//                + "SRCMC耗时: " + (t4 - t3) +" ms"
//                + "SLTMC耗时: " + (t5 - t4) +" ms"
//                + "SRTMC耗时: " + (t6 - t5) +" ms"
                + "ALL_FOUR耗时: " + (t6 - t2) +" ms"
                + "LINQ耗时: " + (t7 - t6) +" ms"
                + "算法耗时: " + (t8 - t7) +" ms"
        );
        return shearerRatio;
    }



    private long getTime(CommonVO vo) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.parse(vo.getTime()).getTime();
    }


    private List<CommonVO> getA(SqlSession session,String dataSourceName,String day){
        List<CommonVO> result = new ArrayList<>();
        try {
            List<CommonVO> list = historyService.selectHistoryByDay(session, dataSourceName, SLCMC, day, DataValueTypeEnum.DOUBLE, SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
            if (null != list)
                result = list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    private List<CommonVO> getB(SqlSession session,String dataSourceName,String day){
        List<CommonVO> result = new ArrayList<>();
        try {
            List<CommonVO> list = historyService.selectHistoryByDay(session, dataSourceName, SRCMC, day, DataValueTypeEnum.DOUBLE, SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
            if (null != list)
                result = list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    private List<CommonVO> getC(SqlSession session,String dataSourceName,String day){
        List<CommonVO> result = new ArrayList<>();
        try {
            List<CommonVO> list = historyService.selectHistoryByDay(session, dataSourceName, SLTMC, day, DataValueTypeEnum.DOUBLE, SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
            if (null != list)
                result = list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    private List<CommonVO> getD(SqlSession session,String dataSourceName,String day){
        List<CommonVO> result = new ArrayList<>();
        try {
            List<CommonVO> list = historyService.selectHistoryByDay(session, dataSourceName, SRTMC, day, DataValueTypeEnum.DOUBLE, SqlFragmentConstant.SQL_SELECT_COAL_CUTTER_TRACK_HISTORY_FRAGMENT);
            if (null != list)
                result = list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    private CommonVO getPreOne(SqlSession session,String dbName,String day) throws ParseException {
        String preDay =String.valueOf(DbUtil.getPreDay(day)) ;
        String time = DbUtil.formatTime(preDay, false);
        CommonVO result = new CommonVO(BigDecimal.valueOf(0.0),time);
        try {
            CommonVO lastOne = historyService.selectTheLastOne(session, dbName, LIKE, preDay, DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_THE_LAST_ONE_LIKE);
            if (null!=lastOne)
                result =lastOne;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private CommonVO getNextOne(SqlSession session,String dbName,String day) throws ParseException {
        String nextDay = String.valueOf(DbUtil.getNextDay(day));
        String time = DbUtil.xuyimiao(nextDay,false);
        CommonVO result = new CommonVO(BigDecimal.valueOf(0.0),time);
        try {
            CommonVO lastOne = historyService.selectTheFirstOne(session, dbName, LIKE, nextDay, DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_THE_FIRST_ONE_LIKE);
            if (null!=lastOne)
                result =lastOne;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 得到四种电机电流
     *
     *
     *
     */
    private List<CommonVO> getAllFourCurrent(SqlSession session,String dataSourceName,String day){
        LinkedList<CommonVO> result = new LinkedList<>();
        List<CommonVO> list = null;
        try {
            list = historyService.selectHistoryByDay(session, dataSourceName, "Shearer.%MotorCurrent", day, DataValueTypeEnum.DOUBLE, SQL_FOUR_CURRENT);
        } catch (ParseException | IllegalParamException | MysqlAccessDeniedException e) {
            e.printStackTrace();
        }
        if (!RedisUtil.ifListNull(list))
            result.addAll(list);
        return result;
    }

    /**
     * 后一个的时间要大于等于第一个
     * */
    private boolean isValid(CommonVO v1,CommonVO v2) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long t1 = format.parse(v1.getTime()).getTime();
        long t2 = format.parse(v2.getTime()).getTime();
        return (t2-t1) <= 5*60*1000L;
    }
}
