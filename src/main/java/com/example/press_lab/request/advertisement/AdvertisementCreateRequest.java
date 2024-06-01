package com.example.press_lab.request.advertisement;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

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
