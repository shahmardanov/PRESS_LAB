package com.example.press_lab.response.news;

import com.example.press_lab.enums.NewsStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsCreateResponse {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private Long viewCount;
    private String description;
    private NewsStatus status;
    private Long fkCategoryId;
    private Long fkSubCategoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
