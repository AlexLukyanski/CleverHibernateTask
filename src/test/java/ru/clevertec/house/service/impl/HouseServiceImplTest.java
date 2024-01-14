package ru.clevertec.house.service.impl;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.house.dao.HouseDAO;
import ru.clevertec.house.dto.HouseRequest;
import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.dto.PersonResponse;
import ru.clevertec.house.entity.House;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.exception.NotFoundException;
import ru.clevertec.house.mapper.HouseMapper;
import ru.clevertec.house.mapper.PersonMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HouseServiceImplTest {

    @Mock
    private HouseMapper houseMapper;

    @Mock
    private PersonMapper personMapper;

    @Mock
    private HouseDAO houseDAO;

    @InjectMocks
    private HouseServiceImpl houseService;

    @Nested
    class SaveMethodTest {

        @Test
        void should_ReturnUuid_when_HouseSaved() {

            //given
            HouseRequest houseRequest = HouseRequestTestData.builder().build().getHouseRequest();
            House house = HouseTestData.builder().build().getHouse();
            UUID expected = house.getUuid();

            when(houseMapper.fromRequestToHouse(houseRequest)).thenReturn(house);
            when(houseDAO.save(house)).thenReturn(house.getUuid());

            //when
            UUID actual = houseService.save(houseRequest);

            //then
            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetByIDMethodTest {

        @Test
        void should_ReturnHouseResponse_when_HouseExist() {

            //given
            House house = HouseTestData.builder().build().getHouse();
            HouseResponse expected = HouseResponseTestData.builder().build().getHouseResponse();

            when(houseMapper.fromHouseToResponse(house)).thenReturn(expected);
            when(houseDAO.getById(house.getUuid())).thenReturn(Optional.of(house));

            //when
            HouseResponse actual = houseService.getById(house.getUuid());

            //then
            assertEquals(expected, actual);
        }

        @Test
        void should_ThrowNotFoundException_when_HouseDoesNotExist() {

            //given
            House house = HouseTestData.builder().build().getHouse();

            when(houseDAO.getById(house.getUuid())).thenReturn(Optional.empty());

            //when
            Executable executable = () -> houseService.getById(house.getUuid());

            //then
            assertThrows(NotFoundException.class, executable);
        }
    }

    @Nested
    class GetAllMethodTest {

        @Test
        void should_ReturnListOfHouseResponses_when_MethodInvoke() {

            //given
            HouseResponse houseResponse = HouseResponseTestData.builder().build().getHouseResponse();
            House house = HouseTestData.builder().build().getHouse();
            List<House> houses = List.of(house);
            List<HouseResponse> expected = List.of(houseResponse);
            int pageNumber = 1;
            int pageSize = 15;

            when(houseDAO.getAll(pageNumber, pageSize)).thenReturn(houses);
            when(houseMapper.fromHouseToResponse(house)).thenReturn(houseResponse);

            //when
            List<HouseResponse> actual = houseService.getAll(pageNumber, pageSize);

            //then
            assertEquals(expected, actual);
        }
    }

    @Nested
    class UpdateMethodTest {

        @Test
        void should_ReturnUuid_when_HouseUpdated() {

            //given
            HouseRequest houseRequest = HouseRequestTestData.builder().build().getHouseRequest();
            House house = HouseTestData.builder().build().getHouse();
            UUID expected = house.getUuid();

            when(houseMapper.fromRequestToHouse(houseRequest)).thenReturn(house);
            when(houseDAO.update(house)).thenReturn(house.getUuid());

            //when
            UUID actual = houseService.update(houseRequest);

            //then
            assertEquals(expected, actual);
        }
    }

    @Nested
    class DeleteMethodTest {

        @Test
        void should_NotThrowException_when_HouseDoesExist() {

            //given
            UUID id = UUID.fromString("66caf9ea-6074-4b1c-a1d1-c0dca7095f9f");

            doNothing().when(houseDAO).delete(id);

            //when
            Executable executable = () -> houseService.delete(id);

            //then
            assertDoesNotThrow(executable);
        }
    }

    @Nested
    class GetAllResidentsMethodTest {

        @Test
        void should_ReturnListOfPersonResponses_when_MethodInvoke() {

            //given
            PersonResponse personResponse = PersonResponseTestData.builder().build().getPersonResponse();
            Person person = PersonTestData.builder().build().getPerson();
            List<PersonResponse> expected = List.of(personResponse);
            List<Person> personList = List.of(person);
            UUID uuid = personResponse.uuid();

            when(houseDAO.getAllResidents(uuid)).thenReturn(personList);
            when(personMapper.fromPersonToResponse(person)).thenReturn(personResponse);

            //when
            List<PersonResponse> actual = houseService.getAllResidents(uuid);

            //then
            assertEquals(expected, actual);
        }
    }
}