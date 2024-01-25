package ru.clevertec.house.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.clevertec.house.util.constant.ResidentType;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class HouseHistory {

    private BigInteger id;
    private House house;
    private Person person;
    private LocalDate date;
    private ResidentType residentType;
}
