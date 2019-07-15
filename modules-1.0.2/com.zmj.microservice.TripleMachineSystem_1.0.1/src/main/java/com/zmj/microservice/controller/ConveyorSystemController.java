package com.zmj.microservice.controller;

import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.BaseUNDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.service.ConveyorSystemService;
import com.zmj.microservice.triplemachinesystemcontract.ConveyorSystemContract;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @description:    刮板机历史查询
* @author:         umr
* @date:           2019/4/20
*/
@RestController
public class ConveyorSystemController implements ConveyorSystemContract {

    private static final Logger LOGGER = Logger.getLogger(ConveyorSystemController.class);

    @Autowired
    private ConveyorSystemService service;

    @Override
    public SysResult<CommonVO> getConveyorHeadHighSpeedMoterCurrent(BaseUNDTO dto) {
        List<CommonVO> data;
        try{
            data = this.service.getConveyorHeadHighSpeedMoterCurrent(dto);
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
    public SysResult<CommonVO> getConveyorHeadLowSpeedMoterCurrent(BaseUNDTO dto) {
        List<CommonVO> data;
        try{
            data = this.service.getConveyorHeadLowSpeedMoterCurrent(dto);
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
    public SysResult<CommonVO> getConveyorTailHighSpeedMoterCurrent(BaseUNDTO dto) {
        List<CommonVO> data;
        try{
            data = this.service.getConveyorTailHighSpeedMoterCurrent(dto);
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
    public SysResult<CommonVO> getConveyorTailLowSpeedMoterCurrent(BaseUNDTO dto) {
        List<CommonVO> data;
        try{
            data = this.service.getConveyorTailLowSpeedMoterCurrent(dto);
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
