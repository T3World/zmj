package com.zmj.microservice.common.history.exception;

/**
 *
 * @author umr,hushixian
 * @date 2019/3/11
 */
public class MysqlAccessDeniedException extends ConnectDBException {
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
