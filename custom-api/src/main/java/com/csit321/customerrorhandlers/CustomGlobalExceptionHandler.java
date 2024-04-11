package com.csit321.customerrorhandlers;

import com.csit321.customresponses.APIResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleNotFoundException(CustomNotFoundException exc, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exc.getMessage());

        APIResponse<Map<String, String>> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setDetails(errors);
        response.setMessage("The requested resource is not found");
        response.setPath(request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleValidationException(MethodArgumentNotValidException exc, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        exc.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String errorMessage = objectError.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        APIResponse<Map<String,String>> responseError = new APIResponse<>();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setStatus(HttpStatus.BAD_REQUEST.value());
        responseError.setMessage("There are validation errors");
        responseError.setDetails(errors);
        responseError.setPath(request.getDescription(false));

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomUpdateValidationException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleUpdateValidationException(CustomUpdateValidationException exc, WebRequest request) {

        APIResponse<Map<String, String>> responseError = new APIResponse<>();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setStatus(HttpStatus.BAD_REQUEST.value());
        responseError.setMessage("There are validation errors");
        responseError.setDetails(Collections.singletonMap("error", exc.getMessage()));
        responseError.setPath(request.getDescription(false));

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleConstraintViolationException(ConstraintViolationException exc, WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        errors.put(exc.getConstraintName(), "Validation error for: " + exc.getConstraintName());

        APIResponse<Map<String,String>> responseError = new APIResponse<>();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setStatus(HttpStatus.BAD_REQUEST.value());
        responseError.setMessage("There are validation errors");
        responseError.setDetails(errors);
        responseError.setPath(request.getDescription(false));

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<APIResponse<String>> handleNoHandlerException(NoHandlerFoundException exc, WebRequest request) {

        APIResponse<String> responseError = new APIResponse<>();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setStatus(HttpStatus.NOT_FOUND.value());
        responseError.setMessage("Endpoint not found");
        responseError.setDetails(exc.getBody().getDetail());
        responseError.setPath(request.getDescription(false));

        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<String>> handleInternalServerError(Exception exc, WebRequest request) {

        APIResponse<String> responseError = new APIResponse<>();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseError.setMessage("Internal Server Error");
        responseError.setDetails(exc.getMessage());
        responseError.setPath(request.getDescription(false));

        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<APIResponse<String>> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exc, WebRequest request) {

        APIResponse<String> responseError = new APIResponse<>();
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        responseError.setMessage("Handle method error");
        responseError.setDetails(exc.getMessage());
        responseError.setPath(request.getDescription(false));

        return new ResponseEntity<>(responseError, HttpStatus.METHOD_NOT_ALLOWED);
    }


}

