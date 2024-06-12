package com.example.press_lab.exception.user;

public class UserExitsException extends RuntimeException{

    public UserExitsException (String code , String message){
        super(message);
    }
}
