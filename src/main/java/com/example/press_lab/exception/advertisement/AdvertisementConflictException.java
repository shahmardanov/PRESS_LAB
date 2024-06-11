package com.example.press_lab.exception.advertisement;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.ADVERTISEMENT_ALREADY_EXISTS;

public class AdvertisementConflictException extends GenericException {

    public AdvertisementConflictException(){
        super(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), ADVERTISEMENT_ALREADY_EXISTS);
    }
}
