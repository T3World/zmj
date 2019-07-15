package com.zmj.microservice.common.history.exception;

/**
 * @author umr,hushixian
 * @date 2019-03-30 14:02:32
 */
public class IllegalParamException extends Exception{
    public IllegalParamException() {
        super();
    }

    public IllegalParamException(String message) {
        super(message);
    }

    public IllegalParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParamException(Throwable cause) {
        super(cause);
    }

    protected IllegalParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
