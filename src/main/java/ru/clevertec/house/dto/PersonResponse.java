package ru.clevertec.house.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.clevertec.house.util.constant.Sex;

import java.time.LocalDateTime;;
import java.util.UUID;

public record PersonResponse(
        UUID uuid,
        String name,
        String surname,
        Sex sex,
        String passportSeries,
        String passportNumber,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss:SSS")
        LocalDateTime createDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss:SSS")
        LocalDateTime updateDate
) {
}
