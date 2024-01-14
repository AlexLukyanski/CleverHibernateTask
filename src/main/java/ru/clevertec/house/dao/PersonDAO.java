package ru.clevertec.house.dao;

import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDAO {

    UUID save(Person person);

    Optional<Person> getById(UUID uuid);

    List<Person> getAll(int pageNumber, int pageSize);

    UUID update(Person person);

    void delete(UUID uuid);

    List<House> getOwnedHouses(UUID uuid);
}
