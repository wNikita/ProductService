package com.example.productservice.exception;


import java.util.List;

public class ApplicationException extends RuntimeException {

    private List<FieldError> errors;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable ex) {
        super(message, ex);
    }


    public ApplicationException(List<FieldError> errors) {
        super("Errors occurred");
        this.errors = errors;
    }

    public List<FieldError> getErrors() {
        return errors;
    }
}
