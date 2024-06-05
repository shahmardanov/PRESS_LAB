package com.example.press_lab.service.news;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsReadByPage;
import com.example.press_lab.response.news.NewsCardResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NewsRecentService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class NewsRecentServiceTest {
    @MockBean
    private NewsMapper newsMapper;

    @Autowired
    private NewsRecentService newsRecentService;

    @MockBean
    private NewsRepository newsRepository;

    /**
     * Method under test: {@link NewsRecentService#getRecentNews(NewsReadByPage)}
     */
    @Test
    void testGetRecentNews() {
        when(newsRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        List<NewsCardResponse> actualRecentNews = newsRecentService.getRecentNews(new NewsReadByPage(1, 3));

        verify(newsRepository).findAll(isA(Pageable.class));
        assertTrue(actualRecentNews.isEmpty());
    }

    /**
     * Method under test:
     * {@link NewsRecentService#getRecentNewsLast24Hours(NewsReadByPage)}
     */
    @Test
    void testGetRecentNewsLast24Hours() {
        when(newsRepository.findByCreatedAtAfter(Mockito.<LocalDateTime>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        List<NewsCardResponse> actualRecentNewsLast24Hours = newsRecentService
                .getRecentNewsLast24Hours(new NewsReadByPage(1, 3));

        verify(newsRepository).findByCreatedAtAfter(isA(LocalDateTime.class), isA(Pageable.class));
        assertTrue(actualRecentNewsLast24Hours.isEmpty());
    }
}
