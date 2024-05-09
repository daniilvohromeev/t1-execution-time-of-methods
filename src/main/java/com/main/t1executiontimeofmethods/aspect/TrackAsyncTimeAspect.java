package com.main.t1executiontimeofmethods.aspect;

import com.main.t1executiontimeofmethods.exception.InvalidMethodException;
import com.main.t1executiontimeofmethods.service.ExecutionTimeSaveService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;


@Aspect
@Component
@Slf4j
public class TrackAsyncTimeAspect {


    private final ExecutionTimeSaveService executionTimeService;

    public TrackAsyncTimeAspect(ExecutionTimeSaveService executionTimeService) {
        this.executionTimeService = executionTimeService;
    }

    @Around("@annotation(com.main.t1executiontimeofmethods.annotation.TrackAsyncTime)")
    public Object logAsyncExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        if (result instanceof CompletableFuture<?>) {
            return ((CompletableFuture<?>) result)
                    .whenComplete((res, ex) -> {
                        long end = System.currentTimeMillis();
                        handleCompletion(joinPoint, end - start);
                    });
        } else {
            throw new InvalidMethodException("Methods annotated with @TrackAsyncTime must return a CompletableFuture or similar.");
        }
    }

    private void handleCompletion(ProceedingJoinPoint joinPoint, long executionTime) {
        String methodName = joinPoint.getSignature().getName();
        executionTimeService.asyncSaveExecutionTime(methodName, executionTime, LocalDateTime.now());
        log.info("{} executed asynchronously in {} ms", methodName, executionTime);
    }
}
