package com.main.t1executiontimeofmethods.aspect;

import com.main.t1executiontimeofmethods.services.ExecutionTimeService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TrackTimeAspect {


    private final ExecutionTimeService executionTimeService;

    public TrackTimeAspect(ExecutionTimeService executionTimeService) {
        this.executionTimeService = executionTimeService;
    }

    @Around("@annotation(com.main.t1executiontimeofmethods.annotation.TrackTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        String methodName = joinPoint.getSignature().getName();
        log.info("{} executed in {} ms", methodName, executionTime);
        executionTimeService.saveExecutionTime(methodName, executionTime);
        return proceed;
    }
}