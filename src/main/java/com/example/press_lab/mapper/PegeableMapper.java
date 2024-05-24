package com.example.press_lab.mapper;

import com.example.press_lab.response.NewsResponse;
import com.example.press_lab.response.PageNewsResponse;
import org.springframework.data.domain.Page;

public class PegeableMapper {

    public static PageNewsResponse mapNewsResponsePageToCustomPageResponse(Page<NewsResponse> page) {
        return PageNewsResponse.builder()
                .newsResponseList(page.getContent())
                .pageSize(page.getSize())
                .pageNumber(page.getNumber())
                .hasNextPage(page.hasNext())
                .lastPageNumber(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }

}
