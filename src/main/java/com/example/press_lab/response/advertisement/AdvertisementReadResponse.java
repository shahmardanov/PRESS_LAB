package com.example.press_lab.response.advertisement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementReadResponse {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private String sourceUrl;
    private Long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
