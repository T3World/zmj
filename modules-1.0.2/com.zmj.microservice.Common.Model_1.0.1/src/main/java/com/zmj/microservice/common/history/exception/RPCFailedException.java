package com.zmj.microservice.common.history.exception;

/**
 * @description 远程服务调用失败时抛出
 * @author umr
 * @date 2019/5/31
 */
public class RPCFailedException extends Exception {
    public RPCFailedException() {
    }

    public RPCFailedException(String message) {
        super(message);
    }

    public RPCFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RPCFailedException(Throwable cause) {
        super(cause);
    }

    public RPCFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
