package com.example.press_lab.exception.user;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException (String code, String message){
        super(message);
    }
}
