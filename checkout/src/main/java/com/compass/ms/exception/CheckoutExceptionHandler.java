package com.compass.ms.exception;

import com.compass.ms.exception.response.ResponseError;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CheckoutExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> invalidValueArgument(MethodArgumentNotValidException e){
        List<ResponseError> details = new ArrayList<>();
        e.getFieldErrors().forEach(error -> {
            details.add(new ResponseError(error.getField(),
                    error.getDefaultMessage()));
        });
        return new ResponseEntity<>(details.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValueInstantiationException.class)
    public ResponseEntity<?> messageNotReadable(ValueInstantiationException e) {
        String fieldProblem = e.getOriginalMessage().substring(e.getOriginalMessage().lastIndexOf("Invalid"));
        ResponseError error = new ResponseError("type", fieldProblem);
        return new ResponseEntity<>(error.toString(), HttpStatus.BAD_REQUEST);
    }


}
