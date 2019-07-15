package com.zmj.microservice.shearersystem.controller;


import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.DTO.DrumHeightDataDTO;
import com.zmj.microservice.common.history.pojo.DTO.PositionDataDTO;
import com.zmj.microservice.common.history.pojo.DTO.RunningStateDataDTO;
import com.zmj.microservice.common.history.pojo.DTO.TractionSpeedDataDTO;
import com.zmj.microservice.common.history.pojo.VO.CommonVO;
import com.zmj.microservice.common.history.pojo.VO.DrumData;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import com.zmj.microservice.shearersystem.service.ShearerSystemService;
import com.zmj.microservice.shearersystemcontract.ShearerSystemContractInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @description:    ShearerSystemController
* @author:         umr
* @date:           2019/4/28
*/
@RestController
public class ShearerSystemController implements ShearerSystemContractInterface {

    private ShearerSystemService service;
    @Autowired
    public ShearerSystemController(ShearerSystemService service) {
        this.service = service;
    }

    @Override
    public SysResult<CommonVO> getTractionSpeedData(TractionSpeedDataDTO tsd) {
        List<CommonVO> data ;
        try{
            data = this.service.getTractionSpeedData(tsd);
        } catch (IllegalParamException e){
            return new SysResult<>(ResponseCodeEnum.ILLEGAL_PARAM, e.getMessage());
        } catch (ConnectDBException e){
            return new SysResult<>(ResponseCodeEnum.CONNECT_DB_FAILED, e.getMessage());
        } catch (EmptyResultException e){
            return new SysResult<>(ResponseCodeEnum.EMPTY_RESULT, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new SysResult<>(ResponseCodeEnum.UNKNOW_EXCEPTION,e.getMessage());
        }
        return new SysResult<>(data);
    }

    @Override
    public SysResult<DrumData> getDrumHeightData(DrumHeightDataDTO dhd) {
        List<DrumData> data ;
        try{
            data = this.service.getDrumHeightData(dhd);
        } catch (IllegalParamException e){
            return new SysResult<>(ResponseCodeEnum.ILLEGAL_PARAM, e.getMessage());
        } catch (ConnectDBException e){
            return new SysResult<>(ResponseCodeEnum.CONNECT_DB_FAILED, e.getMessage());
        } catch (EmptyResultException e){
            return new SysResult<>(ResponseCodeEnum.EMPTY_RESULT, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new SysResult<>(ResponseCodeEnum.UNKNOW_EXCEPTION,e.getMessage());
        }
        return new SysResult<>(data);
    }

    @Override
    public SysResult<CommonVO> getPositionData(PositionDataDTO pd) {
        List<CommonVO> data ;
        try{
            data = this.service.getPositionData(pd);
        } catch (IllegalParamException e){
            return new SysResult<>(ResponseCodeEnum.ILLEGAL_PARAM, e.getMessage());
        } catch (ConnectDBException e){
            return new SysResult<>(ResponseCodeEnum.CONNECT_DB_FAILED, e.getMessage());
        } catch (EmptyResultException e){
            return new SysResult<>(ResponseCodeEnum.EMPTY_RESULT, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new SysResult<>(ResponseCodeEnum.UNKNOW_EXCEPTION,e.getMessage());
        }
        return new SysResult<>(data);
    }

    @Override
    public SysResult<CommonVO> getRunningStateData(RunningStateDataDTO rsd) {
        List<CommonVO> data ;
        try{
            data = this.service.getRunningStateData(rsd);
        } catch (IllegalParamException e){
            return new SysResult<>(ResponseCodeEnum.ILLEGAL_PARAM, e.getMessage());
        } catch (ConnectDBException e){
            return new SysResult<>(ResponseCodeEnum.CONNECT_DB_FAILED, e.getMessage());
        } catch (EmptyResultException e){
            return new SysResult<>(ResponseCodeEnum.EMPTY_RESULT, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new SysResult<>(ResponseCodeEnum.UNKNOW_EXCEPTION,e.getMessage());
        }
        return new SysResult<>(data);
    }
}
