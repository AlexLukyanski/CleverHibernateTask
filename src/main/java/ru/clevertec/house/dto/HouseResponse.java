package ru.clevertec.house.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record HouseResponse(

        UUID uuid,
        BigDecimal area,
        String country,
        String city,
        String street,
        int number,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss:SSS")
        LocalDateTime createDate
) {
}
