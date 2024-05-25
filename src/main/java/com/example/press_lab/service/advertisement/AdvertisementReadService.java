package com.example.press_lab.service.advertisement;

import com.example.press_lab.exception.advertisement.AdvertisementContentNotFoundException;
import com.example.press_lab.exception.advertisement.AdvertisementSourceUrlNotFoundException;
import com.example.press_lab.mappers.AdvertisementMapper;
import com.example.press_lab.repository.AdvertisementRepository;
import com.example.press_lab.request.advertisement.AdvertisementReadRequest;
import com.example.press_lab.response.advertisement.AdvertisementReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdvertisementReadService {
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementMapper advertisementMapper;

    public List<AdvertisementReadResponse> getAll(){
        return advertisementRepository.findAll()
                .stream()
                .map(advertisementMapper::mapReadToResponse)
                .toList();
    }

    public List<AdvertisementReadResponse> getContent(AdvertisementReadRequest readRequest){
        return advertisementRepository.findByContent(readRequest.getContent())
                .stream()
                .peek(advertisement -> {
                    if(advertisementRepository.findByContent(readRequest.getContent()).isEmpty()){
                        throw new AdvertisementContentNotFoundException();
                    }
                })
                .map(advertisementMapper::mapReadToResponse)
                .toList();
    }

    public List<AdvertisementReadResponse> getImage(AdvertisementReadRequest readRequest){
        return advertisementRepository.findBySourceUrl(readRequest.getSourceUrl())
                .stream()
                .peek(advertisement -> {
                    if(Objects.isNull(advertisementRepository.findBySourceUrl(readRequest.getSourceUrl()))){
                        throw new AdvertisementSourceUrlNotFoundException();
                    }
                })
                .map(advertisementMapper::mapReadToResponse)
                .toList();
    }

}
