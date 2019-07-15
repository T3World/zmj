package com.zmj.microservice.common.history.base;

/**
 * @description 类描述
 * @author umr
 * @date 2019/5/31
 */
public interface ExceptionHandler<T,Y> {
     /**
      * */
     T tryCatch(Y doController);
}
