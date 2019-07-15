package com.zmj.microservice.mixingsystem.service;

import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;

import java.util.List;

public interface MixingSystemService {

    /**
     * 查询某个时间段乳化液浓度变化
     * @param md 实体类，用来接收参数
     * @return 返回值
     */
    public List<CommonVO>  getConcentrationData(BaseUNDTO md) throws Exception;

    /**
     * 查询某个时间段华液浓度变化
     * @param md 实体类，用来接收参数
     * @return 返回值
     */
    public  List<CommonVO> getHuaYeConcentrationData(BaseUNDTO md) throws Exception;



}
