package ru.clevertec.house.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.house.dao.HouseDAO;
import ru.clevertec.house.dao.HouseHistoryDAO;
import ru.clevertec.house.dao.PersonDAO;
import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.dto.PersonResponse;
import ru.clevertec.house.mapper.HouseMapper;
import ru.clevertec.house.mapper.PersonMapper;
import ru.clevertec.house.service.HistoryService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HouseDAO houseDAO;
    private final PersonDAO personDAO;
    private final HouseHistoryDAO houseHistoryDAO;
    private final HouseMapper houseMapper;
    private final PersonMapper personMapper;

    @Override
    public List<PersonResponse> getAllPersonsLivedInHouse(UUID houseId) {

        List<UUID> uuidList = houseHistoryDAO.getAllPersonsUuidLivedInHouse(houseId);
        List<PersonResponse> personList = uuidList.stream()
                .map(personDAO::getById)
                .map(Optional::orElseThrow)
                .map(personMapper::fromPersonToResponse)
                .toList();
        return personList;
    }

    @Override
    public List<PersonResponse> getAllPersonsOwnedHouse(UUID houseId) {

        List<UUID> uuidList = houseHistoryDAO.getAllPersonsUuidOwnedHouse(houseId);
        List<PersonResponse> personList = uuidList.stream()
                .map(personDAO::getById)
                .map(Optional::orElseThrow)
                .map(personMapper::fromPersonToResponse)
                .toList();
        return personList;
    }

    @Override
    public List<HouseResponse> getAllHousesWherePersonLived(UUID personId) {

        List<UUID> uuidList = houseHistoryDAO.getAllHousesUuidWherePersonLived(personId);
        List<HouseResponse> houseList = uuidList.stream()
                .map(houseDAO::getById)
                .map(Optional::orElseThrow)
                .map(houseMapper::fromHouseToResponse)
                .toList();
        return houseList;
    }

    @Override
    public List<HouseResponse> getAllHousesWhichPersonOwned(UUID personId) {

        List<UUID> uuidList = houseHistoryDAO.getAllHousesUuidWhichPersonOwned(personId);
        List<HouseResponse> houseList = uuidList.stream()
                .map(houseDAO::getById)
                .map(Optional::orElseThrow)
                .map(houseMapper::fromHouseToResponse)
                .toList();
        return houseList;
    }
}
