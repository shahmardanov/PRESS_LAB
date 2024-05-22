package com.example.press_lab.entity;

import com.example.press_lab.enums.NewsStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "news")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String content;
    private String imageUrl;
    private Long viewCount;

    @Enumerated(EnumType.STRING)
    private NewsStatus newsStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long fkCategoryId;
    private Long fkSubCategoryId;

}
