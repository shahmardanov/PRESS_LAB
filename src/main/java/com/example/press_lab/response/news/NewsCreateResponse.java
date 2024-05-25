package com.example.press_lab.response.news;

import com.example.press_lab.enums.CategoryStatus;
import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.enums.SubCategoryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Service
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
    private CategoryStatus categoryStatus;
    private SubCategoryStatus subCategoryStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
