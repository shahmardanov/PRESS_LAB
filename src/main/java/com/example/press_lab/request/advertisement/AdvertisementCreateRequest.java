package com.example.press_lab.request.advertisement;


import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementCreateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private String sourceUrl;

}
