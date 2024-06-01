package com.example.press_lab.request.subCategory;

import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryRequest {

    private String  name;

    private Long fkCategoryId;

}
