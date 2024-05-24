package com.example.press_lab.service.news;

import com.example.press_lab.entity.News;
import com.example.press_lab.exception.advertisement.AdvertisementContentNotFoundException;
import com.example.press_lab.exception.news.NewsContentNotFoundException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsCreateRequest;
import com.example.press_lab.response.news.NewsCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NewsCreateService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public NewsCreateResponse create(NewsCreateRequest createRequest){
        if(Objects.nonNull(newsRepository.findByContent(createRequest.getContent()))){
            throw new NewsContentNotFoundException();
        }
        News news = newsMapper.mapRequestToEntity(createRequest);
        News savedNews = newsRepository.save(news);
        return newsMapper.mapCreateToResponse(savedNews);
    }

}
