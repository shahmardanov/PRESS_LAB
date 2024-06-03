package com.example.press_lab.service.news;

import com.example.press_lab.entity.News;
import com.example.press_lab.exception.news.NewsNotFoundException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.response.news.NewsReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsReadService {

    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;

    public NewsReadResponse getNewsById(Long newsId) {
        News news = newsRepository.findById(newsId).orElseThrow(NewsNotFoundException::new);
        return newsMapper.mapReadToResponse(news);
    }

}
