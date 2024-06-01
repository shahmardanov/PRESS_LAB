package com.example.press_lab.exception.kart;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.KART_CONTENT_NOT_FOUND;

public class KartContentNotFoundException extends GenericException {

    public KartContentNotFoundException(){
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), KART_CONTENT_NOT_FOUND);
    }

}
