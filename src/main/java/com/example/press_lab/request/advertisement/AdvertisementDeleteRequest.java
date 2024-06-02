package com.example.press_lab.request.advertisement;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDeleteRequest {

    @NotNull
    private Long id;

}
