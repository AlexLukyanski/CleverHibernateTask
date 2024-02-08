package ru.clevertec.house.service;

import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.dto.PersonResponse;


import java.util.List;
import java.util.UUID;

public interface HistoryService {

    List<PersonResponse> getAllPersonsLivedInHouse(UUID houseId);

    List<PersonResponse> getAllPersonsOwnedHouse(UUID houseId);

    List<HouseResponse> getAllHousesWherePersonLived(UUID personId);

    List<HouseResponse> getAllHousesWhichPersonOwned(UUID personId);
}
