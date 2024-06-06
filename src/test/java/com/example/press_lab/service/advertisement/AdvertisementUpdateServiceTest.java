package com.example.press_lab.service.advertisement;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.Advertisement;
import com.example.press_lab.exception.advertisement.AdvertisementNotFoundException;
import com.example.press_lab.mappers.AdvertisementMapper;
import com.example.press_lab.repository.AdvertisementRepository;
import com.example.press_lab.request.advertisement.AdvertisementUpdateRequest;
import com.example.press_lab.response.advertisement.AdvertisementUpdateResponse;

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

@ContextConfiguration(classes = {AdvertisementUpdateService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AdvertisementUpdateServiceTest {
    @MockBean
    private AdvertisementMapper advertisementMapper;

    @MockBean
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AdvertisementUpdateService advertisementUpdateService;

    /**
     * Method under test:
     * {@link AdvertisementUpdateService#update(AdvertisementUpdateRequest)}
     */
    @Test
    void testUpdate() {
        // Arrange
        Advertisement advertisement = AdvertisementUtil.advertisement();
        Optional<Advertisement> ofResult = Optional.of(advertisement);

        Advertisement advertisement2 = AdvertisementUtil.advertisement();
        when(advertisementRepository.save(Mockito.<Advertisement>any())).thenReturn(advertisement2);
        when(advertisementRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Advertisement advertisement3 = AdvertisementUtil.advertisement();
        when(advertisementMapper.updateAdvertisement(Mockito.<AdvertisementUpdateRequest>any(),
                Mockito.<Advertisement>any())).thenReturn(advertisement3);
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
        when(advertisementMapper.mapUpdateToResponse(Mockito.<Advertisement>any())).thenReturn(buildResult);

        // Act
        AdvertisementUpdateResponse actualUpdateResult = advertisementUpdateService
                .update(new AdvertisementUpdateRequest());

        // Assert
        verify(advertisementMapper).mapUpdateToResponse(isA(Advertisement.class));
        verify(advertisementMapper).updateAdvertisement(isA(AdvertisementUpdateRequest.class), isA(Advertisement.class));
        verify(advertisementRepository).findById(isNull());
        verify(advertisementRepository).save(isA(Advertisement.class));
        LocalTime expectedToLocalTimeResult = actualUpdateResult.getUpdatedAt().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualUpdateResult.getCreatedAt().toLocalTime());
    }

    /**
     * Method under test:
     * {@link AdvertisementUpdateService#update(AdvertisementUpdateRequest)}
     */
    @Test
    void testUpdate2() {
        // Arrange
        Advertisement advertisement = AdvertisementUtil.advertisement();
        Optional<Advertisement> ofResult = Optional.of(advertisement);
        when(advertisementRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(advertisementMapper.updateAdvertisement(Mockito.<AdvertisementUpdateRequest>any(),
                Mockito.<Advertisement>any())).thenThrow(new AdvertisementNotFoundException());

        // Act and Assert
        assertThrows(AdvertisementNotFoundException.class,
                () -> advertisementUpdateService.update(new AdvertisementUpdateRequest()));
        verify(advertisementMapper).updateAdvertisement(isA(AdvertisementUpdateRequest.class), isA(Advertisement.class));
        verify(advertisementRepository).findById(isNull());
    }
}
