package com.example.press_lab.entity;

<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.*;
=======

import lombok.*;
import jakarta.persistence.*;
>>>>>>> 9b7efc9 (Specification)

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
