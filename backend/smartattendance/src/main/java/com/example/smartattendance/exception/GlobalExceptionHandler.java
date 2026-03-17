package com.example.smartattendance.exception;

import com.example.smartattendance.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> notFound(ResourceNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(ex.getMessage()));
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiResponse<Void>> business(BusinessException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(ex.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> validation(MethodArgumentNotValidException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("Validation failed"));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> generic(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Unexpected error"));
  }
}

