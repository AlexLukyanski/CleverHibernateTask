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

    @Builder.Default
    Person owner = new Person();

    @Builder.Default
    List<Person> residents = new ArrayList<>();

    @Builder.Default
    List<Person> owners = new ArrayList<>();

    public HouseRequest getHouseRequest() {
        return new HouseRequest(area, country, city, street, number, createDate, owner, residents, owners);
    }
}
