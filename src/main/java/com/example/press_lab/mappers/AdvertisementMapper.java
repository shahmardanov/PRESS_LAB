package com.example.press_lab.mappers;

import com.example.press_lab.entity.Advertisement;
import com.example.press_lab.request.advertisement.AdvertisementCreateRequest;
import com.example.press_lab.response.advertisement.AdvertisementCreateResponse;
import com.example.press_lab.response.advertisement.AdvertisementReadResponse;
import com.example.press_lab.response.advertisement.AdvertisementUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AdvertisementMapper {

    Advertisement mapRequestToEntity(AdvertisementCreateRequest advertisementCreateRequest);

    AdvertisementCreateResponse mapCreateToResponse(Advertisement advertisement);
    AdvertisementReadResponse mapReadToResponse(Advertisement advertisement);
    AdvertisementUpdateResponse mapUpdateToResponse(Advertisement advertisement);

}
