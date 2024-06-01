package com.example.press_lab.exception.advertisement;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.ADVERTISEMENT_NOT_FOUND;

public class AdvertisementNotFoundException extends GenericException {

    public AdvertisementNotFoundException(){
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), ADVERTISEMENT_NOT_FOUND);
    }

}
