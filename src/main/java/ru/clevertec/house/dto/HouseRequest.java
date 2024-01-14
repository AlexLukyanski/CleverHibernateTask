package ru.clevertec.house.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import ru.clevertec.house.entity.Person;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record HouseRequest(

        @NotEmpty(message = "Cannot be empty")
        @DecimalMin(value = "0", inclusive = false)
        BigDecimal area,

        @NotEmpty (message = "Cannot be empty")
        String country,

        @NotEmpty (message = "Cannot be empty")
        String city,

        @NotEmpty (message = "Cannot be empty")
        String street,

        @NotEmpty (message = "Cannot be empty")
        int number,

        @NotEmpty (message = "Cannot be empty")
        LocalDateTime createDate,

        Person owner,
        List<Person> residents,
        List<Person> owners
) {
}
