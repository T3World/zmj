package com.zmj.microservice.common.history.exception;

/**
 * @description 类描述
 * @author umr
 * @date 2019/5/31
 */
public class UnLoginOrTimeOutException extends AccessDenyException {
    public UnLoginOrTimeOutException() {
    }

    public UnLoginOrTimeOutException(String message) {
        super(message);
    }

    public UnLoginOrTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnLoginOrTimeOutException(Throwable cause) {
        super(cause);
    }

    public UnLoginOrTimeOutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
