package com.zmj.microservice.mixingsystembusiness.service;


import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;

public interface MixingSystemBusinessService {

   /**
    *getConcentrationData用于查询指定时间段内的乳化液浓度的历史数据；
    * @param baseUNDTO 用来接收参数的实体类
    * @return  SysResult
    */
   SysResult<CommonVO> getConcentrationData(BaseUNDTO baseUNDTO);

   /**
    * getHuaYeConcentrationData用于查询指定时间段内的华液乳化液浓度的历史数据
    * @param baseUNDTO 用来接收参数的实体类
    * @return SysResult
    */
   SysResult<CommonVO> getHuaYeConcentrationData(BaseUNDTO baseUNDTO);
}
