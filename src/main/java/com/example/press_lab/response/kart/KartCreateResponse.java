package com.example.press_lab.response.kart;

import java.time.LocalDateTime;

public class KartCreateResponse {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private Long fkNewsId;
    private String description;
    private LocalDateTime createdAt;

}
