package com.zmj.microservice.mixingsystembusiness.service.impl;

import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.common.history.util.CommonUtil;
import com.zmj.microservice.mixingsystembusiness.service.MixingSystemBusinessService;
import com.zmj.microservice.mixingsystembusiness.service.MixingSystemContractFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        SysResult<CommonVO> concentrationData = mixingSystemContractFeignService.getConcentrationData(baseUNDTO); // 得到乳化液浓度查询的结果
        SysResult<CommonVO> result = new SysResult();
        if(concentrationData.getErrcode()!=200){
            return  concentrationData;
        }
        List<CommonVO> data = concentrationData.getData(); // 得到乳化液浓度查询的数据
        CommonUtil.rmreAndRound(data);
        result.setErrcode(ResponseCodeEnum.OK.getValue());
        result.setErrmsg("访问成功");
        result.setData(data);
        return result;
    }

    /**
     * getHuaYeConcentrationData用于查询指定时间段内的华液乳化液浓度的历史数据
     *
     * @param baseUNDTO 用来接收参数的实体类
     * @return SysResult
     */
    @Override
    public SysResult<CommonVO> getHuaYeConcentrationData(BaseUNDTO baseUNDTO) {
        SysResult<CommonVO> huaYeConcentrationData = mixingSystemContractFeignService.getHuaYeConcentrationData(baseUNDTO);// 得到乳化液华液浓度查询的结果
        SysResult<CommonVO> result = new SysResult();
        if(huaYeConcentrationData.getErrcode()!=200){
            return  huaYeConcentrationData;
        }
        List<CommonVO> data = huaYeConcentrationData.getData(); // 得到乳化液华液浓度查询的数据
        CommonUtil.rmreAndRound(data);
        result.setErrcode(ResponseCodeEnum.OK.getValue());
        result.setErrmsg("访问成功");
        result.setData(data);
        return result;
    }
}
