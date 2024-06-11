package com.example.press_lab.service.news;

import com.example.press_lab.entity.News;
import com.example.press_lab.exception.error.ErrorMessages;
import com.example.press_lab.exception.news.NewsNotFoundException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.response.news.NewsReadResponse;
import com.example.press_lab.util.LocaleResolverUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class NewsReadService {

    private final NewsRepository newsRepository;

    private final LocaleResolverUtil localeResolverUtil;
    private final ErrorMessages errorMessages;

    public NewsReadResponse getNewsById(Long newsId, Locale locale) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new NewsNotFoundException(locale, errorMessages));
        return localeResolverUtil.setForLocalId(news, locale);
    }

}

