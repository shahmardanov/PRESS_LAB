package com.example.press_lab.request.news;


import com.example.press_lab.enums.NewsStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsCreateRequest {

    private String title;
    private String content;
    private String imageUrl;
    private String description;
    private Long fkCategoryId;
    private Long fkSubCategoryId;

}
