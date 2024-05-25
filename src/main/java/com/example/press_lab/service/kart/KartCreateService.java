package com.example.press_lab.service.kart;


import com.example.press_lab.entity.Kart;
import com.example.press_lab.exception.kart.KartConflictException;
import com.example.press_lab.mappers.KartMapper;
import com.example.press_lab.repository.KartRepository;
import com.example.press_lab.request.kart.KartCreateRequest;
import com.example.press_lab.response.kart.KartCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KartCreateService {
    private final KartRepository kartRepository;
    private final KartMapper kartMapper;

    public KartCreateResponse create(KartCreateRequest createRequest){
        if(kartRepository.findByContent(createRequest.getContent()).isPresent()){
            throw new KartConflictException();
        }
        Kart kart = kartMapper.mapRequestToEntity(createRequest);
        kartRepository.save(kart);
        return kartMapper.mapCreateToResponse(kart);
    }


}
