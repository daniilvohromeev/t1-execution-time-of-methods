package com.main.t1executiontimeofmethods.exception.advice;

import com.main.t1executiontimeofmethods.exception.DataNotFoundException;
import com.main.t1executiontimeofmethods.exception.payload.ResponseException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseException> handleProductNotFoundException(DataNotFoundException ex) {
        ResponseException error = new ResponseException(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResponseException> handleDataAccessException(DataAccessException ex) {
        ResponseException error = new ResponseException(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}