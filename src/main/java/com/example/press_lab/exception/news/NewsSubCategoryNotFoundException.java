package com.example.press_lab.exception.news;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.NEWS_SUBCATEGORY_NOT_FOUND;

public class NewsSubCategoryNotFoundException extends GenericException {

    public NewsSubCategoryNotFoundException(){
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), NEWS_SUBCATEGORY_NOT_FOUND);
    }

}
