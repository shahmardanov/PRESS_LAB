package com.example.press_lab.service.news;

import com.example.press_lab.enums.CategoryStatus;
import com.example.press_lab.enums.SubCategoryStatus;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.response.news.NewsReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.press_lab.enums.NewsStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class NewsCategoryService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;


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
            return newsRepository.findByStatusAndCategoryStatus(ACTIVE, categoryStatus, PageRequest.of(page,size))
                    .stream()
                    .map(newsMapper::mapReadToResponse)
                    .toList();
        }else{
            return newsRepository.findByStatusAndCategoryStatusAndSubCategoryStatus(ACTIVE, categoryStatus, subCategoryStatus, PageRequest.of(page,size))
                    .stream()
                    .map(newsMapper::mapReadToResponse)
                    .toList();
        }
    }


}
