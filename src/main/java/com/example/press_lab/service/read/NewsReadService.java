package com.example.press_lab.service.read;

import com.example.press_lab.entity.News;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.specification.NewsSpecification;
import com.example.press_lab.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class NewsReadService {


    private final NewsRepository newsRepository;




    public Page<News> findAllNews(List<SearchCriteria> searchCriteriaList, int page, int size) {
        NewsSpecification newsSpecification = new NewsSpecification();
        searchCriteriaList.forEach(newsSpecification::add);
        Pageable pageable = PageRequest.of(page, size);
        return newsRepository.findAll(newsSpecification, pageable);

    }

}
