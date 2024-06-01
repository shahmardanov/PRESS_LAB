package com.example.press_lab.exception.news;

import com.example.press_lab.exception.GenericException;
import com.example.press_lab.exception.error.ErrorMessage;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.NEWS_ALREADY_EXISTS;

public class NewsConflictException extends GenericException {

    public NewsConflictException(){
        super(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), NEWS_ALREADY_EXISTS);
    }
}
