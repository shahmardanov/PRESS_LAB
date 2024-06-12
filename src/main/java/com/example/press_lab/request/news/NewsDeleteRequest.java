package com.example.press_lab.request.news;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsDeleteRequest {

    @NotNull
    private Long id;

}
