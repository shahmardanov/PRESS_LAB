package com.example.press_lab.service.util;

import com.example.press_lab.entity.Advertisement;

import java.time.LocalDate;

public class AdvertisementUtil {

    private  AdvertisementUtil(){

    }

    public static Advertisement advertisement(){
        Advertisement advertisement = new Advertisement();
        advertisement.setContent("Not all who wander are lost");
        advertisement.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        advertisement.setId(1L);
        advertisement.setImageUrl("https://example.org/example");
        advertisement.setSourceUrl("https://example.org/example");
        advertisement.setTitle("Dr");
        advertisement.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        advertisement.setViewCount(3L);
        return advertisement;
    }
}
