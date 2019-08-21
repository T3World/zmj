package com.zmj.microservice.common.history.exception;
/**
 * @description 未达到预期效果时抛出
 * @author umr
 * @date 2019/7/27
 */
public class DoSqlFaildException extends Exception {
    public DoSqlFaildException() {
    }

    public DoSqlFaildException(String message) {
        super(message);
    }

    public DoSqlFaildException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoSqlFaildException(Throwable cause) {
        super(cause);
    }

    public DoSqlFaildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
