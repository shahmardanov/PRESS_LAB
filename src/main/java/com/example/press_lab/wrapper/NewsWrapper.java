package com.example.press_lab.wrapper;

import com.example.press_lab.enums.CategoryStatus;
import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.enums.SubCategoryStatus;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsWrapper {

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


    public NewsWrapper(Long id, String content){
        this.id = id;
        this.content = content;
    }

}
