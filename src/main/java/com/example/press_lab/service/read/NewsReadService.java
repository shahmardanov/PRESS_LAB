package com.example.press_lab.service.read;

import com.example.press_lab.entity.News;
import com.example.press_lab.mapper.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.response.NewsResponse;
import com.example.press_lab.response.PageNewsResponse;
import com.example.press_lab.specification.NewsSpecification;
import com.example.press_lab.specification.PageCriteria;
import com.example.press_lab.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.press_lab.contraint.NewsMessages.NEWS_NOT_FOUND;
import static com.example.press_lab.mapper.PegeableMapper.mapNewsResponsePageToCustomPageResponse;
import static com.example.press_lab.util.NewsUtil.getDefaultPageable;


@Service
@RequiredArgsConstructor
public class NewsReadService {


    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;




//    public Page<News> findAllNews(List<SearchCriteria> searchCriteriaList, int page, int size) {
//        NewsSpecification newsSpecification = new NewsSpecification();
//        searchCriteriaList.forEach(newsSpecification::add);
//        Pageable pageable = PageRequest.of(page, size);
//        return newsRepository.findAll(newsSpecification, pageable);
//
//    }

    public PageNewsResponse findAllNews(List<SearchCriteria> searchCriteria, PageCriteria pageCriteria) {
        Pageable pageable = getDefaultPageable(pageCriteria);
        Page<News> announcementPage = newsRepository.findAll(new NewsSpecification(searchCriteria), pageable);

        if (announcementPage.getContent().isEmpty())
            throw new RuntimeException(NEWS_NOT_FOUND);

        List<NewsResponse> announcementResponseList = announcementPage.getContent().stream().map(newsMapper::entityToResponse).toList();
        Page<NewsResponse> announcementResponsePage = new PageImpl<>(announcementResponseList);
        return mapNewsResponsePageToCustomPageResponse(announcementResponsePage);
    }


}
