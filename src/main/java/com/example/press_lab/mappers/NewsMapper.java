package com.example.press_lab.mappers;

import com.example.press_lab.entity.News;
import com.example.press_lab.request.news.NewsCreateRequest;
import com.example.press_lab.request.news.NewsUpdateRequest;
import com.example.press_lab.response.news.NewsCardResponse;
import com.example.press_lab.response.news.NewsCreateResponse;
import com.example.press_lab.response.news.NewsReadResponse;
import com.example.press_lab.response.news.NewsUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface NewsMapper {

    News mapRequestToEntity(NewsCreateRequest newsCreateRequest);

    NewsCreateResponse mapCreateToResponse(News news);

    NewsReadResponse mapReadToResponse(News news);

    NewsCardResponse mapReadToCardResponse(News news);

    NewsUpdateResponse mapUpdateToResponse(News news);

    News updateNewsToNewsUpdateResponse(NewsUpdateRequest updateRequest, @MappingTarget News news);

}
