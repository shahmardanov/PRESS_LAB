package com.example.press_lab.service.advertisement;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.Advertisement;
import com.example.press_lab.exception.advertisement.AdvertisementNotFoundException;
import com.example.press_lab.repository.AdvertisementRepository;
import com.example.press_lab.request.advertisement.AdvertisementDeleteRequest;

import java.time.LocalDate;
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

@ContextConfiguration(classes = {AdvertisementDeleteService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AdvertisementDeleteServiceTest {
    @Autowired
    private AdvertisementDeleteService advertisementDeleteService;

    @MockBean
    private AdvertisementRepository advertisementRepository;

    /**
     * Method under test: {@link AdvertisementDeleteService#deleteAll()}
     */
    @Test
    void testDeleteAll() {
        // Arrange
        doNothing().when(advertisementRepository).deleteAll();

        // Act
        advertisementDeleteService.deleteAll();

        // Assert that nothing has changed
        verify(advertisementRepository).deleteAll();
    }

    /**
     * Method under test: {@link AdvertisementDeleteService#deleteAll()}
     */
    @Test
    void testDeleteAll2() {
        // Arrange
        doThrow(new AdvertisementNotFoundException()).when(advertisementRepository).deleteAll();

        // Act and Assert
        assertThrows(AdvertisementNotFoundException.class, () -> advertisementDeleteService.deleteAll());
        verify(advertisementRepository).deleteAll();
    }

    /**
     * Method under test:
     * {@link AdvertisementDeleteService#deleteById(AdvertisementDeleteRequest)}
     */
    @Test
    void testDeleteById() {
        // Arrange
        Advertisement advertisement = AdvertisementUtil.advertisement();
        Optional<Advertisement> ofResult = Optional.of(advertisement);
        doNothing().when(advertisementRepository).deleteById(Mockito.<Long>any());
        when(advertisementRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        advertisementDeleteService.deleteById(new AdvertisementDeleteRequest());

        // Assert that nothing has changed
        verify(advertisementRepository).deleteById(isNull());
        verify(advertisementRepository).findById(isNull());
    }

    /**
     * Method under test:
     * {@link AdvertisementDeleteService#deleteById(AdvertisementDeleteRequest)}
     */
    @Test
    void testDeleteById2() {
        // Arrange
        Advertisement advertisement = AdvertisementUtil.advertisement();
        Optional<Advertisement> ofResult = Optional.of(advertisement);
        doThrow(new AdvertisementNotFoundException()).when(advertisementRepository).deleteById(Mockito.<Long>any());
        when(advertisementRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(AdvertisementNotFoundException.class,
                () -> advertisementDeleteService.deleteById(new AdvertisementDeleteRequest()));
        verify(advertisementRepository).deleteById(isNull());
        verify(advertisementRepository).findById(isNull());
    }
}
