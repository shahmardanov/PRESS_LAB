package com.example.press_lab.exception.news;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.NEWS_STATUS_ACTIVE;
import static com.example.press_lab.exception.error.ErrorMessage.NEWS_STATUS_INACTIVE;

public class NewsStatusInactiveException extends GenericException {

    public NewsStatusInactiveException(){
        super(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), NEWS_STATUS_INACTIVE);
    }
}
