package com.ykexc.springbootaop.aop;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.lang.reflect.Parameter;
import java.util.stream.IntStream;

/**
 * @author mqz
 */
@Aspect
@Component
@Slf4j
public class LogAspect {


    @Pointcut("@annotation(com.ykexc.springbootaop.annotation.LogHandle)")
    public void interceptor() {
    }


    @Around("interceptor()")
    public Object beforeHandle(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        long startTime = System.currentTimeMillis();
        String servletPath = request.getServletPath();
        Object[] args = point.getArgs();
        JSONObject object = new JSONObject();
        Parameter[] parameters = ((MethodSignature) (point.getSignature())).getMethod().getParameters();
        IntStream.range(0, parameters.length).forEach(i -> object.put(parameters[i].getName(), args[i].toString()));
        log.info("请求URL:\"{}\" | 远程ip地址: \"{}\" | 请求参数: \"{}\"", servletPath, request.getRemoteAddr(), object);
        Object proceed = point.proceed();
        if (response != null) {
            ContentCachingResponseWrapper wrapper = new ContentCachingResponseWrapper(response);
            wrapper.copyBodyToResponse();
            int status = wrapper.getStatus();
            log.info("请求URL:\"{}\" | 远程ip地址: \"{}\" | 请求状态: \"{}\" | 请求时间: \"{}\"ms"
                    , servletPath, request.getRemoteAddr(), status != 200 ? "错误" : "成功", System.currentTimeMillis() - startTime);
        }
        return proceed;
    }


}
