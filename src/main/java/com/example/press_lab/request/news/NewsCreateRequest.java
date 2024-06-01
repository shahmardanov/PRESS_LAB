package com.example.press_lab.request.news;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsCreateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private String description;

    @NotNull
    private Long fkCategoryId;

    @NotNull
    private Long fkSubCategoryId;

}
