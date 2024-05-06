package com.main.t1executiontimeofmethods.aspect;

import com.main.t1executiontimeofmethods.services.ExecutionTimeService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.concurrent.CompletableFuture;


@Aspect
@Component
@Slf4j
public class TrackAsyncTimeAspect {


    private final ExecutionTimeService executionTimeService;

    public TrackAsyncTimeAspect(ExecutionTimeService executionTimeService) {
        this.executionTimeService = executionTimeService;
    }

    @Around("@annotation(com.main.t1executiontimeofmethods.annotation.TrackAsyncTime)")
    public Object logAsyncExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        if (result instanceof CompletableFuture<?>) {
            return ((CompletableFuture<?>) result)
                    .whenComplete((res, ex) -> {
                        handleCompletion(joinPoint, start, ex);
                    });
        } else {
            throw new IllegalStateException("Methods annotated with @TrackAsyncTime must return a CompletableFuture or similar.");
        }
    }

    private void handleCompletion(ProceedingJoinPoint joinPoint, long start, Throwable ex) {
        long executionTime = System.currentTimeMillis() - start;
        String methodName = joinPoint.getSignature().getName();

        executionTimeService.saveExecutionTime(methodName, executionTime);

        if (ex == null) {
            log.info("{} executed asynchronously in {} ms", methodName, executionTime);
        } else {
            log.error("Error in asynchronous method {}: {}", methodName, ex.getMessage());
        }
    }
}
