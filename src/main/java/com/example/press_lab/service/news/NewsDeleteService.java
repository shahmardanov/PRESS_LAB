package com.example.press_lab.service.news;

import com.example.press_lab.entity.News;
import com.example.press_lab.exception.news.NewsNotFoundException;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsDeleteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.press_lab.enums.NewsStatus.INACTIVE;

@Service
@RequiredArgsConstructor
public class NewsDeleteService {
    private final NewsRepository newsRepository;

    public void deleteAll(){
        newsRepository.deleteAll();
    }
    public void deleteById(NewsDeleteRequest deleteRequest){
        News news = newsRepository.findById(deleteRequest.getId()).orElseThrow(NewsNotFoundException::new);
        news.setStatus(INACTIVE);
        newsRepository.save(news);
    }

    private String extractPublicIdFromUrl(String url) {
        String[] parts = url.split("/");
        String publicIdWithExtension = parts[parts.length - 1];
        return publicIdWithExtension.split("\\.")[0];
    }

}
