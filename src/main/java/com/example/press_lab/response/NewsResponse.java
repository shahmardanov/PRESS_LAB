package com.example.press_lab.response;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse implements Serializable {

    private String title;
    private String content;
    private String imageUrl;
    private Long viewCount;
    private String description;
}
