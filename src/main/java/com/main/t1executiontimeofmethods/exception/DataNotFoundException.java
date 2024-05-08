package com.main.t1executiontimeofmethods.exception;

import com.main.t1executiontimeofmethods.annotation.LogThrow;


@LogThrow
public class DataNotFoundException extends ApplicationException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
