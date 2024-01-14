package ru.clevertec.house.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.house.dto.HouseRequest;
import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.dto.PersonResponse;
import ru.clevertec.house.service.HouseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/house")
public class HouseController {

    private final HouseService houseService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HouseResponse findHouseById(@PathVariable("id") UUID uuid) {
        HouseResponse houseResponse = houseService.getById(uuid);
        return houseResponse;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HouseResponse> findAll(@RequestParam(defaultValue = "1", required = false) int pageNumber,
                                       @RequestParam(defaultValue = "15", required = false) int pageSize) {
        List<HouseResponse> houseResponses = houseService.getAll(pageNumber, pageSize);
        return houseResponses;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create(@RequestBody @Valid HouseRequest houseRequest) {
        UUID uuidResponse = houseService.save(houseRequest);
        return uuidResponse;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UUID update(@RequestBody @Valid HouseRequest houseRequest, @PathVariable("id") UUID uuid) {
        UUID uuidResponse = houseService.update(houseRequest);
        return uuidResponse;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void dekete(@PathVariable("id") UUID uuid) {
        houseService.delete(uuid);
    }

    @GetMapping("/residents/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponse> findAllResidents(@PathVariable("id") UUID uuid) {
        List<PersonResponse> residents = houseService.getAllResidents(uuid);
        return residents;
    }
}