package com.example.press_lab.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "aboutUs")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AboutUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String content;
    private String imageUrl;

}
