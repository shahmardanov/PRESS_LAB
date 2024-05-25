package com.example.press_lab.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsCardResponse {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private Long viewCount;
    private String imageUrl;
}
