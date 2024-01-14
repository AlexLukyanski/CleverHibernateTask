package ru.clevertec.house.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.house.dto.PersonRequest;
import ru.clevertec.house.dto.PersonResponse;
import ru.clevertec.house.entity.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person fromRequestToPerson(PersonRequest personRequest);

    PersonRequest fromPersonToRequest(Person person);

    PersonResponse fromPersonToResponse(Person person);
}
