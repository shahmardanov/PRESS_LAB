package com.example.press_lab.exception.user;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException (String code , String message){
        super(message);
    }
}
