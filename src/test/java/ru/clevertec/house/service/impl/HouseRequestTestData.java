package ru.clevertec.house.service.impl;

import lombok.Builder;
import ru.clevertec.house.dto.HouseRequest;
import ru.clevertec.house.entity.Person;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public class HouseRequestTestData {

    @Builder.Default
    private BigDecimal area = BigDecimal.valueOf(35);

    @Builder.Default
    private String country = "Belarus";

    @Builder.Default
    private String city = "Mogilev";

    @Builder.Default
    private String street = "TheOneAndOnly";

    @Builder.Default
    private int number = 3;

    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.MIN;

    @Builder.Default
    private List<Person> residents = new ArrayList<>();

    @Builder.Default
    private List<Person> owners = new ArrayList<>();

    public HouseRequest getHouseRequest() {
        return new HouseRequest(area, country, city, street, number, createDate, residents, owners);
    }
}
