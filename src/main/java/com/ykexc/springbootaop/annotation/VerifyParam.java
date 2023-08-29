package com.ykexc.springbootaop.annotation;

import com.ykexc.springbootaop.enums.regex.VerifyRegexEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mqz
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface VerifyParam {

    /**
     *最短长度
     */
    int min() default Integer.MIN_VALUE;

    /**
     *最长长度
     */
    int max() default Integer.MAX_VALUE;


    /**
     *是否必填
     */
    boolean required() default false;

    /**
     *自定义正则匹配
     */
    VerifyRegexEnum regex() default VerifyRegexEnum.NO;

}
