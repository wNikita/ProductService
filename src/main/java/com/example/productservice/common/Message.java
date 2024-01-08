package com.example.productservice.common;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Message {

    private static MessageSourceAccessor messageSourceAccessor;
    @Autowired
    private MessageSource messageSource;

    @PostConstruct
    private void setup() {
        messageSourceAccessor = new MessageSourceAccessor(this.messageSource);
    }

    public static String get(String code, Object[] params, String defaultMessage) {
        return messageSourceAccessor.getMessage(code, params, defaultMessage, Locale.ENGLISH);
    }

    public static String get(String code) {
        return get(code, null, null);
    }
}
