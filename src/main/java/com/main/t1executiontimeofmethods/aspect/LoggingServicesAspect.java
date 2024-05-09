package com.main.t1executiontimeofmethods.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingServicesAspect {

    @Pointcut("execution(* com.main.t1executiontimeofmethods.*.*.*(..))")
    private void allMethodsExecutionTimeService() {}

    @Before("allMethodsExecutionTimeService()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Calling method: {} with arguments: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @After("allMethodsExecutionTimeService()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Completed method: {}", joinPoint.getSignature().getName());
    }
}
