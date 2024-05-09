package com.main.t1executiontimeofmethods.utils;

import com.main.t1executiontimeofmethods.annotation.ValidTimeRange;
import com.main.t1executiontimeofmethods.controller.payload.StatisticsRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeRangeValidator implements ConstraintValidator<ValidTimeRange, StatisticsRequest> {

    @Override
    public boolean isValid(StatisticsRequest value, ConstraintValidatorContext context) {
        if (value.getTimeStart() != null && value.getTimeEnd() != null) {
            return !value.getTimeStart().isAfter(value.getTimeEnd());
        }
        return true;
    }
}
