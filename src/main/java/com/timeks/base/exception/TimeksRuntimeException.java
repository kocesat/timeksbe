package com.timeks.base.exception;

public class TimeksRuntimeException extends RuntimeException{
    public TimeksRuntimeException() {
    }

    public TimeksRuntimeException(String message) {
        super(message);
    }

    public TimeksRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeksRuntimeException(Throwable cause) {
        super(cause);
    }

    public TimeksRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
