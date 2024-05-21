package com.example.press_lab.entity;

import com.example.press_lab.enums.CategoryStatus;
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

    private CategoryStatus categoryStatus;

}
