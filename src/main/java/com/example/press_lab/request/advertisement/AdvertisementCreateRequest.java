package com.example.press_lab.request.advertisement;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementCreateRequest {

    private String title;
    private String content;
    private String imageUrl;
    private String sourceUrl;
    private Long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
