package com.example.sharity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomGlobalExceptionHandeler extends ResponseEntityExceptionHandler {

    //  CREATES CUSTOMIZED ERRORS BY HTTPSTATUS
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setMessage(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            EmptyValueException.class,
            NotUniqueException.class,
            EmailPatternException.class,
            AllNullException.class,
            FieldRequiredException.class })
    public ResponseEntity<CustomErrorResponse> customHandlerBadRequest(Exception ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setMessage(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            CrudException.class })
    public ResponseEntity<CustomErrorResponse> customHandlerOk(Exception ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setMessage(ex.getMessage());
        errors.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(errors, HttpStatus.OK);
    }

    @ExceptionHandler({
            CrudAllException.class,
            InputNotAllowedException.class})
    public ResponseEntity<CustomErrorResponse> customHandlerNotAllowed(Exception ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setMessage(ex.getMessage());
        errors.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());

        return new ResponseEntity<>(errors, HttpStatus.METHOD_NOT_ALLOWED);
    }
}

