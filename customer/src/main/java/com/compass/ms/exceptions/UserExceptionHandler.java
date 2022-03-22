package com.compass.ms.exceptions;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityExistsException;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<?> userEmailFound (EntityExceptionResponse e){
        return new ResponseEntity<>(
                new ErrorMessage(e.getMessage(), e.getCause()),
                e.getStatus());
    }

}
