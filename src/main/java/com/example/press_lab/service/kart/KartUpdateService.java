package com.example.press_lab.service.kart;

import com.example.press_lab.entity.Kart;
import com.example.press_lab.exception.kart.KartNotFoundException;
import com.example.press_lab.mappers.KartMapper;
import com.example.press_lab.repository.KartRepository;
import com.example.press_lab.request.kart.KartUpdateRequest;
import com.example.press_lab.response.kart.KartUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class KartUpdateService {
    private final KartRepository kartRepository;
    private final KartMapper kartMapper;

    public KartUpdateResponse update(KartUpdateRequest updateRequest){
        Kart kart = kartRepository.findById(updateRequest.getId()).orElseThrow(KartNotFoundException::new);

        if(Objects.nonNull(updateRequest.getTitle())){
            kart.setTitle(updateRequest.getTitle());
        }
        if(Objects.nonNull(updateRequest.getContent())){
            kart.setContent(updateRequest.getContent());
        }
        if(Objects.nonNull(updateRequest.getImageUrl())){
            kart.setImageUrl(updateRequest.getImageUrl());
        }
        if(Objects.nonNull(updateRequest.getFkNewsId())){
            kart.setFkNewsId(updateRequest.getFkNewsId());
        }
        if(Objects.nonNull(updateRequest.getDescription())){
            kart.setDescription(updateRequest.getDescription());
        }
        if(Objects.nonNull(updateRequest.getCreatedAt())){
            kart.setCreatedAt(updateRequest.getCreatedAt());
        }
        Kart savedKart = kartRepository.save(kart);
        return kartMapper.mapUpdateToResponse(savedKart);
    }
}
