package com.t3w.haveadinner.exception;

/**
 *  token 过期时抛出
 *
 * */
public class TokenTimeOutException extends org.apache.shiro.authc.AuthenticationException {

    public TokenTimeOutException() {
    }

    public TokenTimeOutException(String message) {
        super(message);
    }

    public TokenTimeOutException(Throwable cause) {
        super(cause);
    }

    public TokenTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }
}