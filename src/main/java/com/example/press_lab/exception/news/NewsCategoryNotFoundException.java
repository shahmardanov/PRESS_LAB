package com.example.press_lab.exception.news;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.NEWS_CATEGORY_NOT_FOUND;

public class NewsCategoryNotFoundException extends GenericException {

    public NewsCategoryNotFoundException(){
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), NEWS_CATEGORY_NOT_FOUND);
    }
}
