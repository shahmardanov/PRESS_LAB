package com.example.press_lab.exception.error;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Builder
public class ErrorResponse {

    String code;
    String message;
}
