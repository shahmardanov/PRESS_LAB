package com.example.press_lab.service.kart;

import com.example.press_lab.entity.Kart;
import com.example.press_lab.exception.kart.KartNotFoundException;
import com.example.press_lab.mappers.KartMapper;
import com.example.press_lab.repository.KartRepository;
import com.example.press_lab.request.kart.KartUpdateRequest;
import com.example.press_lab.response.kart.KartUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KartUpdateService {
    private final KartRepository kartRepository;
    private final KartMapper kartMapper;

    public KartUpdateResponse update(KartUpdateRequest updateRequest) {
        Kart kart = kartRepository.findById(updateRequest.getId()).orElseThrow(KartNotFoundException::new);
        Kart save = kartMapper.updateKartToKartUpdateResponse(updateRequest, kart);
        kartRepository.save(save);
        return kartMapper.mapUpdateToResponse(kartRepository.save(save));
    }
}
