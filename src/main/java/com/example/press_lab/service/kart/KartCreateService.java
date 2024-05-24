package com.example.press_lab.service.kart;


import com.example.press_lab.entity.Kart;
import com.example.press_lab.exception.advertisement.AdvertisementContentNotFoundException;
import com.example.press_lab.exception.kart.KartContentNotFoundException;
import com.example.press_lab.mappers.KartMapper;
import com.example.press_lab.repository.KartRepository;
import com.example.press_lab.request.kart.KartCreateRequest;
import com.example.press_lab.response.kart.KartCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class KartCreateService {
    private final KartRepository kartRepository;
    private final KartMapper kartMapper;

    public KartCreateResponse create(KartCreateRequest createRequest){
        if(Objects.nonNull(kartRepository.findByContent(createRequest.getContent()))){
            throw new KartContentNotFoundException();
        }
        Kart kart = kartMapper.mapRequestToEntity(createRequest);
        Kart savedKart = kartRepository.save(kart);
        return kartMapper.mapCreateToResponse(savedKart);
    }


}
