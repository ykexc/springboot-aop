package com.ykexc.springbootaop.enums.exception;

import com.ykexc.springbootaop.exception.ApplicationExceptionEnum;

/**
 * @author mqz
 */
public enum ExceptionEnum implements ApplicationExceptionEnum {

    IllegalParameter(5400, "不合法的参数")
    ;

    final String msg;

    final Integer code;

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    ExceptionEnum(Integer _code, String _msg) {
        code = _code;
        msg = _msg;
    }
}
