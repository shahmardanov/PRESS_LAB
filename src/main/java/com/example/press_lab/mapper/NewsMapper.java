package com.example.press_lab.mapper;

import com.example.press_lab.entity.News;
import com.example.press_lab.request.NewsCreateRequest;
import com.example.press_lab.response.NewsCardResponse;
import com.example.press_lab.response.NewsReadResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsReadResponse toReadResponse(News news);
    NewsCardResponse toCardResponse(News news);
    News toCreateEntity(NewsCreateRequest newsCreateRequest);

}
