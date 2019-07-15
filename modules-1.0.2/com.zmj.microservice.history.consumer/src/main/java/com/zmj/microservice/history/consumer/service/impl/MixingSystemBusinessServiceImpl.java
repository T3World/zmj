package com.zmj.microservice.history.consumer.service.impl;

import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.common.history.util.CommonUtil;
import com.zmj.microservice.common.redis.util.RedisUtil;
import com.zmj.microservice.history.consumer.service.MixingSystemBusinessService;
import com.zmj.microservice.history.consumer.service.feign.MixingSystemContractFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 乳化液自动配比
 * @author umr
 * @date 2019/6/10
 */
@Service
public class MixingSystemBusinessServiceImpl implements MixingSystemBusinessService {

    @Autowired
    private MixingSystemContractFeignService mixingSystemContractFeignService;
    /**
     * getConcentrationData用于查询指定时间段内的乳化液浓度的历史数据；
     *
     * @param baseUNDTO 用来接收参数的实体类
     * @return SysResult
     */
    @Override
    public SysResult<CommonVO> getConcentrationData(BaseUNDTO baseUNDTO) {
        // 得到乳化液浓度查询的结果
        SysResult<CommonVO> concentrationData = mixingSystemContractFeignService.getConcentrationData(baseUNDTO);
        if(concentrationData.getErrcode()!=200)
            return  concentrationData;
        // 得到乳化液浓度查询的数据
        List<CommonVO> data = concentrationData.getData();
        CommonUtil.rmreAndRound(data);
        return new SysResult<>(data);
    }

    /**
     * getHuaYeConcentrationData用于查询指定时间段内的华液乳化液浓度的历史数据
     *
     * @param baseUNDTO 用来接收参数的实体类
     * @return SysResult
     */
    @Override
    public SysResult<CommonVO> getHuaYeConcentrationData(BaseUNDTO baseUNDTO) {
        // 得到乳化液华液浓度查询的结果
        SysResult<CommonVO> huaYeConcentrationData = mixingSystemContractFeignService.getHuaYeConcentrationData(baseUNDTO);
        if(huaYeConcentrationData.getErrcode()!=200)
            return  huaYeConcentrationData;
        // 得到乳化液华液浓度查询的数据
        List<CommonVO> data = huaYeConcentrationData.getData();
        CommonUtil.rmreAndRound(data);
        return new SysResult<>(data);
    }
}
