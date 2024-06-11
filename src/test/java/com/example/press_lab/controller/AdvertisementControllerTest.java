package com.example.press_lab.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.press_lab.request.advertisement.AdvertisementCreateRequest;
import com.example.press_lab.request.advertisement.AdvertisementDeleteRequest;
import com.example.press_lab.request.advertisement.AdvertisementReadRequest;
import com.example.press_lab.request.advertisement.AdvertisementUpdateRequest;
import com.example.press_lab.response.advertisement.AdvertisementCreateResponse;
import com.example.press_lab.response.advertisement.AdvertisementUpdateResponse;
import com.example.press_lab.service.advertisement.AdvertisementCreateService;
import com.example.press_lab.service.advertisement.AdvertisementDeleteService;
import com.example.press_lab.service.advertisement.AdvertisementReadService;
import com.example.press_lab.service.advertisement.AdvertisementUpdateService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AdvertisementController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AdvertisementControllerTest {
    @Autowired
    private AdvertisementController advertisementController;

    @MockBean
    private AdvertisementCreateService advertisementCreateService;

    @MockBean
    private AdvertisementDeleteService advertisementDeleteService;

    @MockBean
    private AdvertisementReadService advertisementReadService;

    @MockBean
    private AdvertisementUpdateService advertisementUpdateService;

    /**
     * Method under test:
     * {@link AdvertisementController#create(AdvertisementCreateRequest)}
     */
    @Test
    void testCreate() throws Exception {
        // Arrange
        AdvertisementCreateResponse.AdvertisementCreateResponseBuilder contentResult = AdvertisementCreateResponse.builder()
                .content("Not all who wander are lost");
        AdvertisementCreateResponse.AdvertisementCreateResponseBuilder titleResult = contentResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .id(1L)
                .imageUrl("https://example.org/example")
                .sourceUrl("https://example.org/example")
                .title("Dr");
        AdvertisementCreateResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .viewCount(3L)
                .build();
        when(advertisementCreateService.create(Mockito.<AdvertisementCreateRequest>any())).thenReturn(buildResult);

        AdvertisementCreateRequest advertisementCreateRequest = new AdvertisementCreateRequest();
        advertisementCreateRequest.setContent("Not all who wander are lost");
        advertisementCreateRequest.setImageUrl("https://example.org/example");
        advertisementCreateRequest.setSourceUrl("https://example.org/example");
        advertisementCreateRequest.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(advertisementCreateRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/advertisements/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(advertisementController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"imageUrl\":\"https://example.org/example"
                                        + "\",\"sourceUrl\":\"https://example.org/example\",\"viewCount\":3,\"createdAt\":[1970,1,1,0,0],\"updatedAt\":[1970"
                                        + ",1,1,0,0]}"));
    }

    /**
     * Method under test: {@link AdvertisementController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        // Arrange
        when(advertisementReadService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/advertisements/all");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(advertisementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link AdvertisementController#getAdvertisementByContent(AdvertisementReadRequest)}
     */
    @Test
    void testGetAdvertisementByContent() throws Exception {
        // Arrange
        when(advertisementReadService.getAdvertisementByContent(Mockito.<AdvertisementReadRequest>any()))
                .thenReturn(new ArrayList<>());

        AdvertisementReadRequest advertisementReadRequest = new AdvertisementReadRequest();
        advertisementReadRequest.setContent("Not all who wander are lost");
        advertisementReadRequest.setId(1L);
        advertisementReadRequest.setSourceUrl("https://example.org/example");
        advertisementReadRequest.setViewCount(3L);
        String content = (new ObjectMapper()).writeValueAsString(advertisementReadRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/advertisements/by-content")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(advertisementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link AdvertisementController#getAdvertisementByImageUrl(AdvertisementReadRequest)}
     */
    @Test
    void testGetAdvertisementByImageUrl() throws Exception {
        // Arrange
        when(advertisementReadService.getAdvertisementByImageUrl(Mockito.<AdvertisementReadRequest>any()))
                .thenReturn(new ArrayList<>());

        AdvertisementReadRequest advertisementReadRequest = new AdvertisementReadRequest();
        advertisementReadRequest.setContent("Not all who wander are lost");
        advertisementReadRequest.setId(1L);
        advertisementReadRequest.setSourceUrl("https://example.org/example");
        advertisementReadRequest.setViewCount(3L);
        String content = (new ObjectMapper()).writeValueAsString(advertisementReadRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/advertisements/by-image")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(advertisementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link AdvertisementController#update(AdvertisementUpdateRequest)}
     */
    @Test
    void testUpdate() throws Exception {
        // Arrange
        AdvertisementUpdateResponse.AdvertisementUpdateResponseBuilder contentResult = AdvertisementUpdateResponse.builder()
                .content("Not all who wander are lost");
        AdvertisementUpdateResponse.AdvertisementUpdateResponseBuilder titleResult = contentResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .id(1L)
                .imageUrl("https://example.org/example")
                .sourceUrl("https://example.org/example")
                .title("Dr");
        AdvertisementUpdateResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .viewCount(3L)
                .build();
        when(advertisementUpdateService.update(Mockito.<AdvertisementUpdateRequest>any())).thenReturn(buildResult);

        AdvertisementUpdateRequest advertisementUpdateRequest = new AdvertisementUpdateRequest();
        advertisementUpdateRequest.setContent("Not all who wander are lost");
        advertisementUpdateRequest.setId(1L);
        advertisementUpdateRequest.setImageUrl("https://example.org/example");
        advertisementUpdateRequest.setSourceUrl("https://example.org/example");
        advertisementUpdateRequest.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(advertisementUpdateRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/advertisements/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(advertisementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"imageUrl\":\"https://example.org/example"
                                        + "\",\"sourceUrl\":\"https://example.org/example\",\"viewCount\":3,\"createdAt\":[1970,1,1,0,0],\"updatedAt\":[1970"
                                        + ",1,1,0,0]}"));
    }

    /**
     * Method under test: {@link AdvertisementController#deleteAll()}
     */
    @Test
    void testDeleteAll() throws Exception {
        // Arrange
        doNothing().when(advertisementDeleteService).deleteAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/advertisements/delete-all");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(advertisementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AdvertisementController#deleteAll()}
     */
    @Test
    void testDeleteAll2() throws Exception {
        // Arrange
        doNothing().when(advertisementDeleteService).deleteAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/advertisements/delete-all");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(advertisementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link AdvertisementController#deleteById(AdvertisementDeleteRequest)}
     */
    @Test
    void testDeleteById() throws Exception {
        // Arrange
        doNothing().when(advertisementDeleteService).deleteById(Mockito.<AdvertisementDeleteRequest>any());

        AdvertisementDeleteRequest advertisementDeleteRequest = new AdvertisementDeleteRequest();
        advertisementDeleteRequest.setId(1L);
        String content = (new ObjectMapper()).writeValueAsString(advertisementDeleteRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/advertisements/delete-by-id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(advertisementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
