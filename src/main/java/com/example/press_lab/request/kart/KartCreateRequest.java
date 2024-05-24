package com.example.press_lab.request.kart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class KartCreateRequest {

    private String title;
    private String content;
    private String imageUrl;
    private Long fkNewsId;
    private String description;
    private LocalDateTime createdAt;

}
