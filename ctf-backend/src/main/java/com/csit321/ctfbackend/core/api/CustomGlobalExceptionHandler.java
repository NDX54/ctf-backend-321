package com.csit321.ctfbackend.core.api;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> handleJwtException(JwtException exc, WebRequest request) {
        return APIResponse.build(exc.getMessage(), "JWT Error", HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(CustomNotFoundException exc, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exc.getMessage());

        return APIResponse.build(errors, "The requested resource is not found", HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException exc, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exc.getMessage());
        return APIResponse.build(errors, "Errors were found", HttpStatus.BAD_REQUEST, request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException exc, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        exc.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String errorMessage = objectError.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return APIResponse.build(errors, "There are validation errors", HttpStatus.BAD_REQUEST, request);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException exc, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exc.getLocalizedMessage());

        return APIResponse.build(errors, "Errors were found", HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleForbiddenException(ForbiddenException exc, WebRequest request) {

        return APIResponse.build(exc.getMessage(), "Forbidden", HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<?> handleWrongPasswordException(WrongPasswordException exc, WebRequest request) {
        return APIResponse.build(exc.getMessage(), "Wrong password. Try again.", HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(CustomUpdateValidationException.class)
    public ResponseEntity<?> handleUpdateValidationException(CustomUpdateValidationException exc, WebRequest request) {

        return APIResponse.build(Collections.singletonMap("error", exc.getMessage()), "There are validation errors", HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exc, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put(exc.getConstraintName(), "Validation error for: " + exc.getConstraintName());

        return APIResponse.build(errors, "There are validation errors", HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNoHandlerException(NoHandlerFoundException exc, WebRequest request) {

        return APIResponse.build(exc.getBody().getDetail(), "Endpoint not found", HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleInternalServerError(Exception exc, WebRequest request) {

        return APIResponse.build(exc.getMessage(), "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exc, WebRequest request) {

        return APIResponse.build(exc.getMessage(), "Handle method error", HttpStatus.METHOD_NOT_ALLOWED, request);
    }


}

