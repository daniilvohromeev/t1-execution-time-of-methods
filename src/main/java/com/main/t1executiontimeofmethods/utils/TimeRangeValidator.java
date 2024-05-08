package com.main.t1executiontimeofmethods.utils;

import com.main.t1executiontimeofmethods.annotation.ValidTimeRange;
import com.main.t1executiontimeofmethods.modal.payload.RequestStatistics;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeRangeValidator implements ConstraintValidator<ValidTimeRange, RequestStatistics> {

    @Override
    public boolean isValid(RequestStatistics value, ConstraintValidatorContext context) {
        if (value.getTimeStart() != null && value.getTimeEnd() != null) {
            return !value.getTimeStart().isAfter(value.getTimeEnd());
        }
        return true;
    }
}
