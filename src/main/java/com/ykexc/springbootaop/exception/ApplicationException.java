package com.ykexc.springbootaop.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mqz
 */
@Getter
@Slf4j
public class ApplicationException extends RuntimeException {

    private final int CODE;


    private final String ERROR;


    public ApplicationException(int code, String error) {
        super(error);
        this.CODE = code;
        this.ERROR = error;
    }

    public ApplicationException(ApplicationExceptionEnum applicationException) {
        super(applicationException.getMsg());
        this.ERROR = applicationException.getMsg();
        this.CODE = applicationException.getCode();
        log.warn(applicationException.getMsg());
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
