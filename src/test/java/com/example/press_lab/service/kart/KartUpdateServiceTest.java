package com.example.press_lab.service.kart;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.Kart;
import com.example.press_lab.exception.kart.KartNotFoundException;
import com.example.press_lab.mappers.KartMapper;
import com.example.press_lab.repository.KartRepository;
import com.example.press_lab.request.kart.KartUpdateRequest;
import com.example.press_lab.response.kart.KartUpdateResponse;

import java.time.LocalDate;
import java.time.LocalTime;
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

@ContextConfiguration(classes = {KartUpdateService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class KartUpdateServiceTest {
    @MockBean
    private KartMapper kartMapper;

    @MockBean
    private KartRepository kartRepository;

    @Autowired
    private KartUpdateService kartUpdateService;

    /**
     * Method under test: {@link KartUpdateService#update(KartUpdateRequest)}
     */
    @Test
    void testUpdate() {
        // Arrange
        Kart kart = KartUtil.kart();
        Optional<Kart> ofResult = Optional.of(kart);

        Kart kart2 = KartUtil.kart();
        when(kartRepository.save(Mockito.<Kart>any())).thenReturn(kart2);
        when(kartRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        KartUpdateResponse.KartUpdateResponseBuilder contentResult = KartUpdateResponse.builder()
                .content("Not all who wander are lost");
        KartUpdateResponse.KartUpdateResponseBuilder titleResult = contentResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .description("The characteristics of someone or something")
                .fkNewsId(1L)
                .id(1L)
                .imageUrl("https://example.org/example")
                .title("Dr");
        KartUpdateResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay()).build();
        when(kartMapper.mapUpdateToResponse(Mockito.<Kart>any())).thenReturn(buildResult);

        // Act
        KartUpdateResponse actualUpdateResult = kartUpdateService.update(new KartUpdateRequest());

        // Assert
        verify(kartMapper).mapUpdateToResponse(isA(Kart.class));
        verify(kartRepository).findById(isNull());
        verify(kartRepository).save(isA(Kart.class));
        LocalTime expectedToLocalTimeResult = actualUpdateResult.getUpdatedAt().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualUpdateResult.getCreatedAt().toLocalTime());
    }

    /**
     * Method under test: {@link KartUpdateService#update(KartUpdateRequest)}
     */
    @Test
    void testUpdate2() {
        // Arrange
        Kart kart = KartUtil.kart();
        Optional<Kart> ofResult = Optional.of(kart);

        Kart kart2 = KartUtil.kart();
        when(kartRepository.save(Mockito.<Kart>any())).thenReturn(kart2);
        when(kartRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(kartMapper.mapUpdateToResponse(Mockito.<Kart>any())).thenThrow(new KartNotFoundException());

        // Act and Assert
        assertThrows(KartNotFoundException.class, () -> kartUpdateService.update(new KartUpdateRequest()));
        verify(kartMapper).mapUpdateToResponse(isA(Kart.class));
        verify(kartRepository).findById(isNull());
        verify(kartRepository).save(isA(Kart.class));
    }

    /**
     * Method under test: {@link KartUpdateService#update(KartUpdateRequest)}
     */
    @Test
    void testUpdate3() {
        // Arrange
        Kart kart = KartUtil.kart();
        Optional<Kart> ofResult = Optional.of(kart);

        Kart kart2 =KartUtil.kart();
        when(kartRepository.save(Mockito.<Kart>any())).thenReturn(kart2);
        when(kartRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        KartUpdateResponse.KartUpdateResponseBuilder contentResult = KartUpdateResponse.builder()
                .content("Not all who wander are lost");
        KartUpdateResponse.KartUpdateResponseBuilder titleResult = contentResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .description("The characteristics of someone or something")
                .fkNewsId(1L)
                .id(1L)
                .imageUrl("https://example.org/example")
                .title("Dr");
        KartUpdateResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay()).build();
        when(kartMapper.mapUpdateToResponse(Mockito.<Kart>any())).thenReturn(buildResult);

        // Act
        KartUpdateResponse actualUpdateResult = kartUpdateService
                .update(new KartUpdateRequest(1L, "Dr", "Not all who wander are lost", "https://example.org/example", 1L,
                        "The characteristics of someone or something", LocalDate.of(1970, 1, 1).atStartOfDay()));

        // Assert
        verify(kartMapper).mapUpdateToResponse(isA(Kart.class));
        verify(kartRepository).findById(eq(1L));
        verify(kartRepository).save(isA(Kart.class));
        LocalTime expectedToLocalTimeResult = actualUpdateResult.getUpdatedAt().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualUpdateResult.getCreatedAt().toLocalTime());
    }
}
