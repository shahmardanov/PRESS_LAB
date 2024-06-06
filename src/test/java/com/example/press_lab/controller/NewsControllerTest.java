package com.example.press_lab.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.News;
import com.example.press_lab.enums.NewsStatus;
import com.example.press_lab.mappers.NewsMapperImpl;
import com.example.press_lab.repository.CategoryRepository;
import com.example.press_lab.repository.NewsRepository;
import com.example.press_lab.repository.SubCategoryRepository;
import com.example.press_lab.repository.SubscriptionRepository;
import com.example.press_lab.request.news.NewsCreateRequest;
import com.example.press_lab.request.news.NewsDeleteRequest;
import com.example.press_lab.request.news.NewsReadByCategoryAndSubCategoryRequest;
import com.example.press_lab.request.news.NewsReadByCategoryRequest;
import com.example.press_lab.request.news.NewsReadByPage;
import com.example.press_lab.request.news.NewsReadByStatusRequest;
import com.example.press_lab.request.news.NewsReadBySubCategoryRequest;
import com.example.press_lab.request.news.NewsReadRequest;
import com.example.press_lab.request.news.NewsUpdateRequest;
import com.example.press_lab.response.news.NewsCreateResponse;
import com.example.press_lab.response.news.NewsReadResponse;
import com.example.press_lab.response.news.NewsUpdateResponse;
import com.example.press_lab.service.emailService.EmailService;
import com.example.press_lab.service.news.NewsCreateService;
import com.example.press_lab.service.news.NewsDeleteService;
import com.example.press_lab.service.news.NewsReadAllService;
import com.example.press_lab.service.news.NewsReadService;
import com.example.press_lab.service.news.NewsRecentService;
import com.example.press_lab.service.news.NewsUpdateService;
import com.example.press_lab.service.news.NewsViewCountIncrementService;
import com.example.press_lab.service.news.NewsViewCountService;
import com.example.press_lab.service.subscriptionService.NotifySubscription;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {NewsController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class NewsControllerTest {
    @Autowired
    private NewsController newsController;

    @MockBean
    private NewsCreateService newsCreateService;

    @MockBean
    private NewsDeleteService newsDeleteService;

    @MockBean
    private NewsReadAllService newsReadAllService;

    @MockBean
    private NewsReadService newsReadService;

    @MockBean
    private NewsRecentService newsRecentService;

    @MockBean
    private NewsUpdateService newsUpdateService;

    @MockBean
    private NewsViewCountIncrementService newsViewCountIncrementService;

    @MockBean
    private NewsViewCountService newsViewCountService;

    /**
     * Method under test: {@link NewsController#create(NewsCreateRequest)}
     */
    @Test
    void testCreate() throws Exception {
        // Arrange
        NewsCreateResponse.NewsCreateResponseBuilder contentResult = NewsCreateResponse.builder()
                .content("Not all who wander are lost");
        NewsCreateResponse.NewsCreateResponseBuilder titleResult = contentResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .description("The characteristics of someone or something")
                .fkCategoryId(1L)
                .fkSubCategoryId(1L)
                .id(1L)
                .imageUrl("https://example.org/example")
                .status(NewsStatus.ACTIVE)
                .title("Dr");
        NewsCreateResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .viewCount(3L)
                .build();
        when(newsCreateService.create(Mockito.<NewsCreateRequest>any())).thenReturn(buildResult);

        NewsCreateRequest newsCreateRequest = new NewsCreateRequest();
        newsCreateRequest.setContent("Not all who wander are lost");
        newsCreateRequest.setDescription("The characteristics of someone or something");
        newsCreateRequest.setFkCategoryId(1L);
        newsCreateRequest.setFkSubCategoryId(1L);
        newsCreateRequest.setImageUrl("https://example.org/example");
        newsCreateRequest.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(newsCreateRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/news/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(newsController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"imageUrl\":\"https://example.org/example"
                                        + "\",\"viewCount\":3,\"description\":\"The characteristics of someone or something\",\"status\":\"ACTIVE\","
                                        + "\"fkCategoryId\":1,\"fkSubCategoryId\":1,\"createdAt\":[1970,1,1,0,0],\"updatedAt\":[1970,1,1,0,0]}"));
    }

    /**
     * Method under test: {@link NewsController#create(NewsCreateRequest)}
     */
    @Test
    void testCreate2() throws Exception {
        // Arrange
        NewsCreateResponse.NewsCreateResponseBuilder contentResult = NewsCreateResponse.builder()
                .content("Not all who wander are lost");
        NewsCreateResponse.NewsCreateResponseBuilder titleResult = contentResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .description("The characteristics of someone or something")
                .fkCategoryId(1L)
                .fkSubCategoryId(1L)
                .id(1L)
                .imageUrl("https://example.org/example")
                .status(NewsStatus.ACTIVE)
                .title("Dr");
        NewsCreateResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .viewCount(3L)
                .build();
        when(newsCreateService.create(Mockito.<NewsCreateRequest>any())).thenReturn(buildResult);

        NewsCreateRequest newsCreateRequest = new NewsCreateRequest();
        newsCreateRequest.setContent("");
        newsCreateRequest.setDescription("The characteristics of someone or something");
        newsCreateRequest.setFkCategoryId(1L);
        newsCreateRequest.setFkSubCategoryId(1L);
        newsCreateRequest.setImageUrl("https://example.org/example");
        newsCreateRequest.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(newsCreateRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/news/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(newsController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link NewsController#getNewsById(Long)}
     */
    @Test
    void testGetNewsById() throws Exception {
        // Arrange
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
        when(newsReadService.getNewsById(Mockito.<Long>any())).thenReturn(buildResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/news/{news-id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"imageUrl\":\"https://example.org/example"
                                        + "\",\"viewCount\":3,\"description\":\"The characteristics of someone or something\",\"status\":\"ACTIVE\","
                                        + "\"fkCategoryId\":1,\"fkSubCategoryId\":1,\"createdAt\":[1970,1,1,0,0],\"updatedAt\":[1970,1,1,0,0]}"));
    }

    /**
     * Method under test: {@link NewsController#getMostViewed(NewsReadByPage)}
     */
    @Test
    void testGetMostViewed() throws Exception {
        // Arrange
        when(newsReadAllService.getMostViewed(Mockito.<NewsReadByPage>any())).thenReturn(new ArrayList<>());

        NewsReadByPage newsReadByPage = new NewsReadByPage();
        newsReadByPage.setPage(1);
        newsReadByPage.setSize(3);
        String content = (new ObjectMapper()).writeValueAsString(newsReadByPage);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/news/most-viewed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link NewsController#incrementNewsViewCount(Long)}
     */
    @Test
    void testIncrementNewsViewCount() throws Exception {
        // Arrange
        doNothing().when(newsViewCountIncrementService).incrementNewsViewCount(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/news/increment/{news-id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link NewsController#incrementNewsViewCount(Long)}
     */
    @Test
    void testIncrementNewsViewCount2() throws Exception {
        // Arrange
        doNothing().when(newsViewCountIncrementService).incrementNewsViewCount(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/news/increment/{news-id}", 1L);
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link NewsController#getRecentNews(NewsReadByPage)}
     */
    @Test
    void testGetRecentNews() throws Exception {
        // Arrange
        when(newsRecentService.getRecentNews(Mockito.<NewsReadByPage>any())).thenReturn(new ArrayList<>());

        NewsReadByPage newsReadByPage = new NewsReadByPage();
        newsReadByPage.setPage(1);
        newsReadByPage.setSize(3);
        String content = (new ObjectMapper()).writeValueAsString(newsReadByPage);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/news/read-recent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link NewsController#getRecentNewsLast24Hours(NewsReadByPage)}
     */
    @Test
    void testGetRecentNewsLast24Hours() throws Exception {
        // Arrange
        when(newsRecentService.getRecentNewsLast24Hours(Mockito.<NewsReadByPage>any())).thenReturn(new ArrayList<>());

        NewsReadByPage newsReadByPage = new NewsReadByPage();
        newsReadByPage.setPage(1);
        newsReadByPage.setSize(3);
        String content = (new ObjectMapper()).writeValueAsString(newsReadByPage);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/news/read-recent-last24hours")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link NewsController#getMostViewedCategoryStatus()}
     */
    @Test
    void testGetMostViewedCategoryStatus() throws Exception {
        // Arrange
        when(newsViewCountService.getMostViewedCategoryStatus()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/news/most-viewed-category");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link NewsController#getMostViewedSubCategoryStatus()}
     */
    @Test
    void testGetMostViewedSubCategoryStatus() throws Exception {
        // Arrange
        when(newsViewCountService.getMostViewedSubCategoryStatus()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/news/most-viewed-subCategory");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link NewsController#getMostViewedSubCategoryStatusFromCategory(Long)}
     */
    @Test
    void testGetMostViewedSubCategoryStatusFromCategory() throws Exception {
        // Arrange
        when(newsViewCountService.getMostViewedSubCategoryStatusFromCategory(Mockito.<Long>any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders
                .post("/news/most-viewed-subCategory-from-category");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("fkCategoryId", String.valueOf(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link NewsController#getMost5ViewedCategoryStatus()}
     */
    @Test
    void testGetMost5ViewedCategoryStatus() throws Exception {
        // Arrange
        when(newsViewCountService.getMost5ViewedCategoryStatus()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/news/most5-viewed-categories");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link NewsController#getMost5ViewedSubCategoryStatus()}
     */
    @Test
    void testGetMost5ViewedSubCategoryStatus() throws Exception {
        // Arrange
        when(newsViewCountService.getMost5ViewedSubCategoryStatus()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/news/most5-viewed-subCategories");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link NewsController#update(NewsUpdateRequest)}
     */
    @Test
    void testUpdate() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        News news = new News();
        news.setContent("Not all who wander are lost");
        news.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        news.setDescription("The characteristics of someone or something");
        news.setFkCategoryId(1L);
        news.setFkSubCategoryId(1L);
        news.setId(1L);
        news.setImageUrl("https://example.org/example");
        news.setStatus(NewsStatus.ACTIVE);
        news.setTitle("Dr");
        news.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        news.setViewCount(3L);
        Optional<News> ofResult = Optional.of(news);

        News news2 = new News();
        news2.setContent("Not all who wander are lost");
        news2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        news2.setDescription("The characteristics of someone or something");
        news2.setFkCategoryId(1L);
        news2.setFkSubCategoryId(1L);
        news2.setId(1L);
        news2.setImageUrl("https://example.org/example");
        news2.setStatus(NewsStatus.ACTIVE);
        news2.setTitle("Dr");
        news2.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        news2.setViewCount(3L);
        NewsRepository newsRepository = mock(NewsRepository.class);
        when(newsRepository.save(Mockito.<News>any())).thenReturn(news2);
        when(newsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        NewsUpdateService updateService = new NewsUpdateService(newsRepository, new NewsMapperImpl());

        NewsRepository newsRepository2 = mock(NewsRepository.class);
        NotifySubscription notifySubscription = new NotifySubscription(new EmailService(new JavaMailSenderImpl()),
                mock(SubscriptionRepository.class));

        NewsCreateService createService = new NewsCreateService(newsRepository2, notifySubscription, new NewsMapperImpl());

        NewsRepository newsRepository3 = mock(NewsRepository.class);
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        SubCategoryRepository subCategoryRepository = mock(SubCategoryRepository.class);
        NewsReadAllService readService = new NewsReadAllService(newsRepository3, categoryRepository, subCategoryRepository,
                new NewsMapperImpl());

        NewsRepository newsRepository4 = mock(NewsRepository.class);
        NewsDeleteService deleteService = new NewsDeleteService(newsRepository4, new NewsMapperImpl());

        NewsViewCountService categoryService = new NewsViewCountService(mock(NewsRepository.class));
        NewsRepository newsRepository5 = mock(NewsRepository.class);
        NewsRecentService recentService = new NewsRecentService(newsRepository5, new NewsMapperImpl());

        NewsRepository newsRepository6 = mock(NewsRepository.class);
        NewsReadService newsReadService = new NewsReadService(newsRepository6, new NewsMapperImpl());

        NewsController newsController = new NewsController(createService, readService, updateService, deleteService,
                categoryService, recentService, newsReadService, new NewsViewCountIncrementService(mock(NewsRepository.class)));

        // Act
        ResponseEntity<NewsUpdateResponse> actualUpdateResult = newsController.update(new NewsUpdateRequest());

        // Assert
        verify(newsRepository).findById(isNull());
        verify(newsRepository).save(isA(News.class));
        assertEquals(200, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.hasBody());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link NewsController#update(NewsUpdateRequest)}
     */
    @Test
    void testUpdate2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        NewsUpdateService updateService = mock(NewsUpdateService.class);
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
        when(updateService.update(Mockito.<NewsUpdateRequest>any())).thenReturn(buildResult);
        NewsRepository newsRepository = mock(NewsRepository.class);
        NotifySubscription notifySubscription = new NotifySubscription(new EmailService(new JavaMailSenderImpl()),
                mock(SubscriptionRepository.class));

        NewsCreateService createService = new NewsCreateService(newsRepository, notifySubscription, new NewsMapperImpl());

        NewsRepository newsRepository2 = mock(NewsRepository.class);
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        SubCategoryRepository subCategoryRepository = mock(SubCategoryRepository.class);
        NewsReadAllService readService = new NewsReadAllService(newsRepository2, categoryRepository, subCategoryRepository,
                new NewsMapperImpl());

        NewsRepository newsRepository3 = mock(NewsRepository.class);
        NewsDeleteService deleteService = new NewsDeleteService(newsRepository3, new NewsMapperImpl());

        NewsViewCountService categoryService = new NewsViewCountService(mock(NewsRepository.class));
        NewsRepository newsRepository4 = mock(NewsRepository.class);
        NewsRecentService recentService = new NewsRecentService(newsRepository4, new NewsMapperImpl());

        NewsRepository newsRepository5 = mock(NewsRepository.class);
        NewsReadService newsReadService = new NewsReadService(newsRepository5, new NewsMapperImpl());

        NewsController newsController = new NewsController(createService, readService, updateService, deleteService,
                categoryService, recentService, newsReadService, new NewsViewCountIncrementService(mock(NewsRepository.class)));

        // Act
        ResponseEntity<NewsUpdateResponse> actualUpdateResult = newsController.update(new NewsUpdateRequest());

        // Assert
        verify(updateService).update(isA(NewsUpdateRequest.class));
        assertEquals(200, actualUpdateResult.getStatusCodeValue());
        assertTrue(actualUpdateResult.hasBody());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link NewsController#deleteAll()}
     */
    @Test
    void testDeleteAll() throws Exception {
        // Arrange
        doNothing().when(newsDeleteService).deleteAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/news/delete-all");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link NewsController#deleteAll()}
     */
    @Test
    void testDeleteAll2() throws Exception {
        // Arrange
        doNothing().when(newsDeleteService).deleteAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/news/delete-all");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link NewsController#deleteById(NewsDeleteRequest)}
     */
    @Test
    void testDeleteById() throws Exception {
        // Arrange
        doNothing().when(newsDeleteService).deleteById(Mockito.<NewsDeleteRequest>any());

        NewsDeleteRequest newsDeleteRequest = new NewsDeleteRequest();
        newsDeleteRequest.setId(1L);
        String content = (new ObjectMapper()).writeValueAsString(newsDeleteRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/news/delete-by-id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link NewsController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        // Arrange
        when(newsReadAllService.getAllNews()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/news/read-all");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link NewsController#getByCategory(NewsReadByCategoryRequest)}
     */
    @Test
    void testGetByCategory() throws Exception {
        // Arrange
        when(newsReadAllService.getNewsByCategory(Mockito.<NewsReadByCategoryRequest>any())).thenReturn(new ArrayList<>());

        NewsReadByCategoryRequest newsReadByCategoryRequest = new NewsReadByCategoryRequest();
        newsReadByCategoryRequest.setCategoryId(1L);
        newsReadByCategoryRequest.setPage(1);
        newsReadByCategoryRequest.setSize(3);
        String content = (new ObjectMapper()).writeValueAsString(newsReadByCategoryRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/news/by-category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link NewsController#getByCategoryAndSubCategory(NewsReadByCategoryAndSubCategoryRequest)}
     */
    @Test
    void testGetByCategoryAndSubCategory() throws Exception {
        // Arrange
        when(newsReadAllService.getNewsByCategoryAndSubCategory(Mockito.<NewsReadByCategoryAndSubCategoryRequest>any()))
                .thenReturn(new ArrayList<>());

        NewsReadByCategoryAndSubCategoryRequest newsReadByCategoryAndSubCategoryRequest = new NewsReadByCategoryAndSubCategoryRequest();
        newsReadByCategoryAndSubCategoryRequest.setCategoryId(1L);
        newsReadByCategoryAndSubCategoryRequest.setPage(1);
        newsReadByCategoryAndSubCategoryRequest.setSize(3);
        newsReadByCategoryAndSubCategoryRequest.setSubCategoryId(1L);
        String content = (new ObjectMapper()).writeValueAsString(newsReadByCategoryAndSubCategoryRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/news/by-category-subCategory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link NewsController#getBySubCategory(NewsReadBySubCategoryRequest)}
     */
    @Test
    void testGetBySubCategory() throws Exception {
        // Arrange
        when(newsReadAllService.getNewsBySubCategory(Mockito.<NewsReadBySubCategoryRequest>any()))
                .thenReturn(new ArrayList<>());

        NewsReadBySubCategoryRequest newsReadBySubCategoryRequest = new NewsReadBySubCategoryRequest();
        newsReadBySubCategoryRequest.setPage(1);
        newsReadBySubCategoryRequest.setSize(3);
        newsReadBySubCategoryRequest.setSubCategoryId(1L);
        String content = (new ObjectMapper()).writeValueAsString(newsReadBySubCategoryRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/news/by-subCategory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link NewsController#getContent(NewsReadRequest)}
     */
    @Test
    void testGetContent() throws Exception {
        // Arrange
        when(newsReadAllService.getNewsByContent(Mockito.<NewsReadRequest>any())).thenReturn(new ArrayList<>());

        NewsReadRequest newsReadRequest = new NewsReadRequest();
        newsReadRequest.setContent("Not all who wander are lost");
        newsReadRequest.setId(1L);
        newsReadRequest.setStatus(NewsStatus.ACTIVE);
        String content = (new ObjectMapper()).writeValueAsString(newsReadRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/news/by-content")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link NewsController#getStatus(NewsReadByStatusRequest)}
     */
    @Test
    void testGetStatus() throws Exception {
        // Arrange
        when(newsReadAllService.getNewsByStatus(Mockito.<NewsReadByStatusRequest>any())).thenReturn(new ArrayList<>());

        NewsReadByStatusRequest newsReadByStatusRequest = new NewsReadByStatusRequest();
        newsReadByStatusRequest.setStatus(NewsStatus.ACTIVE);
        String content = (new ObjectMapper()).writeValueAsString(newsReadByStatusRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/news/by-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(newsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
