package com.ykexc.springbootaop.enums.exception;

import com.ykexc.springbootaop.entity.RestBean;
import com.ykexc.springbootaop.exception.ApplicationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mqz
 */
@ControllerAdvice
public class GlobalExceptionHandle {



    @ExceptionHandler(ApplicationException.class)
    @ResponseBody
    public RestBean<Void> handle(ApplicationException exception) {
        return RestBean.failure(exception.getCODE(), exception.getERROR());
    }



}
