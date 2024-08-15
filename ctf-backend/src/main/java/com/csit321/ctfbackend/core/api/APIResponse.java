package com.csit321.ctfbackend.core.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

// Class representing a standardized API response.
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@Setter
@NoArgsConstructor
public class APIResponse<T> {

    // Timestamp of the response.
    private LocalDateTime timestamp;

    // HTTP status code of the response.
    private int status;

    // Message describing the response.
    private String message;

    // Path of the request that generated the response.
    private String path;

    // Details of the response.
    private T details;

    // Constructor to initialize all fields.
    public APIResponse(LocalDateTime timestamp, int status, T details, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.details = details;
        this.message = message;
        this.path = path;
    }

    // Static method to build a ResponseEntity with the given data, message, status, and request.
    public static <T> ResponseEntity<?> build(T data, String message, HttpStatus status, WebRequest request) {
        APIResponse<T> response = new APIResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status.value());
        response.setMessage(message);
        response.setDetails(data);
        response.setPath(request.getDescription(false));
        return new ResponseEntity<>(response, status);
    }
}
