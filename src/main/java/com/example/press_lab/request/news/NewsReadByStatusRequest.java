package com.example.press_lab.request.news;

import com.example.press_lab.enums.NewsStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsReadByStatusRequest {

    @NotNull
    private NewsStatus status;

}
