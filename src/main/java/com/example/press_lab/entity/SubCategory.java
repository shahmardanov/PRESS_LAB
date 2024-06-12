package com.example.press_lab.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "subCategories")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String nameRu;
    private String nameEn;
    private Long fkCategoryId;


}
