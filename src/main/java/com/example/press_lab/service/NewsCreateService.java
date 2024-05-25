package com.example.press_lab.service;

import com.example.press_lab.entity.News;
import com.example.press_lab.mapper.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.NewsCreateRequest;
import com.example.press_lab.response.NewsReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsCreateService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;


    public NewsReadResponse createNews(NewsCreateRequest request) {
        News news = newsMapper.toCreateEntity(request);

        News savedNews = newsRepository.save(news);
        return newsMapper.toReadResponse(savedNews);
    }

}
