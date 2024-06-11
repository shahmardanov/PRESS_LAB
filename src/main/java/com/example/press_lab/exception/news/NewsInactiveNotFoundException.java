package com.example.press_lab.exception.news;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.*;

public class NewsInactiveNotFoundException extends GenericException {

    public NewsInactiveNotFoundException(){
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), NEWS_INACTIVE_NOT_FOUND);
    }
}
