package com.example.press_lab.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateResponse {

    private String name;
    private String surname;
    private String username;
    private int age;
    private String password;
    private String email;
}
