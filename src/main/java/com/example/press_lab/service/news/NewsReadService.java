package com.example.press_lab.service.news;

import com.example.press_lab.entity.News;
import com.example.press_lab.exception.news.NewsNotFoundException;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.response.news.NewsReadResponse;
import com.example.press_lab.util.LocaleResolverUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.example.press_lab.enums.NewsStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class NewsReadService {

    private final NewsRepository newsRepository;

    private final LocaleResolverUtil localeResolverUtil;

    public NewsReadResponse getNewsById(Long newsId, Locale locale) {
        News news = newsRepository.findByIdAndStatus(newsId,ACTIVE).orElseThrow(NewsNotFoundException::new);
        return localeResolverUtil.setForLocalId(news, locale);
    }

}
