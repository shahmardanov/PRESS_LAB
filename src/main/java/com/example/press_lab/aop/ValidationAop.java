package com.example.press_lab.aop;


import com.example.press_lab.exception.user.LocalDateValidException;
import com.example.press_lab.request.user.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ValidationAop {

    @Before("@annotation(com.example.press_lab.annotation.LocalDatePattern)")
    public void validateDatePattern(JoinPoint joinPoint) {
        try {
            UserCreateRequest userCreateRequest = (UserCreateRequest) joinPoint.getArgs()[0];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate.parse(userCreateRequest.getBirthDate(), formatter);
        } catch (Exception ex) {
            throw new LocalDateValidException("Date format must be like this dd-MM-yyyy");
        }
    }
}


