package com.zmj.microservice.common.history.exception;

/**
 * @author umr,hushixian
 * @date 2019-03-30
 */
public class EmptyResultException extends Exception{
    public EmptyResultException() {
    }

    public EmptyResultException(String message) {
        super(message);
    }

    public EmptyResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyResultException(Throwable cause) {
        super(cause);
    }

    public EmptyResultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
