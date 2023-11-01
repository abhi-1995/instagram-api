package com.insta.instagram.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> UserExceptionHandler(UserException e, WebRequest req) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDetails(req.getDescription(false));
        errorDetails.setMessage(e.getMessage());
        errorDetails.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> methodArgumentNotValidException(MethodArgumentNotValidException me) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDetails(Objects.requireNonNull(me.getBindingResult().getFieldError()).getDefaultMessage());
        errorDetails.setMessage(me.getMessage());
        errorDetails.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception e, WebRequest req) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDetails(req.getDescription(false));
        errorDetails.setMessage(e.getMessage());
        errorDetails.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }
}
