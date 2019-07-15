package com.zmj.microservice.common.history.base;

import com.zmj.microservice.common.history.exception.ConnectDBException;
import com.zmj.microservice.common.history.exception.EmptyResultException;
import com.zmj.microservice.common.history.exception.IllegalParamException;
import com.zmj.microservice.common.history.exception.RPCFailedException;

/**
 * @description 实现baseController时需重写
 * @author umr
 * @date 2019/5/31
 */
public interface DoController<T> {
    T doController() throws IllegalParamException,ConnectDBException,EmptyResultException,Exception;
//    T doController() throws IllegalParamException,ConnectDBException,EmptyResultException;
}
