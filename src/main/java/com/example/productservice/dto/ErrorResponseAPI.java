package com.example.productservice.dto;


import com.example.productservice.exception.FieldError;

import java.util.List;

public class ErrorResponseAPI {
    private String errorMessage;
    private List<FieldError> fieldError;
    public ErrorResponseAPI(List<FieldError> errors) {
        this.fieldError= errors;
    }
    public ErrorResponseAPI(String errorMessage)
    {
        this.errorMessage=errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<FieldError> getFieldError() {
        return fieldError;
    }

    public void setFieldError(List<FieldError> fieldError) {
        this.fieldError = fieldError;
    }
}
