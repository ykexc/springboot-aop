package com.ykexc.springbootaop.annotation;

import com.ykexc.springbootaop.config.MyCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author mqz
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Conditional(MyCondition.class)
@Documented
public @interface MyConditional {


    String value();

    boolean forceCondition() default false;

}
