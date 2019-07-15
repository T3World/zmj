package com.zmj.microservice.arithmetic.exception;

/**
 * @description Arithmetic 包下所有异常类的baba
 * @author umr
 * @date 2019/6/14
 */
public class AlgorithmicException extends Exception{
    public AlgorithmicException() {
    }

    public AlgorithmicException(String message) {
        super(message);
    }

    public AlgorithmicException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlgorithmicException(Throwable cause) {
        super(cause);
    }

    public AlgorithmicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
