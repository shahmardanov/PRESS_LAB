package com.example.press_lab.entity;

import com.example.press_lab.enums.SubCategoryStatus;
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

    private SubCategoryStatus subCategoryStatus;
    private Long fkCategoryId;

}
