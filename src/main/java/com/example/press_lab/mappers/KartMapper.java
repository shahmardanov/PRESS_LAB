package com.example.press_lab.mappers;

import com.example.press_lab.entity.Kart;
import com.example.press_lab.request.kart.KartCreateRequest;
import com.example.press_lab.response.kart.KartCreateResponse;
import com.example.press_lab.response.kart.KartReadResponse;
import com.example.press_lab.response.kart.KartUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface KartMapper {

    Kart mapRequestToEntity(KartCreateRequest kartCreateRequest);

    KartCreateResponse mapCreateToResponse(Kart kart);
    KartReadResponse mapReadToResponse(Kart kart);
    KartUpdateResponse mapUpdateToResponse(Kart kart);

}
