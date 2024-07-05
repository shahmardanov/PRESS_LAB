package com.example.press_lab.response.news;

import com.example.press_lab.enums.NewsStatus;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsCreateResponse {

    private Long id;
    private String title;
    private String titleRu;
    private String titleEn;
    private String content;
    private String contentRu;
    private String contentEn;
    private String description;
    private String descriptionRu;
    private String descriptionEn;
    private Long viewCount;
    private String imageUrl;
    private NewsStatus status;
    private Long fkCategoryId;
    private Long fkSubCategoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
