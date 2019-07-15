package com.zzmj.util.exception;

public class EmptyResultException extends RuntimeException {
    private static final long serialVersionUID = 2L;

    public EmptyResultException() {
        super();
    }

    public EmptyResultException(String msg) {
        super(msg);
    }

	public EmptyResultException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmptyResultException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyResultException(Throwable cause) {
		super(cause);
	}
    
}