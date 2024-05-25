package com.example.press_lab.request.news;

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
public class NewsUpdateRequest {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private Long viewCount;
    private NewsStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CategoryStatus categoryStatus;
    private SubCategoryStatus subCategoryStatus;

}
