package ru.clevertec.house.service;

import ru.clevertec.house.dto.HouseRequest;
import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.dto.PersonResponse;

import java.util.List;
import java.util.UUID;

public interface HouseService {

    UUID save(HouseRequest houseRequest);

    HouseResponse getById(UUID uuid);

    List<HouseResponse> getAll(int pageNumber, int pageSize);

    UUID update(HouseRequest houseRequest);

    void delete(UUID uuid);

    List<PersonResponse> getAllResidents(UUID uuid);
}
