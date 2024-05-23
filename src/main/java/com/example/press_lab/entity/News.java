package com.example.press_lab.entity;

import com.example.press_lab.enums.CategoryStatus;
import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.enums.SubCategoryStatus;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "news")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String content;
    private String imageUrl;
    private Long viewCount;
    private String description;

    @Enumerated(EnumType.STRING)
    private NewsStatus status;

    @Enumerated(EnumType.STRING)
    private CategoryStatus categoryStatus;

    @Enumerated(EnumType.STRING)
    private SubCategoryStatus subCategoryStatus;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


}
