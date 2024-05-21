package com.example.press_lab.entity;

import com.example.press_lab.enums.CategoryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "advertisements")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;

    @Lob
    private String content;
    private String imageUrl;
    private Long viewCount;
    private LocalDateTime createdAt;

}
