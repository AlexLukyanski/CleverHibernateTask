package ru.clevertec.house.service;

import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.dto.PersonRequest;
import ru.clevertec.house.dto.PersonResponse;

import java.util.List;
import java.util.UUID;

public interface PersonService {
    UUID save(PersonRequest personRequest);

    PersonResponse getById(UUID uuid);

    List<PersonResponse> getAll(int pageNumber, int pageSize);

    UUID update(PersonRequest personRequest);

    void delete(UUID uuid);

    List<HouseResponse> getOwnedHouses(UUID uuid);
}