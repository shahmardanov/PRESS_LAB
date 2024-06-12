package com.example.press_lab.service.util;

import com.example.press_lab.entity.Kart;

import java.time.LocalDate;

public class KartUtil {

    private KartUtil(){

    }

    public static Kart kart() {
        Kart kart = new Kart();
        kart.setContent("Not all who wander are lost");
        kart.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        kart.setDescription("The characteristics of someone or something");
        kart.setFkNewsId(1L);
        kart.setId(1L);
        kart.setImageUrl("https://example.org/example");
        kart.setTitle("Dr");
        return kart;
    }
}
