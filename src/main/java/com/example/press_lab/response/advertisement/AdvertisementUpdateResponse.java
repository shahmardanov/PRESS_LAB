package com.example.press_lab.response.advertisement;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementUpdateResponse {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private String sourceUrl;
    private Long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
