package com.zmj.microservice.common.history.base;

import com.zmj.microservice.common.history.cenum.ResponseCodeEnum;
import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.exception.RPCFailedException;
import com.zmj.microservice.common.history.pojo.VO.SysResult;

/**
 * @description base controller
 * @author umr
 * @date 2019/5/31
 */
public class BaseController implements ExceptionHandler<SysResult,DoController<SysResult>>{

    @Override
    public SysResult tryCatch(DoController<SysResult> doController) {
        SysResult result ;
        try{
            result = doController.doController();
        } catch (IllegalParamException e){
             return new SysResult<>(ResponseCodeEnum.ILLEGAL_PARAM, e.getMessage());
        } catch (ConnectDBException e){
            return new SysResult<>(ResponseCodeEnum.CONNECT_DB_FAILED, e.getMessage());
        } catch (EmptyResultException e){
            return new SysResult<>(ResponseCodeEnum.EMPTY_RESULT, e.getMessage());
//        }  catch (RPCFailedException e){
//            return new SysResult<>(ResponseCodeEnum.RPC_FAILED, e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new SysResult<>(ResponseCodeEnum.UNKNOW_EXCEPTION,e.getMessage());
        }
        return result;
    }
}
