package com.example.press_lab.exception.news;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.NEWS_CONTENT_NOT_FOUND;
import static com.example.press_lab.exception.error.ErrorMessage.NEWS_NOT_FOUND;

public class NewsContentNotFoundException extends GenericException {

    public NewsContentNotFoundException(){
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), NEWS_CONTENT_NOT_FOUND);
    }

}
