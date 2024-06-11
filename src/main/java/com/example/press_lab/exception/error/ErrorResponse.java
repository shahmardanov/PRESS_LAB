package com.example.press_lab.exception.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private LocalDate timestamp;
    private String message;
    private Integer statusCode;
    private String path;
    private List<String> validationsMessages;

    public ErrorResponse(String message, Integer statusCode) {
    this.message=message;
    this.statusCode=statusCode;
    }
//    private String severity;
//    private String info;

}
