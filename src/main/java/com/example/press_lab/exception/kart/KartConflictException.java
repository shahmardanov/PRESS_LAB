package com.example.press_lab.exception.kart;

import com.example.press_lab.exception.GenericException;
import org.springframework.http.HttpStatus;

import static com.example.press_lab.exception.error.ErrorMessage.KART_ALREADY_EXISTS;

public class KartConflictException extends GenericException {

    public KartConflictException(){
        super(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), KART_ALREADY_EXISTS);
    }
}
