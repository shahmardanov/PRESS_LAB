package com.example.press_lab.service.news;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.News;
import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.exception.news.NewsNotFoundException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsUpdateRequest;
import com.example.press_lab.response.news.NewsUpdateResponse;

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

@ContextConfiguration(classes = {NewsUpdateService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class NewsUpdateServiceTest {
    @MockBean
    private NewsMapper newsMapper;

    @MockBean
    private NewsRepository newsRepository;

    @Autowired
    private NewsUpdateService newsUpdateService;


    @Test
    void testUpdate() {
        News news = NewsUtil.news();
        Optional<News> ofResult = Optional.of(news);

        News news2 = NewsUtil.news();
        when(newsRepository.save(Mockito.<News>any())).thenReturn(news2);
        when(newsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        News news3 = NewsUtil.news();
        when(newsMapper.updateNewsToNewsUpdateResponse(Mockito.<NewsUpdateRequest>any(), Mockito.<News>any()))
                .thenReturn(news3);
        NewsUpdateResponse.NewsUpdateResponseBuilder contentResult = NewsUpdateResponse.builder()
                .content("Not all who wander are lost");
        NewsUpdateResponse.NewsUpdateResponseBuilder titleResult = contentResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .description("The characteristics of someone or something")
                .fkCategoryId(1L)
                .fkSubCategoryId(1L)
                .id(1L)
                .imageUrl("https://example.org/example")
                .status(NewsStatus.ACTIVE)
                .title("Dr");
        NewsUpdateResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay()).build();
        when(newsMapper.mapUpdateToResponse(Mockito.<News>any())).thenReturn(buildResult);

        NewsUpdateResponse actualUpdateResult = newsUpdateService.update(new NewsUpdateRequest());

        verify(newsMapper).mapUpdateToResponse(isA(News.class));
        verify(newsMapper).updateNewsToNewsUpdateResponse(isA(NewsUpdateRequest.class), isA(News.class));
        verify(newsRepository).findById(isNull());
        verify(newsRepository).save(isA(News.class));
        LocalTime expectedToLocalTimeResult = actualUpdateResult.getUpdatedAt().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualUpdateResult.getCreatedAt().toLocalTime());
    }

    /**
     * Method under test: {@link NewsUpdateService#update(NewsUpdateRequest)}
     */
    @Test
    void testUpdate2() {
        News news = NewsUtil.news();
        Optional<News> ofResult = Optional.of(news);
        when(newsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(newsMapper.updateNewsToNewsUpdateResponse(Mockito.<NewsUpdateRequest>any(), Mockito.<News>any()))
                .thenThrow(new NewsNotFoundException());

        assertThrows(NewsNotFoundException.class, () -> newsUpdateService.update(new NewsUpdateRequest()));
        verify(newsMapper).updateNewsToNewsUpdateResponse(isA(NewsUpdateRequest.class), isA(News.class));
        verify(newsRepository).findById(isNull());
    }

    /**
     * Method under test: {@link NewsUpdateService#update(NewsUpdateRequest)}
     */
    @Test
    void testUpdate3() {
        News news = NewsUtil.news();
        Optional<News> ofResult = Optional.of(news);
        when(newsRepository.save(Mockito.<News>any())).thenThrow(new NewsNotFoundException());
        when(newsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        News news2 = NewsUtil.news();
        when(newsMapper.updateNewsToNewsUpdateResponse(Mockito.<NewsUpdateRequest>any(), Mockito.<News>any()))
                .thenReturn(news2);
        NewsUpdateRequest updateRequest = NewsUpdateRequest.builder()
                .content("Not all who wander are lost")
                .description("The characteristics of someone or something")
                .fkCategoryId(1L)
                .fkSubCategoryId(1L)
                .id(1L)
                .imageUrl("https://example.org/example")
                .status(NewsStatus.ACTIVE)
                .title("Dr")
                .build();

        assertThrows(NewsNotFoundException.class, () -> newsUpdateService.update(updateRequest));
        verify(newsMapper).updateNewsToNewsUpdateResponse(isA(NewsUpdateRequest.class), isA(News.class));
        verify(newsRepository).findById(eq(1L));
        verify(newsRepository).save(isA(News.class));
    }
}
