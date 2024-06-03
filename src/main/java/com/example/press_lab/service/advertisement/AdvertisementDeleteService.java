package com.example.press_lab.service.advertisement;

import com.example.press_lab.exception.advertisement.AdvertisementNotFoundException;
import com.example.press_lab.repository.AdvertisementRepository;
import com.example.press_lab.request.advertisement.AdvertisementDeleteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertisementDeleteService {
    private final AdvertisementRepository advertisementRepository;

    public void deleteAll(){
        advertisementRepository.deleteAll();
    }

    public void deleteById(AdvertisementDeleteRequest deleteRequest){
        advertisementRepository.findById(deleteRequest.getId()).orElseThrow(AdvertisementNotFoundException::new);
        advertisementRepository.deleteById(deleteRequest.getId());
    }
}
