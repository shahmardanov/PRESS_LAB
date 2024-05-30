package com.example.press_lab.service.news;

import com.example.press_lab.enums.CategoryStatus;
import com.example.press_lab.enums.SubCategoryStatus;
import com.example.press_lab.exception.news.NewsContentNotFoundException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsReadRequest;
import com.example.press_lab.response.news.NewsReadResponse;
import com.example.press_lab.util.NewsCategoryCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<NewsReadResponse> getNewsByCategory(CategoryStatus categoryStatus, int page, int size){
        return newsRepository.findByStatusAndCategoryStatus(ACTIVE, categoryStatus, PageRequest.of(page,size))
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getNewsBySubCategory(SubCategoryStatus subCategoryStatus, int page, int size){
        return newsRepository.findByStatusAndSubCategoryStatus(ACTIVE, subCategoryStatus, PageRequest.of(page,size))
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getNewsByCategoryAndSubCategory(CategoryStatus categoryStatus, SubCategoryStatus subCategoryStatus, int page, int size){
        if(subCategoryStatus == null){
            subCategoryStatus = ƏSASXƏBƏRLƏR;
        }
        categoryCounter.incrementViewCount(categoryStatus, subCategoryStatus);
        return newsRepository.findByStatusAndCategoryStatusAndSubCategoryStatus(ACTIVE, categoryStatus, subCategoryStatus, PageRequest.of(page,size))
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

}
