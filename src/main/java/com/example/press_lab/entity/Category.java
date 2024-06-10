package com.example.press_lab.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "categories")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String nameRu;
    private String nameEn;

}
