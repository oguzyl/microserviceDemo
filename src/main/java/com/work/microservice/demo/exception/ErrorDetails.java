package com.work.microservice.demo.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorDetails {

    private HttpStatus httpStatus;
    private LocalDateTime dateTime;
    private String message;
    private String details;

    public ErrorDetails(HttpStatus httpStatus, LocalDateTime dateTime, String message, String details) {
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
        this.message = message;
        this.details = details;
    }
}
