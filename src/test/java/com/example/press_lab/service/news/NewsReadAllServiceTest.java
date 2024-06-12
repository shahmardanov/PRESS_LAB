package com.example.press_lab.service.news;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.Category;
import com.example.press_lab.entity.News;
import com.example.press_lab.entity.SubCategory;
import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.exception.news.NewsCategoryNotFoundException;
import com.example.press_lab.exception.news.NewsContentNotFoundException;
import com.example.press_lab.exception.news.NewsNotFoundException;
import com.example.press_lab.exception.news.NewsSubCategoryNotFoundException;
import com.example.press_lab.mappers.NewsMapper;
import com.example.press_lab.repository.CategoryRepository;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.repository.SubCategoryRepository;
import com.example.press_lab.request.news.NewsReadByCategoryAndSubCategoryRequest;
import com.example.press_lab.request.news.NewsReadByCategoryRequest;
import com.example.press_lab.request.news.NewsReadByStatusRequest;
import com.example.press_lab.request.news.NewsReadBySubCategoryRequest;
import com.example.press_lab.request.news.NewsReadRequest;
import com.example.press_lab.response.news.NewsCardResponse;
import com.example.press_lab.response.news.NewsReadResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.press_lab.service.util.NewsUtil;
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

@ContextConfiguration(classes = {NewsReadAllService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class NewsReadAllServiceTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private NewsMapper newsMapper;

    @Autowired
    private NewsReadAllService newsReadAllService;

    @MockBean
    private NewsRepository newsRepository;

    @MockBean
    private SubCategoryRepository subCategoryRepository;

    /**
     * Method under test: {@link NewsReadAllService#getAllNews()}
     */
    @Test
    void testGetAllNews() {
        when(newsRepository.findAll()).thenReturn(new ArrayList<>());

        List<NewsReadResponse> actualAllNews = newsReadAllService.getAllNews();

        verify(newsRepository).findAll();
        assertTrue(actualAllNews.isEmpty());
    }

    /**
     * Method under test:
     * {@link NewsReadAllService#getNewsByContent(NewsReadRequest)}
     */
    @Test
    void testGetNewsByContent() {
        News news = NewsUtil.news();
        Optional<News> ofResult = Optional.of(news);
        when(newsRepository.findByContent(Mockito.<String>any())).thenReturn(ofResult);
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

        List<NewsReadResponse> actualNewsByContent = newsReadAllService.getNewsByContent(new NewsReadRequest());

        verify(newsMapper).mapReadToResponse(isA(News.class));
        verify(newsRepository).findByContent(isNull());
        assertEquals(1, actualNewsByContent.size());
    }

    /**
     * Method under test:
     * {@link NewsReadAllService#getNewsByContent(NewsReadRequest)}
     */
    @Test
    void testGetNewsByContent2() {
        Optional<News> emptyResult = Optional.empty();
        when(newsRepository.findByContent(Mockito.<String>any())).thenReturn(emptyResult);

        assertThrows(NewsContentNotFoundException.class, () -> newsReadAllService.getNewsByContent(new NewsReadRequest()));
        verify(newsRepository).findByContent(isNull());
    }

    /**
     * Method under test:
     * {@link NewsReadAllService#getNewsByStatus(NewsReadByStatusRequest)}
     */
    @Test
    void testGetNewsByStatus() {
        when(newsRepository.findByStatus(Mockito.<NewsStatus>any())).thenReturn(new ArrayList<>());

        assertThrows(NewsNotFoundException.class,
                () -> newsReadAllService.getNewsByStatus(new NewsReadByStatusRequest(NewsStatus.ACTIVE)));
        verify(newsRepository).findByStatus(eq(NewsStatus.ACTIVE));
    }

    /**
     * Method under test:
     * {@link NewsReadAllService#getNewsByStatus(NewsReadByStatusRequest)}
     */
    @Test
    void testGetNewsByStatus2() {
        News news = NewsUtil.news();

        ArrayList<News> newsList = new ArrayList<>();
        newsList.add(news);
        when(newsRepository.findByStatus(Mockito.<NewsStatus>any())).thenReturn(newsList);
        NewsCardResponse.NewsCardResponseBuilder builderResult = NewsCardResponse.builder();
        NewsCardResponse.NewsCardResponseBuilder titleResult = builderResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .id(1L)
                .image("Image")
                .title("Dr");
        NewsCardResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay()).viewCount(3L).build();
        when(newsMapper.mapReadToCardResponse(Mockito.<News>any())).thenReturn(buildResult);

        List<NewsCardResponse> actualNewsByStatus = newsReadAllService
                .getNewsByStatus(new NewsReadByStatusRequest(NewsStatus.ACTIVE));

        verify(newsMapper).mapReadToCardResponse(isA(News.class));
        verify(newsRepository).findByStatus(eq(NewsStatus.ACTIVE));
        assertEquals(1, actualNewsByStatus.size());
    }

    /**
     * Method under test:
     * {@link NewsReadAllService#getNewsByCategory(NewsReadByCategoryRequest)}
     */
    @Test
    void testGetNewsByCategory() {
        when(newsRepository.findByStatusAndFkCategoryId(Mockito.<NewsStatus>any(), Mockito.<Long>any(),
                Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        NewsReadByCategoryRequest categoryRequest = NewsReadByCategoryRequest.builder()
                .categoryId(1L)
                .page(1)
                .size(3)
                .build();

        assertThrows(NewsCategoryNotFoundException.class, () -> newsReadAllService.getNewsByCategory(categoryRequest));
        verify(newsRepository).findByStatusAndFkCategoryId(eq(NewsStatus.ACTIVE), eq(1L), isA(Pageable.class));
        verify(categoryRepository).findById(eq(1L));
    }

    /**
     * Method under test:
     * {@link NewsReadAllService#getNewsByCategory(NewsReadByCategoryRequest)}
     */
    @Test
    void testGetNewsByCategory2() {
        News news = NewsUtil.news();

        ArrayList<News> content = new ArrayList<>();
        content.add(news);
        PageImpl<News> pageImpl = new PageImpl<>(content);
        when(newsRepository.findByStatusAndFkCategoryId(Mockito.<NewsStatus>any(), Mockito.<Long>any(),
                Mockito.<Pageable>any())).thenReturn(pageImpl);

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        NewsCardResponse.NewsCardResponseBuilder builderResult = NewsCardResponse.builder();
        NewsCardResponse.NewsCardResponseBuilder titleResult = builderResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .id(1L)
                .image("Image")
                .title("Dr");
        NewsCardResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay()).viewCount(3L).build();
        when(newsMapper.mapReadToCardResponse(Mockito.<News>any())).thenReturn(buildResult);
        NewsReadByCategoryRequest categoryRequest = NewsReadByCategoryRequest.builder()
                .categoryId(1L)
                .page(1)
                .size(3)
                .build();

        List<NewsCardResponse> actualNewsByCategory = newsReadAllService.getNewsByCategory(categoryRequest);

        verify(newsMapper).mapReadToCardResponse(isA(News.class));
        verify(newsRepository).findByStatusAndFkCategoryId(eq(NewsStatus.ACTIVE), eq(1L), isA(Pageable.class));
        verify(categoryRepository).findById(eq(1L));
        assertEquals(1, actualNewsByCategory.size());
    }

    /**
     * Method under test:
     * {@link NewsReadAllService#getNewsBySubCategory(NewsReadBySubCategoryRequest)}
     */
    @Test
    void testGetNewsBySubCategory() {
        when(newsRepository.findByStatusAndFkSubCategoryId(Mockito.<NewsStatus>any(), Mockito.<Long>any(),
                Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        SubCategory subCategory = new SubCategory();
        subCategory.setFkCategoryId(1L);
        subCategory.setId(1L);
        subCategory.setName("Name");
        Optional<SubCategory> ofResult = Optional.of(subCategory);
        when(subCategoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        NewsReadBySubCategoryRequest subCategoryRequest = NewsReadBySubCategoryRequest.builder()
                .page(1)
                .size(3)
                .subCategoryId(1L)
                .build();

        assertThrows(NewsSubCategoryNotFoundException.class,
                () -> newsReadAllService.getNewsBySubCategory(subCategoryRequest));
        verify(newsRepository).findByStatusAndFkSubCategoryId(eq(NewsStatus.ACTIVE), eq(1L), isA(Pageable.class));
        verify(subCategoryRepository).findById(eq(1L));
    }

    /**
     * Method under test:
     * {@link NewsReadAllService#getNewsBySubCategory(NewsReadBySubCategoryRequest)}
     */
    @Test
    void testGetNewsBySubCategory2() {
        News news = NewsUtil.news();

        ArrayList<News> content = new ArrayList<>();
        content.add(news);
        PageImpl<News> pageImpl = new PageImpl<>(content);
        when(newsRepository.findByStatusAndFkSubCategoryId(Mockito.<NewsStatus>any(), Mockito.<Long>any(),
                Mockito.<Pageable>any())).thenReturn(pageImpl);

        SubCategory subCategory = new SubCategory();
        subCategory.setFkCategoryId(1L);
        subCategory.setId(1L);
        subCategory.setName("Name");
        Optional<SubCategory> ofResult = Optional.of(subCategory);
        when(subCategoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        NewsCardResponse.NewsCardResponseBuilder builderResult = NewsCardResponse.builder();
        NewsCardResponse.NewsCardResponseBuilder titleResult = builderResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .id(1L)
                .image("Image")
                .title("Dr");
        NewsCardResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay()).viewCount(3L).build();
        when(newsMapper.mapReadToCardResponse(Mockito.<News>any())).thenReturn(buildResult);
        NewsReadBySubCategoryRequest subCategoryRequest = NewsReadBySubCategoryRequest.builder()
                .page(1)
                .size(3)
                .subCategoryId(1L)
                .build();

        List<NewsCardResponse> actualNewsBySubCategory = newsReadAllService.getNewsBySubCategory(subCategoryRequest);

        verify(newsMapper).mapReadToCardResponse(isA(News.class));
        verify(newsRepository).findByStatusAndFkSubCategoryId(eq(NewsStatus.ACTIVE), eq(1L), isA(Pageable.class));
        verify(subCategoryRepository).findById(eq(1L));
        assertEquals(1, actualNewsBySubCategory.size());
    }

    /**
     * Method under test:
     * {@link NewsReadAllService#getNewsByCategoryAndSubCategory(NewsReadByCategoryAndSubCategoryRequest)}
     */
    @Test
    void testGetNewsByCategoryAndSubCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(subCategoryRepository.findById(Mockito.<Long>any())).thenThrow(new NewsContentNotFoundException());

        assertThrows(NewsContentNotFoundException.class,
                () -> newsReadAllService.getNewsByCategoryAndSubCategory(new NewsReadByCategoryAndSubCategoryRequest()));
        verify(categoryRepository).findById(isNull());
        verify(subCategoryRepository).findById(isNull());
    }

    /**
     * Method under test:
     * {@link NewsReadAllService#getNewsByCategoryAndSubCategory(NewsReadByCategoryAndSubCategoryRequest)}
     */
    @Test
    void testGetNewsByCategoryAndSubCategory2() {
        when(newsRepository.findByStatusAndFkCategoryIdAndFkSubCategoryId(Mockito.<NewsStatus>any(), Mockito.<Long>any(),
                Mockito.<Long>any(), Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        SubCategory subCategory = new SubCategory();
        subCategory.setFkCategoryId(1L);
        subCategory.setId(1L);
        subCategory.setName("Name");
        Optional<SubCategory> ofResult2 = Optional.of(subCategory);
        when(subCategoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        NewsReadByCategoryAndSubCategoryRequest categoryAndSubCategoryRequest = NewsReadByCategoryAndSubCategoryRequest
                .builder()
                .categoryId(1L)
                .page(1)
                .size(3)
                .subCategoryId(1L)
                .build();

        assertThrows(NewsNotFoundException.class,
                () -> newsReadAllService.getNewsByCategoryAndSubCategory(categoryAndSubCategoryRequest));
        verify(newsRepository).findByStatusAndFkCategoryIdAndFkSubCategoryId(eq(NewsStatus.ACTIVE), eq(1L), eq(1L),
                isA(Pageable.class));
        verify(categoryRepository).findById(eq(1L));
        verify(subCategoryRepository).findById(eq(1L));
    }

    /**
     * Method under test:
     * {@link NewsReadAllService#getNewsByCategoryAndSubCategory(NewsReadByCategoryAndSubCategoryRequest)}
     */
    @Test
    void testGetNewsByCategoryAndSubCategory3() {
        News news = NewsUtil.news();

        ArrayList<News> content = new ArrayList<>();
        content.add(news);
        PageImpl<News> pageImpl = new PageImpl<>(content);
        when(newsRepository.findByStatusAndFkCategoryIdAndFkSubCategoryId(Mockito.<NewsStatus>any(), Mockito.<Long>any(),
                Mockito.<Long>any(), Mockito.<Pageable>any())).thenReturn(pageImpl);

        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        SubCategory subCategory = new SubCategory();
        subCategory.setFkCategoryId(1L);
        subCategory.setId(1L);
        subCategory.setName("Name");
        Optional<SubCategory> ofResult2 = Optional.of(subCategory);
        when(subCategoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        NewsCardResponse.NewsCardResponseBuilder builderResult = NewsCardResponse.builder();
        NewsCardResponse.NewsCardResponseBuilder titleResult = builderResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .id(1L)
                .image("Image")
                .title("Dr");
        NewsCardResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay()).viewCount(3L).build();
        when(newsMapper.mapReadToCardResponse(Mockito.<News>any())).thenReturn(buildResult);
        NewsReadByCategoryAndSubCategoryRequest categoryAndSubCategoryRequest = NewsReadByCategoryAndSubCategoryRequest
                .builder()
                .categoryId(1L)
                .page(1)
                .size(3)
                .subCategoryId(1L)
                .build();

        List<NewsCardResponse> actualNewsByCategoryAndSubCategory = newsReadAllService
                .getNewsByCategoryAndSubCategory(categoryAndSubCategoryRequest);

        verify(newsMapper).mapReadToCardResponse(isA(News.class));
        verify(newsRepository).findByStatusAndFkCategoryIdAndFkSubCategoryId(eq(NewsStatus.ACTIVE), eq(1L), eq(1L),
                isA(Pageable.class));
        verify(categoryRepository).findById(eq(1L));
        verify(subCategoryRepository).findById(eq(1L));
        assertEquals(1, actualNewsByCategoryAndSubCategory.size());
    }
}
