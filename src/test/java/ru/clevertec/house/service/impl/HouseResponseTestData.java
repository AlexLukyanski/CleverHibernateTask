package ru.clevertec.house.service.impl;


import lombok.Builder;
import ru.clevertec.house.dto.HouseResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public class HouseResponseTestData {

    @Builder.Default
    UUID uuid = UUID.fromString("0c45cb09-6cbd-4088-89da-9d72b157d48c");

    @Builder.Default
    BigDecimal area = BigDecimal.valueOf(35);

    @Builder.Default
    String country = "Belarus";

    @Builder.Default
    String city = "Mogilev";

    @Builder.Default
    String street = "TheOneAndOnly";

    @Builder.Default
    int number = 3;

    @Builder.Default
    LocalDateTime createDate = LocalDateTime.MIN;

    public HouseResponse getHouseResponse() {
        return new HouseResponse(uuid, area, country, city, street, number, createDate);
    }
}
