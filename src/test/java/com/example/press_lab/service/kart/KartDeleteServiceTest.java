package com.example.press_lab.service.kart;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.Kart;
import com.example.press_lab.exception.kart.KartNotFoundException;
import com.example.press_lab.repository.KartRepository;
import com.example.press_lab.request.kart.KartDeleteRequest;

import java.time.LocalDate;
import java.util.Optional;

import com.example.press_lab.service.util.KartUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {KartDeleteService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class KartDeleteServiceTest {
    @Autowired
    private KartDeleteService kartDeleteService;

    @MockBean
    private KartRepository kartRepository;

    /**
     * Method under test: {@link KartDeleteService#deleteAll()}
     */
    @Test
    void testDeleteAll() {
        // Arrange
        doNothing().when(kartRepository).deleteAll();

        // Act
        kartDeleteService.deleteAll();

        // Assert that nothing has changed
        verify(kartRepository).deleteAll();
    }

    /**
     * Method under test: {@link KartDeleteService#deleteAll()}
     */
    @Test
    void testDeleteAll2() {
        // Arrange
        doThrow(new KartNotFoundException()).when(kartRepository).deleteAll();

        // Act and Assert
        assertThrows(KartNotFoundException.class, () -> kartDeleteService.deleteAll());
        verify(kartRepository).deleteAll();
    }

    /**
     * Method under test: {@link KartDeleteService#deleteById(KartDeleteRequest)}
     */
    @Test
    void testDeleteById() {
        // Arrange
        Kart kart = KartUtil.kart();
        Optional<Kart> ofResult = Optional.of(kart);
        doNothing().when(kartRepository).deleteById(Mockito.<Long>any());
        when(kartRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        kartDeleteService.deleteById(new KartDeleteRequest());

        // Assert that nothing has changed
        verify(kartRepository).deleteById(isNull());
        verify(kartRepository).findById(isNull());
    }

    /**
     * Method under test: {@link KartDeleteService#deleteById(KartDeleteRequest)}
     */
    @Test
    void testDeleteById2() {
        // Arrange
        Kart kart = KartUtil.kart();
        Optional<Kart> ofResult = Optional.of(kart);
        doThrow(new KartNotFoundException()).when(kartRepository).deleteById(Mockito.<Long>any());
        when(kartRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(KartNotFoundException.class, () -> kartDeleteService.deleteById(new KartDeleteRequest()));
        verify(kartRepository).deleteById(isNull());
        verify(kartRepository).findById(isNull());
    }
}
