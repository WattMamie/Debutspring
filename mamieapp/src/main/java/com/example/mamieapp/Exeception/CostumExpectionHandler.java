package com.example.mamieapp.Exeception;


import com.example.mamieapp.Apierror;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CostumExpectionHandler {
    @ExceptionHandler(PersonNotFoundException.class)
    ResponseEntity<Apierror> handlePersonNotFoundException(PersonNotFoundException ex) {
        Apierror apierror = new Apierror();
        apierror.setMessage(ex.getMessage());
        apierror.setCode(HttpStatus.NOT_FOUND.value());
        apierror.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apierror, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Apierror> handleException(Exception ex) {
        Apierror apierror = new Apierror();
        apierror.setMessage(ex.getMessage());
        apierror.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apierror.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apierror, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
