package com.main.t1executiontimeofmethods.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionHandlingAspect {

    @AfterThrowing(pointcut = "@annotation(com.main.t1executiontimeofmethods.annotation.TrackAsyncTime)", throwing = "exception")
    public void handleAsyncMethodException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        log.error("Exception in async method {}: {}", methodName, exception.getMessage());
    }
}