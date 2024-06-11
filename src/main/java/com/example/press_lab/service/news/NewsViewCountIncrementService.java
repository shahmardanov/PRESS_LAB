package com.example.press_lab.service.news;

import com.example.press_lab.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsViewCountIncrementService {

    private final NewsRepository newsRepository;

    public void incrementNewsViewCount(Long newsId) {
        newsRepository.incrementViewCount(newsId);
    }

}
