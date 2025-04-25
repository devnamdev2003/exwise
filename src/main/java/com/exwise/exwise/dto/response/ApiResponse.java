package com.exwise.exwise.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse<T> {

    private String status;
    private int statusCode;
    private String message;
    private T data;
    private Boolean error;
    private LocalDateTime timestamp = LocalDateTime.now();


    public ApiResponse(String status, int statusCode, String message, T data, Boolean error) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.error = error;
    }

    // Getters & Setters
}
