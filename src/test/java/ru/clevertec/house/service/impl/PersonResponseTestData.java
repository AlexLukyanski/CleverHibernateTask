package ru.clevertec.house.service.impl;

import lombok.Builder;
import ru.clevertec.house.dto.PersonResponse;
import ru.clevertec.house.util.constant.Sex;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public class PersonResponseTestData {

    @Builder.Default
    UUID uuid = UUID.fromString("5c7e43eb-75a8-44d0-9b1e-89c5dabbacfd");

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
    LocalDateTime createDate = LocalDateTime.MIN;

    @Builder.Default
    LocalDateTime updateDate = LocalDateTime.MIN;

    public PersonResponse getPersonResponse() {
        return new PersonResponse(uuid, name, surname, sex, passportSeries, passportNumber, createDate, updateDate);
    }
}
