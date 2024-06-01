package com.example.press_lab.service.news;


import com.example.press_lab.entity.News;
import com.example.press_lab.exception.news.NewsNotFoundException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsUpdateRequest;
import com.example.press_lab.response.news.NewsUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsUpdateService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public NewsUpdateResponse update(NewsUpdateRequest updateRequest){
        News news = newsRepository.findById(updateRequest.getId()).orElseThrow(NewsNotFoundException::new);
        News updatedNews = newsMapper.updateNewsToNewsUpdateResponse(updateRequest, news);
        News savedNews = newsRepository.save(updatedNews);
        return newsMapper.mapUpdateToResponse(savedNews);
    }

}
