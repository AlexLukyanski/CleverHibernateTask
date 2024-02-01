package ru.clevertec.house.service.impl;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.house.dao.PersonDAO;
import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.dto.PersonRequest;
import ru.clevertec.house.dto.PersonResponse;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.exception.NotFoundException;
import ru.clevertec.house.mapper.HouseMapper;
import ru.clevertec.house.mapper.PersonMapper;
import ru.clevertec.house.service.Cache;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonDAO personDAO;

    @Mock
    private PersonMapper personMapper;

    @Mock
    private HouseMapper houseMapper;

    @Mock
    private Cache cache;

    @InjectMocks
    private PersonServiceImpl personService;


    @Nested
    class SaveMethodTest {

        @Test
        void should_ReturnUuid_when_PersonSaved() {

            //given
            PersonRequest personRequest = PersonRequestTestData.builder().build().getPersonRequest();
            Person person = PersonTestData.builder().build().getPerson();
            UUID expected = person.getUuid();

            when(personMapper.fromRequestToPerson(personRequest)).thenReturn(person);
            when(personDAO.save(person)).thenReturn(person.getUuid());

            //when
            UUID actual = personService.save(personRequest);

            //then
            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetByIDMethodTest {

        @Test
        void should_ReturnPersonResponse_when_PersonExist() {

            //given
            Person person = PersonTestData.builder().build().getPerson();
            PersonResponse expected = PersonResponseTestData.builder().build().getPersonResponse();

            when(personDAO.getById(person.getUuid())).thenReturn(Optional.of(person));
            when(personMapper.fromPersonToResponse(person)).thenReturn(expected);

            //when
            PersonResponse actual = personService.getById(person.getUuid());

            //then
            assertEquals(expected, actual);
        }

        @Test
        void should_ThrowNotFoundException_when_PersonDoesNotExist() {

            //given
            Person person = PersonTestData.builder().build().getPerson();

            when(personDAO.getById(person.getUuid())).thenReturn(Optional.empty());

            //when
            Executable executable = () -> personService.getById(person.getUuid());

            //then
            assertThrows(NotFoundException.class, executable);
        }
    }

    @Nested
    class GetAllMethodTest {

        @Test
        void should_ReturnListOfPersonResponses_when_MethodInvoke() {

            //given
            PersonResponse personResponse = PersonResponseTestData.builder().build().getPersonResponse();
            Person person = PersonTestData.builder().build().getPerson();
            List<Person> persons = List.of(person);
            List<PersonResponse> expected = List.of(personResponse);
            int pageNumber = 1;
            int pageSize = 15;

            when(personDAO.getAll(pageNumber, pageSize)).thenReturn(persons);
            when(personMapper.fromPersonToResponse(person)).thenReturn(personResponse);

            //when
            List<PersonResponse> actual = personService.getAll(pageNumber, pageSize);

            //then
            assertEquals(expected, actual);
        }
    }

    @Nested
    class UpdateMethodTest {

        @Test
        void should_ReturnUuid_when_PersonUpdated() {

            //given
            PersonRequest personRequest = PersonRequestTestData.builder().build().getPersonRequest();
            Person person = PersonTestData.builder().build().getPerson();
            UUID expected = person.getUuid();

            when(personMapper.fromRequestToPerson(personRequest)).thenReturn(person);
            when(personDAO.update(person)).thenReturn(person.getUuid());

            //when
            UUID actual = personService.update(personRequest);

            //then
            assertEquals(expected, actual);
        }
    }

    @Nested
    class DeleteMethodTest {

        @Test
        void should_ReturnListOfHouseResponses_when_MethodInvoke() {

            //given
            UUID id = UUID.fromString("66caf9ea-6074-4b1c-a1d1-c0dca7095f9f");

            doNothing().when(personDAO).delete(id);

            //when
            Executable executable = () -> personService.delete(id);

            //then
            assertDoesNotThrow(executable);
        }
    }

    @Nested
    class GetOwnedHousesMethodTest {

        @Test
        void should_NotThrowException_when_PersonDoesExist() {

            //given
            HouseResponse houseResponse = HouseResponseTestData.builder().build().getHouseResponse();
            House house = HouseTestData.builder().build().getHouse();
            List<House> houses = List.of(house);
            List<HouseResponse> expected = List.of(houseResponse);
            UUID uuid = houseResponse.uuid();

            when(personDAO.getOwnedHouses(uuid)).thenReturn(houses);
            when(houseMapper.fromHouseToResponse(house)).thenReturn(houseResponse);

            //when
            List<HouseResponse> actual = personService.getOwnedHouses(uuid);

            //then
            assertEquals(expected, actual);
        }
    }
}