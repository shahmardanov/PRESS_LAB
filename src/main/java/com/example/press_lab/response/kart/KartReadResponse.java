package com.example.press_lab.response.kart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class KartReadResponse {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private Long fkNewsId;
    private String description;
    private LocalDateTime createdAt;

}
