package com.example.press_lab.service;

import com.example.press_lab.entity.News;
import com.example.press_lab.mapper.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.response.NewsCardResponse;
import com.example.press_lab.response.NewsReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsReadService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;


    public NewsReadResponse getNewsById(int id) {

        return newsRepository.findById((long) id)
                .map(newsEntity ->{
                    if (newsEntity.getViewCount() == null) {
                        newsEntity.setViewCount(0L);
                    }
                    newsEntity.setViewCount(newsEntity.getViewCount() + 1);
                    newsRepository.save(newsEntity);
                    return newsMapper.toReadResponse(newsEntity);
                })
                .orElseThrow(() -> new RuntimeException("News not found with id " + id));
    }

    public List<NewsReadResponse> getAllNews() {
        return newsRepository.findAll().stream()
                .map(newsMapper::toReadResponse)
                .collect(Collectors.toList());
    }


    public Page<NewsCardResponse> getNewsPage(int page, int size) {
        Page<News> newsPage = newsRepository.findAll(PageRequest.of(page, size));
        return newsPage.map(newsMapper::toCardResponse);
    }
}
