package com.zmj.microservice.common.history.exception;

/**
 * @description 访问被拒绝,包括没有登录,登陆过期,没有权限等...
 * @author umr
 * @date 2019/5/31
 */
public class AccessDenyException extends Exception{
    public AccessDenyException() {
    }

    public AccessDenyException(String message) {
        super(message);
    }

    public AccessDenyException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDenyException(Throwable cause) {
        super(cause);
    }

    public AccessDenyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
