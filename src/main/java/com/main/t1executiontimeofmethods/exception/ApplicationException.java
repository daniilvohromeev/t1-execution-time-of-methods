package com.main.t1executiontimeofmethods.exception;

import com.main.t1executiontimeofmethods.annotation.LogThrow;

@LogThrow
public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }
}
