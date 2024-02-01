package ru.clevertec.house.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertec.house.dto.HouseRequest;
import ru.clevertec.house.dto.HouseResponse;
import ru.clevertec.house.service.impl.HouseRequestTestData;
import ru.clevertec.house.service.impl.HouseResponseTestData;
import ru.clevertec.house.service.impl.HouseServiceImpl;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RequiredArgsConstructor
public class HouseControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private final HouseServiceImpl houseService;

    @Test
    void testFindHouseById() throws Exception {

        HouseResponse houseResponse = HouseResponseTestData.builder().build().getHouseResponse();

        when(houseService.getById(houseResponse.uuid())).thenReturn(houseResponse);

        mockMvc.perform(get("/house/" + houseResponse.uuid())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uuid").value(houseResponse.uuid()))
                .andExpect(jsonPath("$.area").value(houseResponse.area().toString()))
                .andExpect(jsonPath("$.country").value(houseResponse.country()))
                .andExpect(jsonPath("$.city").value(houseResponse.city()))
                .andExpect(jsonPath("$.street").value(houseResponse.street()))
                .andExpect(jsonPath("$.number").value(houseResponse.number()));
    }

    @Test
    void testFindAll() throws Exception {

        HouseResponse houseResponse = HouseResponseTestData.builder().build().getHouseResponse();
        List<HouseResponse> list = List.of(houseResponse);
        int pageSize = 10;
        int pageNumber = 1;

        when(houseService.getAll(pageNumber, pageSize)).thenReturn(list);

        mockMvc.perform(get("/house")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testCreate() throws Exception {

        HouseRequest houseRequest = HouseRequestTestData.builder().build().getHouseRequest();
        UUID uuid = UUID.fromString("d1c41a4e-f756-4a9d-8f68-a8ae3412a34e ");

        when(houseService.save(houseRequest)).thenReturn(uuid);

        mockMvc.perform(post("/house")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdate() throws Exception {

        HouseRequest houseRequest = HouseRequestTestData.builder().build().getHouseRequest();
        UUID uuid = UUID.fromString("d1c41a4e-f756-4a9d-8f68-a8ae3412a34e ");

        when(houseService.update(houseRequest)).thenReturn(uuid);

        mockMvc.perform(put("/house")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testDelete() throws Exception {

        UUID uuid = UUID.fromString("d1c41a4e-f756-4a9d-8f68-a8ae3412a34e ");

        doNothing().when(houseService).delete(uuid);

        mockMvc.perform(delete("/house/" + uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}