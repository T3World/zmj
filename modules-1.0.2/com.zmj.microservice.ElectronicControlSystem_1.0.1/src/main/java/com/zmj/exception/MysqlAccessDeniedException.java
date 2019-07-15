package com.zmj.exception;

/**
 * Created by umr on 2019/3/11.
 */
public class MysqlAccessDeniedException extends Exception {
    public MysqlAccessDeniedException() {
    }

    public MysqlAccessDeniedException(String message) {
        super(message);
    }

    public MysqlAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MysqlAccessDeniedException(Throwable cause) {
        super(cause);
    }

    public MysqlAccessDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
