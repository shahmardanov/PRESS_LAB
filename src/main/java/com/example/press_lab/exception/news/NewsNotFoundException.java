package com.example.press_lab.exception.news;

import com.example.press_lab.exception.GenericException;
import com.example.press_lab.exception.error.ErrorMessages;
import org.springframework.http.HttpStatus;

import java.util.Locale;

import static com.example.press_lab.exception.error.ErrorMessage.NEWS_NOT_FOUND;

public class NewsNotFoundException extends GenericException {

    public NewsNotFoundException(Locale locale, ErrorMessages errorMessages) {
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "error.news.not_found", locale, errorMessages);
    }

    public NewsNotFoundException(Long newsId, Locale locale, ErrorMessages errorMessages) {
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "error.news.not_found_with_id", locale, errorMessages);
    }
}
