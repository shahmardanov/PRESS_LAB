package com.example.press_lab.exception.news;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.*;

public class NewsActiveNotFoundException extends GenericException {

    public NewsActiveNotFoundException(){
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), NEWS_ACTIVE_NOT_FOUND);
    }

}
