package com.example.press_lab.service.advertisement;

import com.example.press_lab.entity.Advertisement;
import com.example.press_lab.exception.advertisement.AdvertisementNotFoundException;
import com.example.press_lab.mappers.AdvertisementMapper;
import com.example.press_lab.repository.AdvertisementRepository;
import com.example.press_lab.request.advertisement.AdvertisementUpdateRequest;
import com.example.press_lab.response.advertisement.AdvertisementUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdvertisementUpdateService {
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementMapper advertisementMapper;

    public AdvertisementUpdateResponse update(AdvertisementUpdateRequest updateRequest){
        Advertisement advertisement = advertisementRepository.findById(updateRequest.getId()).orElseThrow(AdvertisementNotFoundException::new);

        if(Objects.nonNull(updateRequest.getTitle())){
            advertisement.setTitle(updateRequest.getTitle());
        }
        if(Objects.nonNull(updateRequest.getContent())){
            advertisement.setContent(updateRequest.getContent());
        }
        if(Objects.nonNull(updateRequest.getImageUrl())){
            advertisement.setImageUrl(updateRequest.getImageUrl());
        }
        if(Objects.nonNull(updateRequest.getViewCount())){
            advertisement.setViewCount(updateRequest.getViewCount());
        }
        if(Objects.nonNull(updateRequest.getCreatedAt())){
            advertisement.setCreatedAt(updateRequest.getCreatedAt());
        }
        if(Objects.nonNull(updateRequest.getUpdatedAt())){
            advertisement.setUpdatedAt(updateRequest.getUpdatedAt());
        }
        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);
        return advertisementMapper.mapUpdateToResponse(savedAdvertisement);
    }
}
