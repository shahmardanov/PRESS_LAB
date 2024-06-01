package com.example.press_lab.response.news;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
