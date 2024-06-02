package com.example.press_lab.service.news;

import com.example.press_lab.entity.News;
import com.example.press_lab.exception.news.NewsConflictException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsCreateRequest;
import com.example.press_lab.response.news.NewsCreateResponse;
import com.example.press_lab.service.subscriptionService.NotifySubscription;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsCreateService {

    private final NewsRepository newsRepository;

    private final NotifySubscription notifySubscription;

    private final NewsMapper newsMapper;

    @Transactional
    public NewsCreateResponse create(NewsCreateRequest createRequest){
        if(newsRepository.findByContent(createRequest.getContent()).isPresent()){
            throw new NewsConflictException();
        }
        News news = newsMapper.mapRequestToEntity(createRequest);
        newsRepository.save(news);
        notifySubscription.notifySubscribers(news);
        return newsMapper.mapCreateToResponse(news);
    }

}
