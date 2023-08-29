package com.ykexc.springbootaop.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mqz
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
public @interface GlobalInterceptor {

    /**
     * 全局处理注解
     * @return 是否处理
     */
    boolean value() default false;

}
