package com.example.press_lab.service.news;


import com.example.press_lab.entity.News;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsReadByPage;
import com.example.press_lab.response.news.NewsCardResponse;
import com.example.press_lab.util.LocaleResolverUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class NewsRecentService {
    private final NewsRepository newsRepository;

    private final LocaleResolverUtil localeResolverUtil;

    @Transactional
    public List<NewsCardResponse> getRecentNews(NewsReadByPage newsReadByPage, Locale locale) {
        Pageable pageable = PageRequest.of(newsReadByPage.getPage(), newsReadByPage.getSize(), Sort.by("createdAt").descending());
        Page<News> newsPage = newsRepository.findAll(pageable);
        return newsPage.stream()
                .map(news -> localeResolverUtil.setForLocal(news, locale))
                .toList();
    }

    @Transactional
    public List<NewsCardResponse> getRecentNewsLast24Hours(NewsReadByPage newsReadByPage, Locale locale) {
        Pageable pageable = PageRequest.of(newsReadByPage.getPage(), newsReadByPage.getSize(), Sort.by("createdAt").descending());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime last24Hours = now.minusHours(24);
        Page<News> recentNews = newsRepository.findByCreatedAtAfter(last24Hours, pageable);
        return recentNews.stream()
                .map(news -> localeResolverUtil.setForLocal(news, locale))
                .toList();
    }

}
