package com.example.press_lab.exception.advertisement;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.ADVERTISEMENT_SOURCE_URL_NOT_FOUND;

public class AdvertisementSourceUrlNotFoundException extends GenericException {

    public AdvertisementSourceUrlNotFoundException(){
        super(HttpStatus.NOT_FOUND, HttpStatus.NO_CONTENT.value(), ADVERTISEMENT_SOURCE_URL_NOT_FOUND);
    }
}
