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

    @AfterThrowing(pointcut = "within(com.main.t1executiontimeofmethods.*.*) && " +
            "execution(* * (..) throws @com.main.t1executiontimeofmethods.annotation.LogThrow *)",
            throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        log.info("Exception in method: {}", joinPoint.getSignature().toShortString());
        log.info("with message: {}", exception.getMessage());
    }

}