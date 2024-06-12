package com.example.press_lab.exception.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalDateValidException extends RuntimeException {

    private String message;

    public LocalDateValidException(String message) {
        super(message);
        this.message = message;
    }
}


