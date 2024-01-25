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
import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.dto.PersonRequest;
import ru.clevertec.house.dto.PersonResponse;
import ru.clevertec.house.service.PersonService;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonResponse findPersonByID(@PathVariable UUID id) {
        PersonResponse personResponse = personService.getById(id);
        return personResponse;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponse> findAll(@RequestParam(defaultValue = "1", required = false) int pageNumber,
                                        @RequestParam(defaultValue = "15", required = false) int pageSize) {
        List<PersonResponse> personResponses = personService.getAll(pageNumber, pageSize);
        return personResponses;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create(@RequestBody @Valid PersonRequest personRequest) {
        UUID uuid = personService.save(personRequest);
        return uuid;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UUID update(@RequestBody @Valid PersonRequest personRequest, @PathVariable UUID id) {
        UUID uuidResponse = personService.update(personRequest);
        return uuidResponse;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID uuid) {
        personService.delete(uuid);
    }

    @GetMapping("/owned/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<HouseResponse> getOwnedHouses(@PathVariable UUID id) {

        List<HouseResponse> ownedHousesResponse = personService.getOwnedHouses(id);
        return ownedHousesResponse;
    }
}
