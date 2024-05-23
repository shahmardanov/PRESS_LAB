package com.example.press_lab.controller;

import com.example.press_lab.entity.News;
import com.example.press_lab.service.read.NewsReadService;
import com.example.press_lab.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("v1/news/")
public class NewsController {

    private final NewsReadService newsReadService;



    @PostMapping("specificSearch")
    public ResponseEntity<Page<News>> findAllNews(@RequestBody List<SearchCriteria> searchCriteriaList, int page, int size) {
        return ResponseEntity.ok(newsReadService.findAllNews(searchCriteriaList,page, size));
    }

}
