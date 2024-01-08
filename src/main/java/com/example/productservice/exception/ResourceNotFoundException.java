package com.example.productservice.exception;

import java.util.List;

public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }

    public ResourceNotFoundException(List<FieldError> errors) {
        super(errors);
    }
}
