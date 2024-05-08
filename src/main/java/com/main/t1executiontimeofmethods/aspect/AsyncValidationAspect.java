package com.main.t1executiontimeofmethods.aspect;

import com.main.t1executiontimeofmethods.exception.InvalidMethodException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class AsyncValidationAspect {

    @Before("@annotation(com.main.t1executiontimeofmethods.annotation.TrackAsyncTime) && execution(* *(..))")
    public void validateAsyncAnnotation(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        if (!method.isAnnotationPresent(Async.class)) {
            throw new InvalidMethodException("@TrackAsyncTime can only be applied to @Async methods");
        }
    }
}