package com.example.press_lab.request.user;

import com.example.press_lab.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import jakarta.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {


    private String name;
    private String surname;
    private String password;
    @Email
    private String email;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private String birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;



}
