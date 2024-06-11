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
    private String titleRu;

    @NotBlank
    private String titleEn;

    @NotBlank
    private String description;

    @NotBlank
    private String descriptionRu;

    @NotBlank
    private String descriptionEn;

    @NotBlank
    private String content;

    @NotBlank
    private String contentRu;

    @NotBlank
    private String contentEn;

    @NotBlank
    private String imageUrl;

    @NotNull
    private Long fkCategoryId;

    @NotNull
    private Long fkSubCategoryId;

}
