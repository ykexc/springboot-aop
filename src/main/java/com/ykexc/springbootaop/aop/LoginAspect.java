package com.ykexc.springbootaop.aop;

import com.ykexc.springbootaop.annotation.LoginVerify;
import com.ykexc.springbootaop.exception.ApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

import static com.ykexc.springbootaop.enums.exception.ExceptionEnum.Forbidden;
import static com.ykexc.springbootaop.enums.exception.ExceptionEnum.Unauthorized;
import static com.ykexc.springbootaop.utils.Const.ADMIN;
import static com.ykexc.springbootaop.utils.Const.LOGIN;

/**
 * @author mqz
 */
@Aspect
@Component
public class LoginAspect {


    @Pointcut("@annotation(com.ykexc.springbootaop.annotation.LoginVerify)")
    public void interceptor() {
    }


    @SneakyThrows
    @Before("interceptor()")
    public void handle(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        String methodName = joinPoint.getSignature().getName();
        Class<?>[] parameterTypes = ((MethodSignature) (joinPoint.getSignature())).getMethod().getParameterTypes();
        Method method = target.getClass().getMethod(methodName, parameterTypes);
        LoginVerify annotation = method.getAnnotation(LoginVerify.class);
        if (annotation == null) return;
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        if (request.getSession().getAttribute(LOGIN) == null && annotation.value()) throw new ApplicationException(Unauthorized);
        if (request.getSession().getAttribute(ADMIN) == null && annotation.admin()) throw new ApplicationException(Forbidden);
    }

}
