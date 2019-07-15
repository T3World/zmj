package com.zmj.microservice.filterstation.service;

import com.zmj.microservice.common.history.cenum.DataValueTypeEnum;
import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.constant.SqlFragmentConstant;
import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.DTO.FilterStationDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.FilterDataVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
/**
 * @description:    FilterStationServiceImpl
 * @author:         umr
 * @date:           2019/4/28
 */
@Service
public class FilterStationServiceImpl implements FilterStationService {

    private static final String PRESSURE_TYPE_IN1 = "in1";
    private static final String PRESSURE_TYPE_OUT1 = "out1";
    private static final String PRESSURE_TYPE_IN2 = "in2";
    private static final String PRESSURE_TYPE_OUT2 = "out2";
    private static final String PRESSURE_TYPE_ALL = "all";
    //进口压力0,1,PumpStation.FilterStation.InPressure
    private static final String FSIN = "PumpStation/FilterStation/InPressure";
    private static final String FSIN2 = "PumpStation/FilterStation/InPressure2";
    private static final String FSOUT = "PumpStation/FilterStation/OutPressure";
    private static final String FSOUT2 = "PumpStation/FilterStation/OutPressure2";

    private static Logger LOGGER = Logger.getLogger(FilterStationServiceImpl.class);


    @Autowired
    private com.zmj.microservice.common.mybatis.service.HistoryService historyService;

    @Resource(name = "stringRedisTemplate2")
    private StringRedisTemplate redisTemplate;

    /**
     * 根据参数获取进口，出口，压力的数据
     * @param filterStationDTO 传递参数
     * @return 返回值
     */
    @Override
    public List<FilterDataVO<CommonVO>> getInOutPressureData(FilterStationDTO filterStationDTO) throws Exception {
        String[] types = this.parseParam(filterStationDTO.getPressureType());

        if (types==null)
            throw new IllegalParamException("参数不合法!");

        LinkedList<FilterDataVO<CommonVO>> result = new LinkedList<>();
        FilterDataVO<CommonVO> fdvo ;
        for(int i = 0;i<types.length;i++){
            filterStationDTO.setPressureType(types[i]);
            fdvo = getInOutDataByType(filterStationDTO);
            result.add(fdvo);
        }
        return result;
    }

    private FilterDataVO<CommonVO> getInOutDataByType(FilterStationDTO filterStationDTO) throws Exception {
        // 根据这个参数来判断是进口压力，还是出口压力，或者是进出口压力都有
        String pressureType = filterStationDTO.getPressureType();
        // 返回的结果集
        FilterDataVO<CommonVO> result = new FilterDataVO();
        List<CommonVO> data = null;

        try{
            if (PRESSURE_TYPE_IN1.equals(pressureType)) {
                data = this.getInPressureZeroOneData(new BaseUNDTO(filterStationDTO.getDataSourceName(),
                        filterStationDTO.getStartTime(), filterStationDTO.getEndTime()));
            }else if (PRESSURE_TYPE_IN2.equals(pressureType)) {
                // 查询出来进口压力2的值
                data = this.getInPressureData(new BaseUNDTO(filterStationDTO.getDataSourceName(),
                        filterStationDTO.getStartTime(), filterStationDTO.getEndTime()));
            }else if (PRESSURE_TYPE_OUT1.equals(pressureType)) {
                // 查询出来出口压力2的值
                data = this.getOutPressureZeroOneData(new BaseUNDTO(filterStationDTO.getDataSourceName(),
                        filterStationDTO.getStartTime(), filterStationDTO.getEndTime()));
            }else if (PRESSURE_TYPE_OUT2.equals(pressureType)) {
                // 查询出来出口压力2的值
                data = this.getOutPressureData(new BaseUNDTO(filterStationDTO.getDataSourceName(),
                        filterStationDTO.getStartTime(), filterStationDTO.getEndTime()));
            }
        } catch (EmptyResultException e){
            data = new ArrayList<>();
        }
        result.setPressureType(pressureType);
        result.setData(data);
        return result;
    }
    /**
     * 这取出来的值是InPressure/InPressure1 进口0和1的值
     * @param fs 传参
     * @return 返回值
     * @throws IllegalParamException 参数异常类
     * @throws ConnectDBException 数据库sql异常
     * @throws EmptyResultException  结果为空异常
     * @throws Exception 异常
     */
    public List<CommonVO> getInPressureZeroOneData(BaseUNDTO fs) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception {
        return historyService.listHistory(fs,FSIN,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_HISTORY_FRAGMENT_INOUT_ZERO_ONE);
    }


    /**
     * 这取出来的值是InPressure2 进口2的值
     * @param fs 传参
     * @return 返回值
     * @throws IllegalParamException 参数异常类
     * @throws ConnectDBException 数据库sql异常
     * @throws EmptyResultException  结果为空异常
     * @throws Exception 异常
     */
    @Override
    public List<CommonVO> getInPressureData(BaseUNDTO fs) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception {
        return historyService.listHistory(fs,FSIN2,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_HISTORY_FRAGMENT_LIKE);
    }


    /**
     * 这取出来的值是OutPressure/OutPressure1 出口0和1的值
     * @param fs 传参
     * @return 返回值
     * @throws IllegalParamException 参数异常类
     * @throws ConnectDBException 数据库sql异常
     * @throws EmptyResultException  结果为空异常
     * @throws Exception 异常
     */
    public List<CommonVO> getOutPressureZeroOneData(BaseUNDTO fs) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception {
        return historyService.listHistory(fs,FSOUT,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_HISTORY_FRAGMENT_INOUT_ZERO_ONE);
    }

    /**
     * 这取出来的值是OutPressure2 出口2的值
     * @param fs 传参
     * @return 返回值
     * @throws IllegalParamException 参数异常类
     * @throws ConnectDBException 数据库sql异常
     * @throws EmptyResultException  结果为空异常
     * @throws Exception 异常
     */
    @Override
    public List<CommonVO> getOutPressureData(BaseUNDTO fs) throws IllegalParamException,ConnectDBException,EmptyResultException,Exception {
        return historyService.listHistory(fs,FSOUT2,DataValueTypeEnum.DOUBLE,SqlFragmentConstant.SQL_SELECT_HISTORY_FRAGMENT_LIKE);
    }

    private boolean isNull(String s){
        if (s == null|| "".equals(s.trim()))
            return true;
        return false;
    }

    private String[] parseParam(String param){
        if (null==param|| "".equals(param))
            return null;
        if (PRESSURE_TYPE_ALL.equals(param))
            return new String[]{"in1","in2","out1","out2"};
        return param.split(",");
    }
}
