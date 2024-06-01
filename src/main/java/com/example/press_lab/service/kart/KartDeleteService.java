package com.example.press_lab.service.kart;

import com.example.press_lab.exception.kart.KartNotFoundException;
import com.example.press_lab.repository.KartRepository;
import com.example.press_lab.request.kart.KartDeleteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class KartDeleteService {
    private final KartRepository kartRepository;

    public void deleteAll(){
        kartRepository.deleteAll();
    }

    public void deleteById(KartDeleteRequest deleteRequest){
        kartRepository.findById(deleteRequest.getId()).orElseThrow(KartNotFoundException::new);
        kartRepository.deleteById(deleteRequest.getId());
    }

}
