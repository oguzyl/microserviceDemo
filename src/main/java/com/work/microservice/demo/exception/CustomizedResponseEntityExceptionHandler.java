package com.work.microservice.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

      @ExceptionHandler(UserNotFoundException.class)
      public ResponseEntity<Object> handleAllUserNotFoundEx(Exception ex, WebRequest request) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
            return handleExceptionInternal(ex,errorDetails, new HttpHeaders(), HttpStatus.NOT_FOUND ,request);
      }
}
