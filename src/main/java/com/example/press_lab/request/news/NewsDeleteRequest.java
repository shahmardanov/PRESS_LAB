package com.example.press_lab.request.news;

import com.example.press_lab.enums.NewsStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class NewsDeleteRequest {

    private Long id;

}
