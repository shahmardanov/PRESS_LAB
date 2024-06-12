package com.example.press_lab.service.kart;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.press_lab.entity.Kart;
import com.example.press_lab.exception.kart.KartConflictException;
import com.example.press_lab.mappers.KartMapper;
import com.example.press_lab.repository.KartRepository;
import com.example.press_lab.request.kart.KartCreateRequest;
import com.example.press_lab.response.kart.KartCreateResponse;

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

@ContextConfiguration(classes = {KartCreateService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class KartCreateServiceTest {
    @Autowired
    private KartCreateService kartCreateService;

    @MockBean
    private KartMapper kartMapper;

    @MockBean
    private KartRepository kartRepository;

    /**
     * Method under test: {@link KartCreateService#create(KartCreateRequest)}
     */
    @Test
    void testCreate() {
        // Arrange
        Kart kart = KartUtil.kart();

        Optional<Kart> ofResult = Optional.of(kart);
        when(kartRepository.findByContent(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(KartConflictException.class, () -> kartCreateService.create(new KartCreateRequest()));
        verify(kartRepository).findByContent(isNull());
    }

    /**
     * Method under test: {@link KartCreateService#create(KartCreateRequest)}
     */
    @Test
    void testCreate2() {
        // Arrange
        Kart kart = KartUtil.kart();
        when(kartRepository.save(Mockito.<Kart>any())).thenReturn(kart);
        Optional<Kart> emptyResult = Optional.empty();
        when(kartRepository.findByContent(Mockito.<String>any())).thenReturn(emptyResult);

        Kart kart2 = KartUtil.kart();
        when(kartMapper.mapRequestToEntity(Mockito.<KartCreateRequest>any())).thenReturn(kart2);
        KartCreateResponse.KartCreateResponseBuilder contentResult = KartCreateResponse.builder()
                .content("Not all who wander are lost");
        KartCreateResponse.KartCreateResponseBuilder titleResult = contentResult
                .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
                .description("The characteristics of someone or something")
                .fkNewsId(1L)
                .id(1L)
                .imageUrl("https://example.org/example")
                .title("Dr");
        KartCreateResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay()).build();
        when(kartMapper.mapCreateToResponse(Mockito.<Kart>any())).thenReturn(buildResult);

        // Act
        KartCreateResponse actualCreateResult = kartCreateService.create(new KartCreateRequest());

        // Assert
        verify(kartMapper).mapCreateToResponse(isA(Kart.class));
        verify(kartMapper).mapRequestToEntity(isA(KartCreateRequest.class));
        verify(kartRepository).findByContent(isNull());
        verify(kartRepository).save(isA(Kart.class));
        LocalTime expectedToLocalTimeResult = actualCreateResult.getUpdatedAt().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualCreateResult.getCreatedAt().toLocalTime());
    }
}
