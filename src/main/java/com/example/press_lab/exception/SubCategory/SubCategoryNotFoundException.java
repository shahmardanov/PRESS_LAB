package com.example.press_lab.exception.SubCategory;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.SUBCATEGORY_NOT_FOUND;

public class SubCategoryNotFoundException extends GenericException {

    public SubCategoryNotFoundException(){
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), SUBCATEGORY_NOT_FOUND);
    }

}
