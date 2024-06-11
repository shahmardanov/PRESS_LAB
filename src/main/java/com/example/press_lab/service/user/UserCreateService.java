package com.example.press_lab.service.user;


import com.example.press_lab.request.user.AuthenticateRequest;
import com.example.press_lab.request.user.JwtDto;
import com.example.press_lab.request.user.UserCreateRequest;
import com.example.press_lab.response.user.UserCreateResponse;

public interface UserCreateService {


    UserCreateResponse createUser(UserCreateRequest userCreateRequest);

    void activate(String email, Integer verificationCode);

    void resendVerificationCode(String email);

    JwtDto authenticate(AuthenticateRequest authenticateRequest);

}
