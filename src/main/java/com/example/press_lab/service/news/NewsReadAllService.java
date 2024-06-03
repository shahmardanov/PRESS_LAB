package com.example.press_lab.service.news;

import com.example.press_lab.entity.Category;
import com.example.press_lab.entity.News;
import com.example.press_lab.entity.SubCategory;
import com.example.press_lab.exception.SubCategory.SubCategoryNotFoundException;
import com.example.press_lab.exception.category.CategoryNotFoundException;
import com.example.press_lab.exception.news.*;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.CategoryRepository;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.repository.SubCategoryRepository;
import com.example.press_lab.request.news.*;
import com.example.press_lab.response.news.NewsCardResponse;
import com.example.press_lab.response.news.NewsReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.press_lab.enums.NewsStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class NewsReadAllService {
    private final NewsRepository newsRepository;

    private final CategoryRepository categoryRepository;

    private final SubCategoryRepository subCategoryRepository;

    private final NewsMapper newsMapper;

    public List<NewsReadResponse> getAllNews() {
        return newsRepository.findAll()
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsReadResponse> getNewsByContent(NewsReadRequest readRequest) {
        Optional<News> byContent = newsRepository.findByContent(readRequest.getContent());
        if (byContent.isEmpty()) {
            throw new NewsContentNotFoundException();
        }
        return byContent
                .stream()
                .map(newsMapper::mapReadToResponse)
                .toList();
    }

    public List<NewsCardResponse> getNewsByStatus(NewsReadByStatusRequest statusRequest) {
        List<News> byStatus = newsRepository.findByStatus(statusRequest.getStatus());
        if (byStatus.isEmpty()) {
            throw new NewsNotFoundException();
        }
        return byStatus
                .stream()
                .map(newsMapper::mapReadToCardResponse)
                .toList();
    }

    public List<NewsCardResponse> getNewsByCategory(NewsReadByCategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(categoryRequest.getCategoryId()).orElseThrow(CategoryNotFoundException::new);
        Page<News> byStatusAndCategoryStatus = newsRepository.findByStatusAndFkCategoryId(ACTIVE, category.getId(), PageRequest.of(categoryRequest.getPage(), categoryRequest.getSize()));
        if (byStatusAndCategoryStatus.isEmpty()) {
            throw new NewsCategoryNotFoundException();
        }
        return byStatusAndCategoryStatus
                .stream()
                .map(newsMapper::mapReadToCardResponse)
                .toList();
    }

    public List<NewsCardResponse> getNewsBySubCategory(NewsReadBySubCategoryRequest subCategoryRequest) {
        SubCategory subCategory = subCategoryRepository.findById(subCategoryRequest.getSubCategoryId()).orElseThrow(SubCategoryNotFoundException::new);
        Page<News> byStatusAndSubCategoryStatus = newsRepository.findByStatusAndFkSubCategoryId(ACTIVE, subCategory.getId(), PageRequest.of(subCategoryRequest.getPage(), subCategoryRequest.getSize()));
        if (byStatusAndSubCategoryStatus.isEmpty()) {
            throw new NewsSubCategoryNotFoundException();
        }
        return byStatusAndSubCategoryStatus
                .stream()
                .map(newsMapper::mapReadToCardResponse)
                .toList();
    }

    public List<NewsCardResponse> getNewsByCategoryAndSubCategory(NewsReadByCategoryAndSubCategoryRequest categoryAndSubCategoryRequest) {
        Category category = categoryRepository.findById(categoryAndSubCategoryRequest.getCategoryId()).orElseThrow(CategoryNotFoundException::new);
        SubCategory subCategory = subCategoryRepository.findById(categoryAndSubCategoryRequest.getSubCategoryId()).orElseThrow(SubCategoryNotFoundException::new);
        Page<News> byStatusAndCategoryStatusAndSubCategoryStatus = newsRepository.findByStatusAndFkCategoryIdAndFkSubCategoryId(ACTIVE, category.getId(), subCategory.getId(), PageRequest.of(categoryAndSubCategoryRequest.getPage(), categoryAndSubCategoryRequest.getSize()));
        if (byStatusAndCategoryStatusAndSubCategoryStatus.isEmpty()) {
            throw new NewsNotFoundException();
        }
        return byStatusAndCategoryStatusAndSubCategoryStatus
                .stream()
                .map(newsMapper::mapReadToCardResponse)
                .toList();
    }

    @Transactional
    public List<NewsCardResponse> getMostViewed(NewsReadByPage newsReadByPage){
        PageRequest of = PageRequest.of(newsReadByPage.getPage(), newsReadByPage.getSize());
        return newsRepository.findByOrderByViewCountDesc(of)
                .stream()
                .map(newsMapper::mapReadToCardResponse)
                .collect(Collectors.toList());
    }

}
