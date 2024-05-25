package com.example.press_lab.service.news;


import com.example.press_lab.entity.News;
import com.example.press_lab.exception.news.NewsNotFoundException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsUpdateRequest;
import com.example.press_lab.response.news.NewsUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NewsUpdateService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public NewsUpdateResponse update(NewsUpdateRequest updateRequest){
        News news = newsRepository.findById(updateRequest.getId()).orElseThrow(NewsNotFoundException::new);

        if(Objects.nonNull(updateRequest.getTitle())){
            news.setTitle(updateRequest.getTitle());
        }
        if(Objects.nonNull(updateRequest.getContent())){
            news.setContent(updateRequest.getContent());
        }
        if(Objects.nonNull(updateRequest.getImageUrl())){
            news.setImageUrl(updateRequest.getImageUrl());
        }
        if(Objects.nonNull(updateRequest.getViewCount())){
            news.setViewCount(updateRequest.getViewCount());
        }
        if(Objects.nonNull(updateRequest.getDescription())){
            news.setDescription(updateRequest.getDescription());
        }
        if(Objects.nonNull(updateRequest.getStatus())){
            news.setStatus(updateRequest.getStatus());
        }
        if(Objects.nonNull(updateRequest.getCreatedAt())){
            news.setCreatedAt(updateRequest.getCreatedAt());
        }
        if(Objects.nonNull(updateRequest.getUpdatedAt())){
            news.setUpdatedAt(updateRequest.getUpdatedAt());
        }
        if(Objects.nonNull(updateRequest.getCategoryStatus())){
            news.setCategoryStatus(updateRequest.getCategoryStatus());
        }
        if(Objects.nonNull(updateRequest.getSubCategoryStatus())){
            news.setSubCategoryStatus(updateRequest.getSubCategoryStatus());
        }

        newsRepository.save(news);
        return newsMapper.mapUpdateToResponse(news);
    }

}
