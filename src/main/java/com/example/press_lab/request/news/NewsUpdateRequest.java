package com.example.press_lab.request.news;

import com.example.press_lab.enums.NewsStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsUpdateRequest {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private String description;
    private NewsStatus status;
    private Long fkCategoryId;
    private Long fkSubCategoryId;

}
