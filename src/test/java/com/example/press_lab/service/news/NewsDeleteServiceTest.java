package com.example.press_lab.service.news;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.News;
import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.exception.news.NewsNotFoundException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsDeleteRequest;

import java.time.LocalDate;
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

@DisabledInAotMode
@ContextConfiguration(classes = {NewsDeleteService.class})
@ExtendWith(SpringExtension.class)
class NewsDeleteServiceTest {
    @MockBean
    private NewsMapper newsMapper;

    @MockBean
    private NewsRepository newsRepository;

    @Autowired
    private NewsDeleteService newsDeleteService;

    /**
     * Method under test: {@link NewsDeleteService#deleteAll()}
     */
    @Test
    void testDeleteAll() {
        // Arrange
        doNothing().when(newsRepository).deleteAll();

        newsDeleteService.deleteAll();

        verify(newsRepository).deleteAll();
    }

    @Test
    void testDeleteById() {
        News news = NewsUtil.news();
        Optional<News> ofResult = Optional.of(news);

        News news2 = NewsUtil.news();
        when(newsRepository.save(Mockito.<News>any())).thenReturn(news2);
        when(newsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        newsDeleteService.deleteById(new NewsDeleteRequest());

        verify(newsRepository).findById(isNull());
        verify(newsRepository).save(isA(News.class));
    }

    /**
     * Method under test: {@link NewsDeleteService#deleteById(NewsDeleteRequest)}
     */
    @Test
    void testDeleteById2() {
        News news = NewsUtil.news();
        Optional<News> ofResult = Optional.of(news);

        when(newsRepository.save(Mockito.<News>any())).thenThrow(new NewsNotFoundException());
        when(newsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        assertThrows(NewsNotFoundException.class, () -> newsDeleteService.deleteById(new NewsDeleteRequest()));
        verify(newsRepository).findById(isNull());
        verify(newsRepository).save(isA(News.class));
    }

}
