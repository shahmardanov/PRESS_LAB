package com.example.press_lab.request.category;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$",message = "Write the category name correctly.")
    private String name;

}
