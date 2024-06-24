package com.example.press_lab.service.advertisement;

import com.example.press_lab.entity.Advertisement;
import com.example.press_lab.exception.advertisement.AdvertisementContentNotFoundException;
import com.example.press_lab.exception.advertisement.AdvertisementSourceUrlNotFoundException;
import com.example.press_lab.mappers.AdvertisementMapper;
import com.example.press_lab.repository.AdvertisementRepository;
import com.example.press_lab.request.advertisement.AdvertisementReadRequest;
import com.example.press_lab.response.advertisement.AdvertisementReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertisementReadService {
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementMapper advertisementMapper;

    @Transactional
    public List<AdvertisementReadResponse> getAll(){
        return advertisementRepository.findAll()
                .stream()
                .map(advertisementMapper::mapReadToResponse)
                .toList();
    }

    @Transactional
    public List<AdvertisementReadResponse> getAdvertisementByContent(AdvertisementReadRequest readRequest){
        Optional<Advertisement> byContent = advertisementRepository.findByContent(readRequest.getContent());
        if(byContent.isEmpty()){
            throw new AdvertisementContentNotFoundException();
        }
        return advertisementRepository.findByContent(readRequest.getContent())
                .stream()
                .map(advertisementMapper::mapReadToResponse)
                .toList();
    }

    @Transactional
    public List<AdvertisementReadResponse> getAdvertisementByImageUrl(AdvertisementReadRequest readRequest){
        List<Advertisement> bySourceUrl = advertisementRepository.findBySourceUrl(readRequest.getSourceUrl());
        if(bySourceUrl.isEmpty()){
            throw new AdvertisementSourceUrlNotFoundException();
        }
        return advertisementRepository.findBySourceUrl(readRequest.getSourceUrl())
                .stream()
                .map(advertisementMapper::mapReadToResponse)
                .toList();
    }

}
