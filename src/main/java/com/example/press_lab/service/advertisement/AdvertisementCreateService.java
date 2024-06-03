package com.example.press_lab.service.advertisement;


import com.example.press_lab.entity.Advertisement;
import com.example.press_lab.exception.advertisement.AdvertisementConflictException;
import com.example.press_lab.mappers.AdvertisementMapper;
import com.example.press_lab.repository.AdvertisementRepository;
import com.example.press_lab.request.advertisement.AdvertisementCreateRequest;
import com.example.press_lab.response.advertisement.AdvertisementCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertisementCreateService {
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementMapper advertisementMapper;

    public AdvertisementCreateResponse create (AdvertisementCreateRequest createRequest){
        if(advertisementRepository.findByContent(createRequest.getContent()).isPresent()){
            throw new AdvertisementConflictException();
        }
        Advertisement advertisement = advertisementMapper.mapRequestToEntity(createRequest);
        advertisementRepository.save(advertisement);
        return advertisementMapper.mapCreateToResponse(advertisement);
    }

}
