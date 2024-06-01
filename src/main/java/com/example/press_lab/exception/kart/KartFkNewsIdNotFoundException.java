package com.example.press_lab.exception.kart;

import com.example.press_lab.exception.GenericException;
import com.example.press_lab.exception.error.ErrorMessage;
import org.springframework.http.HttpStatus;

public class KartFkNewsIdNotFoundException extends GenericException {

    public KartFkNewsIdNotFoundException(){
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), ErrorMessage.KART_FK_NEWS_ID_NOT_FOUND);
    }

}
