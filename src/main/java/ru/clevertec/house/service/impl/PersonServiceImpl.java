package ru.clevertec.house.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.house.dao.PersonDAO;
import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.dto.PersonRequest;
import ru.clevertec.house.dto.PersonResponse;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.exception.NotFoundException;
import ru.clevertec.house.mapper.HouseMapper;
import ru.clevertec.house.mapper.PersonMapper;
import ru.clevertec.house.service.PersonService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonDAO personDAO;
    private final PersonMapper personMapper;
    private final HouseMapper houseMapper;

    @Override
    @Transactional
    public UUID save(PersonRequest personRequest) {

        Person person = personMapper.fromRequestToPerson(personRequest);
        UUID uuid = personDAO.save(person);
        return uuid;
    }

    @Override
    @Transactional
    public PersonResponse getById(UUID uuid) {

        Person person = personDAO.getById(uuid)
                .orElseThrow(() -> NotFoundException.of(Person.class, uuid));
        PersonResponse personResponse = personMapper.fromPersonToResponse(person);
        return personResponse;
    }

    @Override
    @Transactional
    public List<PersonResponse> getAll(int pageNumber, int pageSize) {

        List<PersonResponse> personList = personDAO.getAll(pageNumber, pageSize).stream()
                .map(personMapper::fromPersonToResponse)
                .toList();

        return personList;
    }

    @Override
    @Transactional
    public UUID update(PersonRequest personRequest) {
        Person person = personMapper.fromRequestToPerson(personRequest);
        UUID uuid = personDAO.update(person);
        return uuid;
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        personDAO.delete(uuid);
    }

    @Override
    @Transactional
    public List<HouseResponse> getOwnedHouses(UUID uuid) {
        List<HouseResponse> ownedHouses = personDAO.getOwnedHouses(uuid).stream()
                .map(houseMapper::fromHouseToResponse)
                .toList();
        return ownedHouses;
    }
}