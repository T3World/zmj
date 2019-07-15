package com.zmj.microservice.arithmetic.exception;

/**
 * @description 当Arithmetic对不支持的Period进行计算时,抛出该异常
 * @author umr
 * @date 2019/6/14
 */
public class UnSupportPeriodException extends Exception{

    public UnSupportPeriodException() {
    }

    public UnSupportPeriodException(String message) {
        super(message);
    }

    public UnSupportPeriodException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnSupportPeriodException(Throwable cause) {
        super(cause);
    }

    public UnSupportPeriodException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
