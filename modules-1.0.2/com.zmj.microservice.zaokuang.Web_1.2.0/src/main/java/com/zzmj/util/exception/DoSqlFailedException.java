package com.zzmj.util.exception;

public class DoSqlFailedException extends RuntimeException {
    private static final long serialVersionUID = 2L;

    public DoSqlFailedException() {
        super();
    }

    public DoSqlFailedException(String msg) {
        super(msg);
    }

    public DoSqlFailedException(Throwable cause) {
    	super(cause);
    }
    
    public DoSqlFailedException(String message, Throwable cause) {
    	super(message, cause);
    }

    public DoSqlFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}