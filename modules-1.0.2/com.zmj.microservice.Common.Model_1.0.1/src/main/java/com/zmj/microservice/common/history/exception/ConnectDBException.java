package com.zmj.microservice.common.history.exception;

/**
 * 连接数据库异常
 *
 * @author umr
 * @date 2019/3/7
 */
public class ConnectDBException extends Exception{
    public ConnectDBException() {
    }

    public ConnectDBException(String message) {
        super(message);
    }

    public ConnectDBException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectDBException(Throwable cause) {
        super(cause);
    }

    public ConnectDBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
