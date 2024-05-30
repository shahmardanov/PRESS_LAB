package com.example.press_lab.service.news;

import com.example.press_lab.entity.News;
import com.example.press_lab.enums.CategoryStatus;
import com.example.press_lab.enums.SubCategoryStatus;
import com.example.press_lab.exception.news.*;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsReadRequest;
import com.example.press_lab.response.news.NewsReadResponse;
import com.example.press_lab.util.NewsCategoryCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.press_lab.enums.NewsStatus.ACTIVE;
import static com.example.press_lab.enums.SubCategoryStatus.ƏSASXƏBƏRLƏR;

@Service
@RequiredArgsConstructor
public class NewsReadService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final NewsCategoryCounter categoryCounter;


    public List<NewsReadResponse> getAllNews(){
        return newsRepository.findAll()
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getNewsByContent(NewsReadRequest readRequest){
        Optional<News> byContent = newsRepository.findByContent(readRequest.getContent());
        if(byContent.isEmpty()){
            throw new NewsContentNotFoundException();
        }
        return byContent
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getNewsByStatus(NewsReadRequest readRequest, int page, int size){
        PageRequest pageable = PageRequest.of(page, size);
        Page<News> byStatus = newsRepository.findByStatus(readRequest.getStatus(), pageable);
        if(byStatus.isEmpty()){
            throw new NewsNotFoundException();
        }
        return byStatus
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getActiveNews(int page, int size){
        Page<News> byStatus = newsRepository.findByStatus(ACTIVE, PageRequest.of(page, size));
        if(byStatus.isEmpty()){
            throw new NewsActiveNotFoundException();
        }
        return byStatus
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getNewsByCategory(CategoryStatus categoryStatus, int page, int size){
        Page<News> byStatusAndCategoryStatus = newsRepository.findByStatusAndCategoryStatus(ACTIVE, categoryStatus, PageRequest.of(page, size));
        if(byStatusAndCategoryStatus.isEmpty()){
            throw new NewsCategoryNotFoundException();
        }
        return byStatusAndCategoryStatus
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getNewsBySubCategory(SubCategoryStatus subCategoryStatus, int page, int size){
        Page<News> byStatusAndSubCategoryStatus = newsRepository.findByStatusAndSubCategoryStatus(ACTIVE, subCategoryStatus, PageRequest.of(page, size));
        if(byStatusAndSubCategoryStatus.isEmpty()){
            throw new NewsSubCategoryNotFoundException();
        }
        return byStatusAndSubCategoryStatus
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getNewsByCategoryAndSubCategory(CategoryStatus categoryStatus, SubCategoryStatus subCategoryStatus, int page, int size){
        if(subCategoryStatus == null){
            subCategoryStatus = ƏSASXƏBƏRLƏR;
        }
        Page<News> byStatusAndCategoryStatusAndSubCategoryStatus = newsRepository.findByStatusAndCategoryStatusAndSubCategoryStatus(ACTIVE, categoryStatus, subCategoryStatus, PageRequest.of(page, size));
        if(byStatusAndCategoryStatusAndSubCategoryStatus.isEmpty()){
            throw new NewsNotFoundException();
        }
        categoryCounter.incrementViewCount(categoryStatus, subCategoryStatus);
        return byStatusAndCategoryStatusAndSubCategoryStatus
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

}
