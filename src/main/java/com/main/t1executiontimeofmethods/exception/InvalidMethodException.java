package com.main.t1executiontimeofmethods.exception;

import com.main.t1executiontimeofmethods.annotation.LogThrow;

@LogThrow
public class InvalidMethodException extends Exception{
    public InvalidMethodException(String message) {
        super(message);
    }
}
