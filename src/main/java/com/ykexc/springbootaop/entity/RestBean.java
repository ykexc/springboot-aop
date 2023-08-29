package com.ykexc.springbootaop.entity;

import com.ykexc.springbootaop.exception.ApplicationExceptionEnum;

/**
 * @author mqz
 */
public record RestBean<T>(
        Integer code,

        T data,

        String msg
) {
    public static <T> RestBean<T> success(T data, String msg) {
        return new RestBean<>(200, data, msg);
    }

    public static <T> RestBean<T> success(String msg) {
        return success(null, msg);
    }

    public static <T> RestBean<T> failure(Integer code, String msg) {
        return new RestBean<>(code, null, msg);
    }

    public static <T> RestBean<T> failure(Integer code) {
        return new RestBean<>(code, null, null);
    }

    public static <T> RestBean<T> failure(ApplicationExceptionEnum exceptionEnum) {
        return failure(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }
}
