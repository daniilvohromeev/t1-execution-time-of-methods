package com.main.t1executiontimeofmethods.annotation;

import com.main.t1executiontimeofmethods.utils.TimeRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeRangeValidator.class)
public @interface ValidTimeRange {
    String message() default "The start time must be before the end time";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}