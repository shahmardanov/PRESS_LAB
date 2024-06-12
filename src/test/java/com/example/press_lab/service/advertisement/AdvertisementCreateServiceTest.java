package com.example.press_lab.service.advertisement;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.Advertisement;
import com.example.press_lab.exception.advertisement.AdvertisementConflictException;
import com.example.press_lab.mappers.AdvertisementMapper;
import com.example.press_lab.repository.AdvertisementRepository;
import com.example.press_lab.request.advertisement.AdvertisementCreateRequest;
import com.example.press_lab.response.advertisement.AdvertisementCreateResponse;

import java.time.LocalDate;
import java.time.LocalTime;
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

@ContextConfiguration(classes = {AdvertisementCreateService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AdvertisementCreateServiceTest {
    @Autowired
    private AdvertisementCreateService advertisementCreateService;

    @MockBean
    private AdvertisementMapper advertisementMapper;

    @MockBean
    private AdvertisementRepository advertisementRepository;

    /**
     * Method under test:
     * {@link AdvertisementCreateService#create(AdvertisementCreateRequest)}
     */
    @Test
    void testCreate() {
        // Arrange
        Advertisement advertisement = AdvertisementUtil.advertisement();
        Optional<Advertisement> ofResult = Optional.of(advertisement);
        when(advertisementRepository.findByContent(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(AdvertisementConflictException.class,
                () -> advertisementCreateService.create(new AdvertisementCreateRequest("Dr", "Not all who wander are lost",
                        "https://example.org/example", "https://example.org/example")));
        verify(advertisementRepository).findByContent(eq("Not all who wander are lost"));
    }

    /**
     * Method under test:
     * {@link AdvertisementCreateService#create(AdvertisementCreateRequest)}
     */
    @Test
    void testCreate2() {
        // Arrange
        Advertisement advertisement = AdvertisementUtil.advertisement();
        when(advertisementRepository.save(Mockito.<Advertisement>any())).thenReturn(advertisement);
        Optional<Advertisement> emptyResult = Optional.empty();
        when(advertisementRepository.findByContent(Mockito.<String>any())).thenReturn(emptyResult);

        Advertisement advertisement2 = AdvertisementUtil.advertisement();
        when(advertisementMapper.mapRequestToEntity(Mockito.<AdvertisementCreateRequest>any())).thenReturn(advertisement2);
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
        when(advertisementMapper.mapCreateToResponse(Mockito.<Advertisement>any())).thenReturn(buildResult);

        // Act
        AdvertisementCreateResponse actualCreateResult = advertisementCreateService.create(new AdvertisementCreateRequest(
                "Dr", "Not all who wander are lost", "https://example.org/example", "https://example.org/example"));

        // Assert
        verify(advertisementMapper).mapCreateToResponse(isA(Advertisement.class));
        verify(advertisementMapper).mapRequestToEntity(isA(AdvertisementCreateRequest.class));
        verify(advertisementRepository).findByContent(eq("Not all who wander are lost"));
        verify(advertisementRepository).save(isA(Advertisement.class));
        LocalTime expectedToLocalTimeResult = actualCreateResult.getUpdatedAt().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualCreateResult.getCreatedAt().toLocalTime());
    }
}
