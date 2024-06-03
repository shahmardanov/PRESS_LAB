package com.example.press_lab.service.advertisement;

import com.example.press_lab.entity.Advertisement;
import com.example.press_lab.exception.advertisement.AdvertisementNotFoundException;
import com.example.press_lab.mappers.AdvertisementMapper;
import com.example.press_lab.repository.AdvertisementRepository;
import com.example.press_lab.request.advertisement.AdvertisementUpdateRequest;
import com.example.press_lab.response.advertisement.AdvertisementUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertisementUpdateService {
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementMapper advertisementMapper;

    public AdvertisementUpdateResponse update(AdvertisementUpdateRequest updateRequest){
        Advertisement advertisement = advertisementRepository.findById(updateRequest.getId()).orElseThrow(AdvertisementNotFoundException::new);
        Advertisement updated = advertisementMapper.updateAdvertisement(updateRequest, advertisement);
        Advertisement save = advertisementRepository.save(updated);
        return advertisementMapper.mapUpdateToResponse(save);
    }

}
