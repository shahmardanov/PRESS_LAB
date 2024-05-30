package com.example.press_lab.service.kart;

import com.example.press_lab.entity.Kart;
import com.example.press_lab.exception.news.NewsContentNotFoundException;
import com.example.press_lab.mappers.KartMapper;
import com.example.press_lab.repository.KartRepository;
import com.example.press_lab.request.kart.KartReadRequest;
import com.example.press_lab.response.kart.KartReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    public List<KartReadResponse> getKartByContent(KartReadRequest readRequest){
        Optional<Kart> byContent = kartRepository.findByContent(readRequest.getContent());
        if(byContent.isEmpty()){
            throw new NewsContentNotFoundException();
        }
        return byContent
                .stream()
                .map(kartMapper::mapReadToResponse)
                .toList();
    }

    public List<KartReadResponse> getKartByFkNewsId(KartReadRequest readRequest){
        List<Kart> byFkNewsId = kartRepository.findByFkNewsId(readRequest.getFkNewsId());
        if(byFkNewsId.isEmpty()){
            throw new NewsContentNotFoundException();
        }
        return byFkNewsId
                .stream()
                .map(kartMapper::mapReadToResponse)
                .toList();
    }

}
