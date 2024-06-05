package com.example.press_lab.service.news;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.News;
import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.exception.news.NewsNotFoundException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.response.news.NewsReadResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import com.example.press_lab.service.util.NewsUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NewsReadService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class NewsReadServiceTest {
    @MockBean
    private NewsMapper newsMapper;

    @Autowired
    private NewsReadService newsReadService;

    @MockBean
    private NewsRepository newsRepository;

    /**
     * Method under test: {@link NewsReadService#getNewsById(Long)}
     */
    @Test
    void testGetNewsById() {
        News news = NewsUtil.news();
        Optional<News> ofResult = Optional.of(news);
        when(newsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        NewsReadResponse.NewsReadResponseBuilder contentResult = NewsReadResponse.builder()
                .content("Not all who wander are lost");
        NewsReadResponse.NewsReadResponseBuilder titleResult = contentResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .description("The characteristics of someone or something")
                .fkCategoryId(1L)
                .fkSubCategoryId(1L)
                .id(1L)
                .imageUrl("https://example.org/example")
                .status(NewsStatus.ACTIVE)
                .title("Dr");
        NewsReadResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay()).viewCount(3L).build();
        when(newsMapper.mapReadToResponse(Mockito.<News>any())).thenReturn(buildResult);

        NewsReadResponse actualNewsById = newsReadService.getNewsById(1L);

        verify(newsMapper).mapReadToResponse(isA(News.class));
        verify(newsRepository).findById(eq(1L));
        LocalTime expectedToLocalTimeResult = actualNewsById.getUpdatedAt().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualNewsById.getCreatedAt().toLocalTime());
    }

    /**
     * Method under test: {@link NewsReadService#getNewsById(Long)}
     */
    @Test
    void testGetNewsById2() {
        News news = NewsUtil.news();
        Optional<News> ofResult = Optional.of(news);
        when(newsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(newsMapper.mapReadToResponse(Mockito.<News>any())).thenThrow(new NewsNotFoundException());

        assertThrows(NewsNotFoundException.class, () -> newsReadService.getNewsById(1L));
        verify(newsMapper).mapReadToResponse(isA(News.class));
        verify(newsRepository).findById(eq(1L));
    }
}
