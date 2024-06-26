package com.example.press_lab.service.news;

import com.example.press_lab.entity.News;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsCreateRequest;
import com.example.press_lab.response.news.NewsCreateResponse;
import com.example.press_lab.service.subscriptionService.NotifySubscription;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

    @SneakyThrows
    @Transactional
    public NewsCreateResponse create(NewsCreateRequest createRequest){
        log.info("create service called with: {}",createRequest);
        News news = newsMapper.mapRequestToEntity(createRequest);
        News save = newsRepository.save(news);
        log.info("news created: {}",save);
        notifySubscription.notifySubscribers(save);
        return newsMapper.mapCreateToResponse(save);
    }

}
