package com.example.press_lab.entity;

import com.example.press_lab.enums.NewsStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.example.press_lab.enums.NewsStatus.ACTIVE;

@Table(name = "news")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String titleRu;
    private String titleEn;
    private String description;
    private String descriptionRu;
    private String descriptionEn;
    private Long viewCount;
    private Long fkCategoryId;
    private Long fkSubCategoryId;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String contentRu;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String contentEn;

    @Lob
    private byte[] image;

    @Enumerated(EnumType.STRING)
    private NewsStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @PrePersist
    public void setStatusAndViewCount() {
        if (status == null) {
            status=ACTIVE;
        }
        if (viewCount == null) {
            viewCount=0L;
        }
    }

}
