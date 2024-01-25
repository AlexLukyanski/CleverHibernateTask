package ru.clevertec.house.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.dto.PersonResponse;
import ru.clevertec.house.service.HistoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/history")
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/persons-lived-house/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponse> getAllPersonsLivedInHouse(@PathVariable UUID id) {
        List<PersonResponse> personResponses = historyService.getAllPersonsLivedInHouse(id);
        return personResponses;
    }

    @GetMapping("/persons-owned-house/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponse> getAllPersonsOwnedHouse(@PathVariable UUID id) {
        List<PersonResponse> personResponses = historyService.getAllPersonsOwnedHouse(id);
        return personResponses;
    }

    @GetMapping("/person-lived-houses/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<HouseResponse> getAllHousesWherePersonLived(@PathVariable UUID id) {
        List<HouseResponse> houseResponses = historyService.getAllHousesWherePersonLived(id);
        return houseResponses;
    }

    @GetMapping("/person-owned-houses/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<HouseResponse> getAllHousesWhichPersonOwned(@PathVariable UUID id) {
        List<HouseResponse> houseResponses = historyService.getAllHousesWhichPersonOwned(id);
        return houseResponses;
    }
}
