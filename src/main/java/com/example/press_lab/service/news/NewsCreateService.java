package com.example.press_lab.service.news;

import com.example.press_lab.entity.News;
import com.example.press_lab.exception.news.NewsConflictException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsCreateRequest;
import com.example.press_lab.response.news.NewsCreateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsCreateService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public NewsCreateResponse create(NewsCreateRequest createRequest){
        if(Objects.nonNull(newsRepository.findByContent(createRequest.getContent()))){
            throw new NewsConflictException();
        }
        log.info("create giris");
        News news = newsMapper.mapRequestToEntity(createRequest);
        log.info("news gordu");
        News savedNews = newsRepository.save(news);
        log.info("savedNews gordu");
        return newsMapper.mapCreateToResponse(savedNews);
    }
}
