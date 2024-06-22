package com.example.press_lab.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GenericException extends RuntimeException{

    private HttpStatus httpStatus;
    private Integer code;
    private String message;

}
