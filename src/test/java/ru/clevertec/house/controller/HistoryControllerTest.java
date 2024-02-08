package ru.clevertec.house.controller;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.dto.PersonResponse;
import ru.clevertec.house.service.impl.HistoryServiceImpl;
import ru.clevertec.house.service.impl.HouseResponseTestData;
import ru.clevertec.house.service.impl.PersonResponseTestData;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RequiredArgsConstructor
public class HistoryControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private final HistoryServiceImpl historyService;


    @Test
    void testGetAllPersonsLivedInHouse() throws Exception {

        PersonResponse personResponse = PersonResponseTestData.builder().build().getPersonResponse();
        List<PersonResponse> list = List.of(personResponse);
        UUID uuid = UUID.fromString("d1c41a4e-f756-4a9d-8f68-a8ae3412a34e ");

        when(historyService.getAllPersonsLivedInHouse(uuid)).thenReturn(list);

        mockMvc.perform(get("/history/persons-lived-house/" + uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetAllPersonsOwnedHouse() throws Exception {

        PersonResponse personResponse = PersonResponseTestData.builder().build().getPersonResponse();
        List<PersonResponse> list = List.of(personResponse);
        UUID uuid = UUID.fromString("d1c41a4e-f756-4a9d-8f68-a8ae3412a34e ");

        when(historyService.getAllPersonsOwnedHouse(uuid)).thenReturn(list);

        mockMvc.perform(get("/history/persons-owned-house/" + uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetAllHousesWherePersonLived() throws Exception {

        HouseResponse houseResponse = HouseResponseTestData.builder().build().getHouseResponse();
        List<HouseResponse> list = List.of(houseResponse);
        UUID uuid = UUID.fromString("d1c41a4e-f756-4a9d-8f68-a8ae3412a34e ");

        when(historyService.getAllHousesWherePersonLived(uuid)).thenReturn(list);

        mockMvc.perform(get("/history/person-lived-houses/" + uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetAllHousesWhichPersonOwned() throws Exception {

        HouseResponse houseResponse = HouseResponseTestData.builder().build().getHouseResponse();
        List<HouseResponse> list = List.of(houseResponse);
        UUID uuid = UUID.fromString("d1c41a4e-f756-4a9d-8f68-a8ae3412a34e ");

        when(historyService.getAllHousesWhichPersonOwned(uuid)).thenReturn(list);

        mockMvc.perform(get("/history/person-owned-houses/" + uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1));
    }
}
