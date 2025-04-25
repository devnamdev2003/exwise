package com.exwise.exwise.exception;

import com.exwise.exwise.dto.response.ApiResponse;
import com.exwise.exwise.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFound(ResourceNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(
                "error", HttpStatus.NOT_FOUND.value(), ex.getMessage(), null, true
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(Exception ex) {
        ApiResponse<String> response = new ApiResponse<>(
                "error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null, true
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
