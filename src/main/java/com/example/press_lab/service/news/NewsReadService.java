package com.example.press_lab.service.news;

import com.example.press_lab.exception.news.NewsContentNotFoundException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsReadRequest;
import com.example.press_lab.response.news.NewsReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.press_lab.enums.NewsStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class NewsReadService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public List<NewsReadResponse> getAll(){
        return newsRepository.findAll()
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getContent(NewsReadRequest readRequest){
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

    public List<NewsReadResponse> getStatus(NewsReadRequest readRequest){
        return newsRepository.findByStatus(readRequest.getStatus())
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getActiveStatus(NewsReadRequest readRequest){
        return newsRepository.findByStatus(ACTIVE)
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

}
