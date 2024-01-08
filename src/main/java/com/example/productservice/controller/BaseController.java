package com.example.productservice.controller;

import com.example.productservice.dto.APIResponse;
import com.example.productservice.exception.FieldError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;


import java.util.ArrayList;
import java.util.List;

public class BaseController {

    @Autowired
    private MessageSource messageSource;

    public <T> APIResponse<T> prepareSuccessResponse(T data) {
        APIResponse<T> responseVO = new APIResponse<>();
        responseVO.setData(data);
        return responseVO;
    }


    public List<FieldError> buildErrorResponse(BindingResult bindingResult) {
        List<FieldError> errorDetailsList = new ArrayList<>();

        for (org.springframework.validation.FieldError fieldError : bindingResult.getFieldErrors()) {
            String field = fieldError.getField();
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            FieldError errorDetails = new FieldError(field, message);
            errorDetailsList.add(errorDetails);
        }
        return errorDetailsList;
    }

}
