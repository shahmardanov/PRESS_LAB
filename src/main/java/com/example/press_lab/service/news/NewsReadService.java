package com.example.press_lab.service.news;

import com.example.press_lab.exception.news.NewsContentNotFoundException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsReadRequest;
import com.example.press_lab.response.news.NewsReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.press_lab.enums.NewsStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class NewsReadService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public List<NewsReadResponse> getAllNews(){
        return newsRepository.findAll()
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getNewsByContent(NewsReadRequest readRequest){
        return newsRepository.findByContent(readRequest.getContent())
                .stream()
                .peek(news -> {
                    if(newsRepository.findByContent(readRequest.getContent()).isEmpty()){
                        throw new NewsContentNotFoundException();
                    }
                })
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getNewsByStatus(NewsReadRequest readRequest, int page, int size){
        return newsRepository.findByStatus(readRequest.getStatus(), PageRequest.of(page, size))
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getActiveNews(int page, int size){
        return newsRepository.findByStatus(ACTIVE, PageRequest.of(page, size))
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

}
