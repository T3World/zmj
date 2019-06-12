package com.t3w.haveadinner.exception;

public class LogoutException extends Exception {
    public LogoutException() {
    }

    public LogoutException(String message) {
        super(message);
    }

    public LogoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogoutException(Throwable cause) {
        super(cause);
    }

    public LogoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
