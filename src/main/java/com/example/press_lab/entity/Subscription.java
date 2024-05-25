package com.example.press_lab.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "subscriptions")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String email;

}
