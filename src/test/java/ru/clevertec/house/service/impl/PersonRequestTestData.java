package ru.clevertec.house.service.impl;

import lombok.Builder;
import ru.clevertec.house.dto.PersonRequest;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.util.constant.Sex;

import java.util.ArrayList;
import java.util.List;

@Builder
public class PersonRequestTestData {

    @Builder.Default
    String name = "Alex";

    @Builder.Default
    String surname = "The";

    @Builder.Default
    Sex sex = Sex.MALE;

    @Builder.Default
    String passportSeries = "FF55555";

    @Builder.Default
    String passportNumber = "JKN2811DSDD5561";

    @Builder.Default
    House home = HouseTestData.builder().build().getHouse();

    @Builder.Default
    List<House> ownedHouses = new ArrayList<>();

    public PersonRequest getPersonRequest() {
        return new PersonRequest(name, surname, sex, passportSeries, passportNumber, home, ownedHouses);
    }
}
