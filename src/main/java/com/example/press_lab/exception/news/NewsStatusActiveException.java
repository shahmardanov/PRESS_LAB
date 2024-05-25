package com.example.press_lab.exception.news;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.*;

public class NewsStatusActiveException extends GenericException {

    public NewsStatusActiveException(){
        super(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), NEWS_STATUS_ACTIVE);
    }

}
