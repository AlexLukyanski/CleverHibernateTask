package ru.clevertec.house.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.house.dao.HouseDAO;
import ru.clevertec.house.dto.HouseRequest;
import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.dto.PersonResponse;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.exception.NotFoundException;
import ru.clevertec.house.mapper.HouseMapper;
import ru.clevertec.house.mapper.PersonMapper;
import ru.clevertec.house.service.HouseService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseDAO houseDAO;
    private final HouseMapper houseMapper;
    private final PersonMapper personMapper;

    @Override
    @Transactional
    public UUID save(HouseRequest houseRequest) {
        House house = houseMapper.fromRequestToHouse(houseRequest);
        UUID uuid = houseDAO.save(house);
        return uuid;
    }

    @Override
    @Transactional
    public HouseResponse getById(UUID uuid) {
        House house = houseDAO.getById(uuid)
                .orElseThrow(() -> NotFoundException.of(House.class, uuid));
        HouseResponse houseResponse = houseMapper.fromHouseToResponse(house);
        return houseResponse;
    }

    @Override
    @Transactional
    public List<HouseResponse> getAll(int pageNumber, int pageSize) {
        List<HouseResponse> houseResponses = houseDAO.getAll(pageNumber, pageSize).stream()
                .map(houseMapper::fromHouseToResponse)
                .toList();
        return houseResponses;
    }

    @Override
    @Transactional
    public UUID update(HouseRequest houseRequest) {
        House house = houseMapper.fromRequestToHouse(houseRequest);
        UUID uuid = houseDAO.update(house);
        return uuid;
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        houseDAO.delete(uuid);
    }

    @Override
    @Transactional
    public List<PersonResponse> getAllResidents(UUID uuid) {
        List<PersonResponse> residents = houseDAO.getAllResidents(uuid).stream()
                .map(personMapper::fromPersonToResponse)
                .toList();
        return residents;
    }
}
