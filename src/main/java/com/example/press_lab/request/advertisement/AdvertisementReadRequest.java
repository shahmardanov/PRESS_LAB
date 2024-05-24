package com.example.press_lab.request.advertisement;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementReadRequest {

    private Long id;
    private String content;
    private String sourceUrl;
    private Long viewCount;

}
