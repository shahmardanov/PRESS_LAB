package com.example.press_lab.service.news;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.example.press_lab.repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NewsViewCountIncrementService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class NewsViewCountIncrementServiceTest {
    @MockBean
    private NewsRepository newsRepository;

    @Autowired
    private NewsViewCountIncrementService newsViewCountIncrementService;

    /**
     * Method under test:
     * {@link NewsViewCountIncrementService#incrementNewsViewCount(Long)}
     */
    @Test
    void testIncrementNewsViewCount() {
        doNothing().when(newsRepository).incrementViewCount(Mockito.<Long>any());

        newsViewCountIncrementService.incrementNewsViewCount(1L);

        verify(newsRepository).incrementViewCount(eq(1L));
    }
}
