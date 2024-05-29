package com.example.press_lab.service.news;


import com.example.press_lab.entity.News;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.response.news.NewsReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsRecentService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public List<NewsReadResponse> getRecentNews(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<News> newsPage = newsRepository.findAll(pageable);
        return newsPage.stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

}
