package com.example.press_lab.mapper;

import com.example.press_lab.entity.News;
import com.example.press_lab.response.NewsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {




        NewsResponse entityToResponse(News news);


    }



