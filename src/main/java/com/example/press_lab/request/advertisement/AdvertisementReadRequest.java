package com.example.press_lab.request.advertisement;

import lombok.*;

@Builder
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
