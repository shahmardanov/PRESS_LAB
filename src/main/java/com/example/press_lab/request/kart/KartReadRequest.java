package com.example.press_lab.request.kart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KartReadRequest {

    private String content;
    private Long fkNewsId;
    private String description;
    private LocalDateTime createdAt;

}
