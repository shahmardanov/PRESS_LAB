package com.example.press_lab.service.news;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.repository.NewsRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NewsViewCountService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class NewsViewCountServiceTest {
    @MockBean
    private NewsRepository newsRepository;

    @Autowired
    private NewsViewCountService newsViewCountService;

    /**
     * Method under test: {@link NewsViewCountService#getMostViewedCategoryStatus()}
     */
    @Test
    void testGetMostViewedCategoryStatus() {
        ArrayList<Long> resultLongList = new ArrayList<>();
        when(newsRepository.findMostViewedCategoryStatus(Mockito.<Pageable>any())).thenReturn(resultLongList);

        List<Long> actualMostViewedCategoryStatus = newsViewCountService.getMostViewedCategoryStatus();

        verify(newsRepository).findMostViewedCategoryStatus(isA(Pageable.class));
        assertTrue(actualMostViewedCategoryStatus.isEmpty());
        assertSame(resultLongList, actualMostViewedCategoryStatus);
    }

    /**
     * Method under test:
     * {@link NewsViewCountService#getMostViewedSubCategoryStatus()}
     */
    @Test
    void testGetMostViewedSubCategoryStatus() {
        ArrayList<Object[]> objectArrayList = new ArrayList<>();
        when(newsRepository.findMostViewedSubCategoryStatus()).thenReturn(objectArrayList);

        List<Object[]> actualMostViewedSubCategoryStatus = newsViewCountService.getMostViewedSubCategoryStatus();

        verify(newsRepository).findMostViewedSubCategoryStatus();
        assertTrue(actualMostViewedSubCategoryStatus.isEmpty());
        assertSame(objectArrayList, actualMostViewedSubCategoryStatus);
    }

    /**
     * Method under test:
     * {@link NewsViewCountService#getMostViewedSubCategoryStatusFromCategory(Long)}
     */
    @Test
    void testGetMostViewedSubCategoryStatusFromCategory() {
        ArrayList<Object[]> objectArrayList = new ArrayList<>();
        when(newsRepository.findMostViewedSubCategoryStatusFromCategory(Mockito.<Long>any())).thenReturn(objectArrayList);

        List<Object[]> actualMostViewedSubCategoryStatusFromCategory = newsViewCountService
                .getMostViewedSubCategoryStatusFromCategory(1L);

        verify(newsRepository).findMostViewedSubCategoryStatusFromCategory(eq(1L));
        assertTrue(actualMostViewedSubCategoryStatusFromCategory.isEmpty());
        assertSame(objectArrayList, actualMostViewedSubCategoryStatusFromCategory);
    }

    /**
     * Method under test:
     * {@link NewsViewCountService#getMost5ViewedCategoryStatus()}
     */
    @Test
    void testGetMost5ViewedCategoryStatus() {
        ArrayList<Object[]> objectArrayList = new ArrayList<>();
        when(newsRepository.findMost5ViewedCategoryStatus(Mockito.<Pageable>any())).thenReturn(objectArrayList);

        List<Object[]> actualMost5ViewedCategoryStatus = newsViewCountService.getMost5ViewedCategoryStatus();

        verify(newsRepository).findMost5ViewedCategoryStatus(isA(Pageable.class));
        assertTrue(actualMost5ViewedCategoryStatus.isEmpty());
        assertSame(objectArrayList, actualMost5ViewedCategoryStatus);
    }

    /**
     * Method under test:
     * {@link NewsViewCountService#getMost5ViewedSubCategoryStatus()}
     */
    @Test
    void testGetMost5ViewedSubCategoryStatus() {
        ArrayList<Object[]> objectArrayList = new ArrayList<>();
        when(newsRepository.findMost5ViewedSubCategoryStatus(Mockito.<Pageable>any())).thenReturn(objectArrayList);

        List<Object[]> actualMost5ViewedSubCategoryStatus = newsViewCountService.getMost5ViewedSubCategoryStatus();

        verify(newsRepository).findMost5ViewedSubCategoryStatus(isA(Pageable.class));
        assertTrue(actualMost5ViewedSubCategoryStatus.isEmpty());
        assertSame(objectArrayList, actualMost5ViewedSubCategoryStatus);
    }
}
