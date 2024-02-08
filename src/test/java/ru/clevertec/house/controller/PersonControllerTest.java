package ru.clevertec.house.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertec.house.dto.PersonRequest;
import ru.clevertec.house.dto.PersonResponse;
import ru.clevertec.house.service.impl.PersonRequestTestData;
import ru.clevertec.house.service.impl.PersonResponseTestData;
import ru.clevertec.house.service.impl.PersonServiceImpl;


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
public class PersonControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private final PersonServiceImpl personService;

    @Test
    void testFindPersonById() throws Exception {

        PersonResponse personResponse = PersonResponseTestData.builder().build().getPersonResponse();

        when(personService.getById(personResponse.uuid())).thenReturn(personResponse);

        mockMvc.perform(get("/person/" + personResponse.uuid())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uuid").value(personResponse.uuid()))
                .andExpect(jsonPath("$.name").value(personResponse.name()))
                .andExpect(jsonPath("$.surname").value(personResponse.surname()))
                .andExpect(jsonPath("$.sex").value(personResponse.sex().toString()))
                .andExpect(jsonPath("$.passportSeries").value(personResponse.passportSeries()))
                .andExpect(jsonPath("$.passportNumber").value(personResponse.passportNumber()))
                .andExpect(jsonPath("$.createDate").value(personResponse.createDate().toString()))
                .andExpect(jsonPath("$.updateDate").value(personResponse.updateDate().toString()));
    }

    @Test
    void testFindAll() throws Exception {

        PersonResponse personResponse = PersonResponseTestData.builder().build().getPersonResponse();
        List<PersonResponse> list = List.of(personResponse);
        int pageSize = 10;
        int pageNumber = 1;

        when(personService.getAll(pageNumber, pageSize)).thenReturn(list);

        mockMvc.perform(get("/person")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testCreate() throws Exception {

        PersonRequest personRequest = PersonRequestTestData.builder().build().getPersonRequest();
        UUID uuid = UUID.fromString("d1c41a4e-f756-4a9d-8f68-a8ae3412a34e ");

        when(personService.save(personRequest)).thenReturn(uuid);

        mockMvc.perform(post("/person")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdate() throws Exception {

        PersonRequest personRequest = PersonRequestTestData.builder().build().getPersonRequest();
        UUID uuid = UUID.fromString("d1c41a4e-f756-4a9d-8f68-a8ae3412a34e ");

        when(personService.update(personRequest)).thenReturn(uuid);

        mockMvc.perform(put("/person")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testDelete() throws Exception {

        UUID uuid = UUID.fromString("d1c41a4e-f756-4a9d-8f68-a8ae3412a34e ");

        doNothing().when(personService).delete(uuid);

        mockMvc.perform(delete("/person/" + uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}