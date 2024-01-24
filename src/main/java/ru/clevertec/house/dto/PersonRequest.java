package ru.clevertec.house.dto;

import jakarta.validation.constraints.NotEmpty;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.util.constant.Sex;

import java.util.List;

public record PersonRequest(

        @NotEmpty(message = "Cannot be empty")
        String name,

        @NotEmpty(message = "Cannot be empty")
        String surname,

        @NotEmpty(message = "Cannot be empty")
        Sex sex,

        @NotEmpty(message = "Cannot be empty")
        String passportSeries,

        @NotEmpty(message = "Cannot be empty")
        String passportNumber,

        House home,
        List<House> ownedHouses
) {
}
