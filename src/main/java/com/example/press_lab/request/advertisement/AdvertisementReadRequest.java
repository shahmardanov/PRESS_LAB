package com.example.press_lab.request.advertisement;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementReadRequest {

    @NotNull
    private Long id;

    @NotBlank
    private String content;

    @NotBlank
    private String sourceUrl;

    @NotNull
    private Long viewCount;

}
