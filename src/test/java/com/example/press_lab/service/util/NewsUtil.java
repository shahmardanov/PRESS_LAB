package com.example.press_lab.service.util;

import com.example.press_lab.entity.News;
import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.request.news.NewsCreateRequest;
import com.example.press_lab.request.news.NewsDeleteRequest;

import java.time.LocalDate;

public class NewsUtil {

    private NewsUtil(){

    }

    public static News news(){
        News news = new News();
        news.setContent("Not all who wander are lost");
        news.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        news.setDescription("The characteristics of someone or something");
        news.setFkCategoryId(1L);
        news.setFkSubCategoryId(1L);
        news.setId(1L);
        news.setImageUrl("https://example.org/example");
        news.setStatus(NewsStatus.ACTIVE);
        news.setTitle("Dr");
        news.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        news.setViewCount(3L);

        return news;
    }

}
