package ru.clevertec.house.service.impl;

import lombok.Builder;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.util.constant.Sex;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
public class PersonTestData {

    @Builder.Default
    private int id = 2;

    @Builder.Default
    private UUID uuid = UUID.fromString("5c7e43eb-75a8-44d0-9b1e-89c5dabbacfd");

    @Builder.Default
    private String name = "Alex";

    @Builder.Default
    private String surname = "The";

    @Builder.Default
    private Sex sex = Sex.MALE;

    @Builder.Default
    private String passportSeries = "FF55555";

    @Builder.Default
    private String passportNumber = "JKN2811DSDD5561";

    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.MIN;

    @Builder.Default
    private LocalDateTime updateDate = LocalDateTime.MIN;

    @Builder.Default
    private House home = HouseTestData.builder().build().getHouse();

    @Builder.Default
    private List<House> ownedHouses = new ArrayList<>();

    public Person getPerson() {
        Person person = new Person();
        person.setId(id);
        person.setUuid(uuid);
        person.setName(name);
        person.setSurname(surname);
        person.setSex(sex);
        person.setPassportSeries(passportSeries);
        person.setPassportNumber(passportNumber);
        person.setCreateDate(createDate);
        person.setUpdateDate(updateDate);
        person.setHome(home);
        person.setOwnedHouses(ownedHouses);
        return person;
    }
}
