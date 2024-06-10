package com.example.press_lab.exception;

import com.example.press_lab.exception.error.ErrorMessages;
import com.example.press_lab.util.LocaleResolverUtil;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Locale;

public class GenericException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    public GenericException(HttpStatus httpStatus, int code, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public GenericException(HttpStatus httpStatus, int code, String message, Locale locale, LocaleResolverUtil localeResolverUtil) {
        super(localeResolverUtil.getMessage(message, locale));
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public GenericException(HttpStatus httpStatus, int code, String message, Locale locale, ErrorMessages errorMessages) {
        super(errorMessages.getMessage(message, locale));
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
