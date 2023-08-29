package com.ykexc.springbootaop.aop;

import com.ykexc.springbootaop.annotation.GlobalInterceptor;
import com.ykexc.springbootaop.annotation.VerifyParam;
import com.ykexc.springbootaop.exception.ApplicationException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;
import java.util.regex.Pattern;

import static com.ykexc.springbootaop.enums.exception.ExceptionEnum.IllegalParameter;
import static com.ykexc.springbootaop.utils.Const.BASIC_TYPE;

/**
 * @author mqz
 */
@Aspect
@Component
@Slf4j
public class GlobalOperationAspect {


    @Pointcut("@annotation(com.ykexc.springbootaop.annotation.GlobalInterceptor)")
    private void requestInterceptor() {
    }


    @Before("requestInterceptor()")
    public void handle(JoinPoint point) throws Exception {
        //  获取目标对象
        Object target = point.getTarget();

        //  获取方法参数
        Object[] args = point.getArgs();

        //  获取方法名
        String name = point.getSignature().getName();

        //  获取参数类型
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();

        Method method = target.getClass().getMethod(name, parameterTypes);

        //  判断method上面是否有全局注解
        GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);
        if (interceptor == null) return;

        //  参数校验
        if (interceptor.value())
            check(method, args);
    }

    private void check(Method method, Object[] args) {
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object arg = args[i];
            VerifyParam annotation = parameter.getAnnotation(VerifyParam.class);
            if (annotation == null) continue;
            String typeName = parameter.getParameterizedType().getTypeName();
            if (Set.of(BASIC_TYPE).contains(typeName))
                checkValue(arg, annotation);
            else checkObject(arg, parameter);
        }
    }

    @SneakyThrows
    private void checkObject(Object arg, Parameter parameter) {
        String typeName = parameter.getParameterizedType().getTypeName();
        Class<?> aClass = Class.forName(typeName);
        Field[] fields = aClass.getDeclaredFields();
        for (var field : fields) {
            VerifyParam annotation = field.getAnnotation(VerifyParam.class);
            if (annotation == null) continue;
            field.setAccessible(true);
            Object v = field.get(arg);
            checkValue(v, annotation);
        }
    }


    private void checkValue(Object arg, VerifyParam annotation) {
        boolean hasText;
        int length;
        if (arg == null) {
            hasText = false;
            length = 0;
        } else {
            String stringArgs = arg.toString();
            hasText = StringUtils.hasText(stringArgs);
            length = stringArgs.length();
        }
        if (annotation.required() && !hasText)
            throw new ApplicationException(IllegalParameter);
        if (annotation.max() < length || annotation.min() > length)
            throw new ApplicationException(IllegalParameter);
        String regex = annotation.regex().getRegex();
        if (StringUtils.hasText(regex) && hasText) {
            boolean isMatched = Pattern.compile(regex).matcher(arg.toString()).matches();
            if (!isMatched) throw new ApplicationException(IllegalParameter);
        }
    }
}
