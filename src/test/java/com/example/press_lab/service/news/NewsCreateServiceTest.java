package com.example.press_lab.service.news;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.News;
import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.exception.news.NewsConflictException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.request.news.NewsCreateRequest;
import com.example.press_lab.service.subscriptionService.NotifySubscription;

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

@ContextConfiguration(classes = {NewsCreateService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class NewsCreateServiceTest {
    @Autowired
    private NewsCreateService newsCreateService;

    @MockBean
    private NewsMapper newsMapper;

    @MockBean
    private NewsRepository newsRepository;

    @MockBean
    private NotifySubscription notifySubscription;

    /**
     * Method under test: {@link NewsCreateService#create(NewsCreateRequest)}
     */
    @Test
    void testCreate() {
        News news = NewsUtil.news();
        Optional<News> ofResult = Optional.of(news);
        when(newsRepository.findByContent(Mockito.<String>any())).thenReturn(ofResult);

        assertThrows(NewsConflictException.class, () -> newsCreateService.create(new NewsCreateRequest()));
        verify(newsRepository).findByContent(isNull());
    }
}
