package com.example.press_lab.exception.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationException extends RuntimeException{

    private String message;

    public VerificationException(String message) {
        super(message);
        this.message = message;
    }
}

