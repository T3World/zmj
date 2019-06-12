package com.t3w.haveadinner.exception;
/**
 *
 * 通用型 坏token异常
 * */
public class TheBadTokenException extends Exception{

    public TheBadTokenException() {
    }

    public TheBadTokenException(String message) {
        super(message);
    }

    public TheBadTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TheBadTokenException(Throwable cause) {
        super(cause);
    }

    public TheBadTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
