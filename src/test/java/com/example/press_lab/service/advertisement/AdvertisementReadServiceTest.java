package com.example.press_lab.service.advertisement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.Advertisement;
import com.example.press_lab.exception.advertisement.AdvertisementContentNotFoundException;
import com.example.press_lab.exception.advertisement.AdvertisementSourceUrlNotFoundException;
import com.example.press_lab.mappers.AdvertisementMapper;
import com.example.press_lab.repository.AdvertisementRepository;
import com.example.press_lab.request.advertisement.AdvertisementReadRequest;
import com.example.press_lab.response.advertisement.AdvertisementReadResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.press_lab.service.util.AdvertisementUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AdvertisementReadService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AdvertisementReadServiceTest {
    @MockBean
    private AdvertisementMapper advertisementMapper;

    @Autowired
    private AdvertisementReadService advertisementReadService;

    @MockBean
    private AdvertisementRepository advertisementRepository;

    /**
     * Method under test: {@link AdvertisementReadService#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        when(advertisementRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<AdvertisementReadResponse> actualAll = advertisementReadService.getAll();

        // Assert
        verify(advertisementRepository).findAll();
        assertTrue(actualAll.isEmpty());
    }

    /**
     * Method under test:
     * {@link AdvertisementReadService#getAdvertisementByContent(AdvertisementReadRequest)}
     */
    @Test
    void testGetAdvertisementByContent() {
        // Arrange
        Advertisement advertisement = AdvertisementUtil.advertisement();
        Optional<Advertisement> ofResult = Optional.of(advertisement);
        when(advertisementRepository.findByContent(Mockito.<String>any())).thenReturn(ofResult);
        AdvertisementReadResponse.AdvertisementReadResponseBuilder contentResult = AdvertisementReadResponse.builder()
                .content("Not all who wander are lost");
        AdvertisementReadResponse.AdvertisementReadResponseBuilder titleResult = contentResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .id(1L)
                .imageUrl("https://example.org/example")
                .sourceUrl("https://example.org/example")
                .title("Dr");
        AdvertisementReadResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .viewCount(3L)
                .build();
        when(advertisementMapper.mapReadToResponse(Mockito.<Advertisement>any())).thenReturn(buildResult);

        // Act
        List<AdvertisementReadResponse> actualAdvertisementByContent = advertisementReadService
                .getAdvertisementByContent(new AdvertisementReadRequest());

        // Assert
        verify(advertisementMapper).mapReadToResponse(isA(Advertisement.class));
        verify(advertisementRepository, atLeast(1)).findByContent(isNull());
        assertEquals(1, actualAdvertisementByContent.size());
    }

    /**
     * Method under test:
     * {@link AdvertisementReadService#getAdvertisementByContent(AdvertisementReadRequest)}
     */
    @Test
    void testGetAdvertisementByContent2() {
        // Arrange
        Optional<Advertisement> emptyResult = Optional.empty();
        when(advertisementRepository.findByContent(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(AdvertisementContentNotFoundException.class,
                () -> advertisementReadService.getAdvertisementByContent(new AdvertisementReadRequest()));
        verify(advertisementRepository).findByContent(isNull());
    }

    /**
     * Method under test:
     * {@link AdvertisementReadService#getAdvertisementByImageUrl(AdvertisementReadRequest)}
     */
    @Test
    void testGetAdvertisementByImageUrl() {
        // Arrange
        when(advertisementRepository.findBySourceUrl(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(AdvertisementSourceUrlNotFoundException.class,
                () -> advertisementReadService.getAdvertisementByImageUrl(new AdvertisementReadRequest()));
        verify(advertisementRepository).findBySourceUrl(isNull());
    }

    /**
     * Method under test:
     * {@link AdvertisementReadService#getAdvertisementByImageUrl(AdvertisementReadRequest)}
     */
    @Test
    void testGetAdvertisementByImageUrl2() {
        // Arrange
        Advertisement advertisement = AdvertisementUtil.advertisement();

        ArrayList<Advertisement> advertisementList = new ArrayList<>();
        advertisementList.add(advertisement);
        when(advertisementRepository.findBySourceUrl(Mockito.<String>any())).thenReturn(advertisementList);
        AdvertisementReadResponse.AdvertisementReadResponseBuilder contentResult = AdvertisementReadResponse.builder()
                .content("Not all who wander are lost");
        AdvertisementReadResponse.AdvertisementReadResponseBuilder titleResult = contentResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .id(1L)
                .imageUrl("https://example.org/example")
                .sourceUrl("https://example.org/example")
                .title("Dr");
        AdvertisementReadResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .viewCount(3L)
                .build();
        when(advertisementMapper.mapReadToResponse(Mockito.<Advertisement>any())).thenReturn(buildResult);

        // Act
        List<AdvertisementReadResponse> actualAdvertisementByImageUrl = advertisementReadService
                .getAdvertisementByImageUrl(new AdvertisementReadRequest());

        // Assert
        verify(advertisementMapper).mapReadToResponse(isA(Advertisement.class));
        verify(advertisementRepository, atLeast(1)).findBySourceUrl(isNull());
        assertEquals(1, actualAdvertisementByImageUrl.size());
    }
}
