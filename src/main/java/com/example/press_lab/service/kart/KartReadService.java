package com.example.press_lab.service.kart;

import com.example.press_lab.exception.kart.KartFkNewsIdNotFoundException;
import com.example.press_lab.mappers.KartMapper;
import com.example.press_lab.repository.KartRepository;
import com.example.press_lab.request.kart.KartReadRequest;
import com.example.press_lab.response.kart.KartReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class KartReadService {
    private final KartRepository kartRepository;
    private final KartMapper kartMapper;

    public List<KartReadResponse> getAll(){
        return kartRepository.findAll()
                .stream()
                .map(kartMapper::mapReadToResponse)
                .toList();
    }

    public List<KartReadResponse> getFkNewsId(KartReadRequest readRequest){
        return kartRepository.findByFkNewsId(readRequest.getFkNewsId())
                .stream()
                .peek(kart -> {
                    if(kartRepository.findByFkNewsId(readRequest.getFkNewsId()).isEmpty()){
                        throw new KartFkNewsIdNotFoundException();
                    }
                })
                .map(kartMapper::mapReadToResponse)
                .toList();
    }

}
