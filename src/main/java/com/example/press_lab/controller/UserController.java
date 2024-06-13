package com.example.press_lab.controller;



import com.example.press_lab.request.user.AuthenticateRequest;
import com.example.press_lab.request.user.JwtDto;
import com.example.press_lab.request.user.UserCreateRequest;
import com.example.press_lab.response.user.UserCreateResponse;
import com.example.press_lab.service.user.UserCreateServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {



    private final UserCreateServiceImpl userCreateServiceImpl;



    @PostMapping("/registration")
    @ResponseStatus(CREATED)
    public ResponseEntity<UserCreateResponse> userRegistration(@RequestBody UserCreateRequest userCreateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userCreateServiceImpl.createUser(userCreateRequest));
    }

    @PostMapping("/verification")
    public ResponseEntity<Void> activate(@RequestParam("email") String email,
                                         @RequestParam("verificationCode") Integer verificationCode) {
        userCreateServiceImpl.activate(email, verificationCode);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resendVerificationCode")
    public ResponseEntity<Void> resendVerificationCode(@RequestParam("email") String email) {
        userCreateServiceImpl.resendVerificationCode(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/authenticate")
    public ResponseEntity<JwtDto> authenticate(@Valid @RequestBody AuthenticateRequest authenticateRequest) {
        return ResponseEntity.ok().body(userCreateServiceImpl.authenticate(authenticateRequest));
    }










}
