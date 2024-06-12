package com.example.press_lab.exception.kart;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.KART_NOT_FOUND;


public class KartNotFoundException extends GenericException {

    public KartNotFoundException(){
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), KART_NOT_FOUND);
    }

}
