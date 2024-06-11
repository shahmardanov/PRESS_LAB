package com.example.press_lab.service.kart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.example.press_lab.entity.Kart;
import com.example.press_lab.exception.news.NewsContentNotFoundException;
import com.example.press_lab.mappers.KartMapper;
import com.example.press_lab.repository.KartRepository;
import com.example.press_lab.request.kart.KartReadRequest;
import com.example.press_lab.response.kart.KartReadResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

@ContextConfiguration(classes = {KartReadService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class KartReadServiceTest {
  @MockBean
  private KartMapper kartMapper;

  @Autowired
  private KartReadService kartReadService;

  @MockBean
  private KartRepository kartRepository;

  /**
   * Method under test: {@link KartReadService#getAll()}
   */
  @Test
  void testGetAll() {
    // Arrange
    when(kartRepository.findAll()).thenReturn(new ArrayList<>());

    // Act
    List<KartReadResponse> actualAll = kartReadService.getAll();

    // Assert
    verify(kartRepository).findAll();
    assertTrue(actualAll.isEmpty());
  }

  /**
   * Method under test: {@link KartReadService#getKartByContent(KartReadRequest)}
   */
  @Test
  void testGetKartByContent() {
    // Arrange
    Kart kart = KartUtil.kart();
    Optional<Kart> ofResult = Optional.of(kart);
    when(kartRepository.findByContent(Mockito.<String>any())).thenReturn(ofResult);
    KartReadResponse.KartReadResponseBuilder contentResult = KartReadResponse.builder()
        .content("Not all who wander are lost");
    KartReadResponse.KartReadResponseBuilder titleResult = contentResult
        .createdAt(LocalDate.of(1970, 1, 1).atStartOfDay())
        .description("The characteristics of someone or something")
        .fkNewsId(1L)
        .id(1L)
        .imageUrl("https://example.org/example")
        .title("Dr");
    KartReadResponse buildResult = titleResult.updatedAt(LocalDate.of(1970, 1, 1).atStartOfDay()).build();
    when(kartMapper.mapReadToResponse(Mockito.<Kart>any())).thenReturn(buildResult);

    // Act
    List<KartReadResponse> actualKartByContent = kartReadService.getKartByContent(new KartReadRequest());

    // Assert
    verify(kartMapper).mapReadToResponse(isA(Kart.class));
    verify(kartRepository).findByContent(isNull());
    assertEquals(1, actualKartByContent.size());
  }

  /**
   * Method under test: {@link KartReadService#getKartByContent(KartReadRequest)}
   */
  @Test
  void testGetKartByContent2() {
    // Arrange
    Optional<Kart> emptyResult = Optional.empty();
    when(kartRepository.findByContent(Mockito.<String>any())).thenReturn(emptyResult);

    // Act and Assert
    assertThrows(NewsContentNotFoundException.class, () -> kartReadService.getKartByContent(new KartReadRequest()));
    verify(kartRepository).findByContent(isNull());
  }

  /**
   * Method under test: {@link KartReadService#getKartByFkNewsId(KartReadRequest)}
   */
  @Test
  void testGetKartByFkNewsId() {
    // Arrange
    when(kartRepository.findByFkNewsId(Mockito.<Long>any())).thenReturn(new ArrayList<>());

    // Act and Assert
    assertThrows(NewsContentNotFoundException.class, () -> kartReadService.getKartByFkNewsId(new KartReadRequest()));
    verify(kartRepository).findByFkNewsId(isNull());
  }
}
