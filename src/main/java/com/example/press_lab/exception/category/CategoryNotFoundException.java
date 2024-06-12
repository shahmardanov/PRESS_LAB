package com.example.press_lab.exception.category;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.CATEGORY_NOT_FOUND;

public class CategoryNotFoundException extends GenericException {

    public CategoryNotFoundException(){
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), CATEGORY_NOT_FOUND);
    }

}
