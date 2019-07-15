package com.zmj.microservice.history.consumer.controller;

import com.zmj.microservice.common.history.base.DoController;
import com.zmj.microservice.common.history.base.ExceptionHandler;
import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.pojo.VO.SysResult;
import feign.FeignException;

/**
 * @description 重写controller
 * @author umr
 * @date 2019/6/10
 */
public class BaseController implements ExceptionHandler<SysResult,DoController<SysResult>> {
    @Override
    public SysResult tryCatch(DoController<SysResult> doController) {
        SysResult result ;
        try {
            result = doController.doController();
        } catch (IllegalParamException e){
            return new SysResult<>(ResponseCodeEnum.ILLEGAL_PARAM, e.getMessage());
//        } catch (EmptyResultException e){
//            return new SysResult<>(ResponseCodeEnum.EMPTY_RESULT, e.getMessage());
        } catch (FeignException e) {
            return new SysResult<>(ResponseCodeEnum.RPC_FAILED, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new SysResult<>(ResponseCodeEnum.UNKNOW_EXCEPTION,e.getMessage());
        }
        return result;
    }
}
