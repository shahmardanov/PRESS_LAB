package com.example.press_lab.controller;

import com.example.press_lab.entity.News;
import com.example.press_lab.request.SearchRequest;
import com.example.press_lab.response.PageNewsResponse;
import com.example.press_lab.service.read.NewsReadService;
import com.example.press_lab.specification.PageCriteria;
import com.example.press_lab.specification.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("v1/news/")
public class NewsController {

    private final NewsReadService newsReadService;



    @PostMapping("specificSearch")
    public ResponseEntity<PageNewsResponse> findAllNews(@RequestBody List<SearchCriteria> searchCriteriaList,PageCriteria pageCriteria) {
        return ResponseEntity.ok(newsReadService.findAllNews(searchCriteriaList, pageCriteria));
    }

}
