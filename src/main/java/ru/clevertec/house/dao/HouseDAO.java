package ru.clevertec.house.dao;

import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HouseDAO {

    UUID save(House house);

    Optional<House> getById(UUID uuid);

    List<House> getAll(int pageNumber, int pageSize);

    UUID update(House house);

    void delete(UUID uuid);

    List<Person> getAllResidents(UUID uuid);
}
