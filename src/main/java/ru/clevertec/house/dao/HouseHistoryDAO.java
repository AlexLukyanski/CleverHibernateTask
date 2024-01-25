package ru.clevertec.house.dao;


import java.util.List;
import java.util.UUID;

public interface HouseHistoryDAO {

    List<UUID> getAllPersonsUuidLivedInHouse(UUID houseId);
    List<UUID> getAllPersonsUuidOwnedHouse(UUID houseId);
    List<UUID> getAllHousesUuidWherePersonLived(UUID personId);
    List<UUID> getAllHousesUuidWhichPersonOwned(UUID personId);
}
