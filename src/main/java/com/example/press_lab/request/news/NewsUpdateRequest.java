package com.example.press_lab.request.news;

import com.example.press_lab.enums.NewsStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsUpdateRequest {

    @NotNull
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private String description;

    @NotBlank
    private NewsStatus status;

    @NotNull
    private Long fkCategoryId;

    @NotNull
    private Long fkSubCategoryId;

}
