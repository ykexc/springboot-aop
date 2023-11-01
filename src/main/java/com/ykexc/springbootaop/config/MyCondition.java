package com.ykexc.springbootaop.config;

import com.ykexc.springbootaop.annotation.MyConditional;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author mqz
 */
@Component
public class MyCondition implements Condition {
    @Override
    public boolean matches(@NonNull ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(MyConditional.class.getName());
        String property = context.getEnvironment().getProperty("xx");
        System.out.println("property: " + property);
        assert annotationAttributes != null;
        String value = (String) annotationAttributes.get("value");
        if ("ykexc".equals(value)) return true;
        return true;
    }
}
