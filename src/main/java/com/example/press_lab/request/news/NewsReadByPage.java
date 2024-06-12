package com.example.press_lab.request.news;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsReadByPage {

    @Min(0)
    private int page;

    @Min(0)
    private int size;

}
