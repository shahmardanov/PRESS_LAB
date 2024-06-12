package com.example.press_lab.request.subCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryRequest {

    @NotBlank
    private String  name;

    @NotBlank
    private String  nameRu;

    @NotBlank
    private String  nameEn;

    @NotNull
    private Long fkCategoryId;

}
