package com.example.press_lab.request.news;

import com.example.press_lab.enums.NewsStatus;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsReadRequest {

    private Long id;
    private String content;
    private NewsStatus status;

}
