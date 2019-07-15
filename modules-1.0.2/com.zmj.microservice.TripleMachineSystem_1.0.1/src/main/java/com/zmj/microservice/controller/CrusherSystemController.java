package com.zmj.microservice.controller;


import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.service.CrusherSystemService;
import com.zmj.microservice.triplemachinesystemcontract.CrusherSystemContract;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* @description:    破碎机
* @author:         umr
* @date:           2019/4/20
*/
public class CrusherSystemController implements CrusherSystemContract {
    @Autowired
    private CrusherSystemService service;

    private static final Logger LOGGER = Logger.getLogger(ConveyorSystemController.class);

    @Override
    public SysResult<CommonVO> getCrusherHighSpeadMotorCurrent(BaseUNDTO dto) {
        List<CommonVO> data;
        try{
            data = this.service.getCrusherHighSpeadMotorCurrent(dto);
        } catch (IllegalParamException e){
            return new SysResult<>(ResponseCodeEnum.ILLEGAL_PARAM, e.getMessage());
        } catch (ConnectDBException e){
            return new SysResult<>(ResponseCodeEnum.CONNECT_DB_FAILED, e.getMessage());
        } catch (EmptyResultException e){
            return new SysResult<>(ResponseCodeEnum.EMPTY_RESULT, e.getMessage());
        } catch(Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new SysResult<>(ResponseCodeEnum.UNKNOW_EXCEPTION,"系统错误!");
        }
        return new SysResult<>(data);
    }

    @Override
    public SysResult<CommonVO> getCrusherLowSpeadMotorCurrent(BaseUNDTO dto) {
        List<CommonVO> data;
        try{
            data = this.service.getCrusherLowSpeadMotorCurrent(dto);
        } catch (IllegalParamException e){
            return new SysResult<>(ResponseCodeEnum.ILLEGAL_PARAM, e.getMessage());
        } catch (ConnectDBException e){
            return new SysResult<>(ResponseCodeEnum.CONNECT_DB_FAILED, e.getMessage());
        } catch (EmptyResultException e){
            return new SysResult<>(ResponseCodeEnum.EMPTY_RESULT, e.getMessage());
        } catch(Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return new SysResult<>(ResponseCodeEnum.UNKNOW_EXCEPTION,"系统错误!");
        }
        return new SysResult<>(data);
    }
}
