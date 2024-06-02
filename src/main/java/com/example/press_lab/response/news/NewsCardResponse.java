package com.example.press_lab.response.news;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsCardResponse {

    private Long id;
    private String title;
    private String image;
    private Long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
