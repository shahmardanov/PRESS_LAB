package com.example.press_lab.service.news;

import com.example.press_lab.entity.News;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsCreateRequest;
import com.example.press_lab.response.news.NewsCreateResponse;
import com.example.press_lab.service.cloudinary.CloudinaryService;
import com.example.press_lab.service.subscriptionService.NotifySubscription;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsCreateService {

    private final NewsRepository newsRepository;

    private final CloudinaryService cloudinaryService;

    private final NotifySubscription notifySubscription;

    private final NewsMapper newsMapper;

    @Transactional
    public NewsCreateResponse create(NewsCreateRequest createRequest) throws IOException {
        String imageUrl = cloudinaryService.uploadImage(createRequest.getImage());
        News news = newsMapper.mapRequestToEntity(createRequest);
        news.setImageUrl(imageUrl);
        News save = newsRepository.save(news);
        log.info("news created: {}", save);
//        notifySubscription.notifySubscribers(save);
        return newsMapper.mapCreateToResponse(save);
    }

}
