package com.example.press_lab.request.advertisement;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementReadRequest {

    private Long id;
    private String content;
    private String sourceUrl;
    private Long viewCount;

}
