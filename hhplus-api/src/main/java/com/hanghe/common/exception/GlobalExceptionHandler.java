package com.hanghe.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleApiException(BusinessException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
               ex.hashCode(),
                ex.getMessage()
        );
        return ResponseEntity.status(ex.hashCode()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                500,
                "서버 에러"
        );
        return ResponseEntity.status(500).body(errorResponse);
    }
}
