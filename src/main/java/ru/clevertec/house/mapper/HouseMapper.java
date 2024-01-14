package ru.clevertec.house.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.house.dto.HouseRequest;
import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.entity.House;

@Mapper(componentModel = "spring")
public interface HouseMapper {

    House fromRequestToHouse(HouseRequest houseRequest);

    HouseRequest fromHouseToRequest(House house);

    HouseResponse fromHouseToResponse(House house);
}