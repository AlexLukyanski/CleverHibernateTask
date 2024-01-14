package ru.clevertec.house.service.impl;

import lombok.Builder;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Person;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
public class HouseTestData {

    @Builder.Default
    private int id = 1;

    @Builder.Default
    private UUID uuid = UUID.fromString("0c45cb09-6cbd-4088-89da-9d72b157d48c");

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

    public House getHouse() {
        House house = new House();
        house.setId(id);
        house.setUuid(uuid);
        house.setArea(area);
        house.setCountry(country);
        house.setCity(city);
        house.setStreet(street);
        house.setNumber(number);
        house.setCreateDate(createDate);
        house.setResidents(residents);
        house.setOwners(owners);
        return house;
    }
}
