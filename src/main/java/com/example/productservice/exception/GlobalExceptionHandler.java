package com.example.productservice.exception;


import com.example.productservice.dto.ErrorResponseAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseAPI> resourceNotFoundHandling(ResourceNotFoundException exception) {
        ErrorResponseAPI errorDetails = new ErrorResponseAPI(exception.getMessage());

        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponseAPI> handlingErrorResponse(ApplicationException exception) {
        ErrorResponseAPI errorDetails = new ErrorResponseAPI(exception.getErrors());
        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseAPI> globalExceptionHandling(Exception exception) {
        ErrorResponseAPI errorDetails = new ErrorResponseAPI(exception.getMessage());
        return ResponseEntity.badRequest().body(errorDetails);
    }
}
