package com.example.hw_institutionCatalog.controller;

import com.example.hw_institutionCatalog.AppContextTest;
import com.example.hw_institutionCatalog.clients.UserOutDto;
import com.example.hw_institutionCatalog.clients.UserServiceClients;
import com.example.hw_institutionCatalog.dto.in.ChangeOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.dto.out.InstitutionOutDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class InstitutionControllerTest extends AppContextTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    private InstitutionOutDto institutionOutDto;
    private InstitutionInDto institutionInDto;
    @MockBean
    private UserServiceClients userServiceClients;

    @BeforeEach
    void beforeAddRestaurant() {
        institutionOutDto = InstitutionOutDto.builder()
                .id(11)
                .name("institutionInDto")
                .address("mmmmmmmmmmm2585")
                .description("zzzzzzzzzzzzzzz")
                .telephoneNumber("+79996663322")
//                .email("fgh@gdfd.ru")
                .foundationDate(LocalDate.of(2011, 6, 23))
                .build();
        institutionInDto = InstitutionInDto.builder()
                .name("institutionInDto")
                .description("zzzzzzzzzzzzzzz")
                .address("mmmmmmmmmmm2585")
                .foundationDate(LocalDate.of(2011, 06, 23))
                .telephoneNumber("+79996663322")
                .ownerId(13)
                .build();

    }

    @Test
    void addRestaurant() throws Exception {
        String afterSaveRestaurant = objectMapper.writeValueAsString(institutionOutDto);
        this.mockMvc.perform(post("/inst")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(institutionInDto)))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check media typeof response
                .andExpect(content().json(afterSaveRestaurant)); // check response
    }

    @Test
    public void institutionNotFound() throws Exception {
        this.mockMvc.perform(get("/inst/{id}", 9999))
                .andDo(print()) //print response in console
                .andExpect(status().isNotFound());
    }

    @Test
    public void validationTest() throws Exception {
        ObjectMapper objectMapper = new JsonMapper();
        InstitutionInDto dto = InstitutionInDto.builder()
                .name("")
                .foundationDate(LocalDate.now().plusDays(6))
                .telephoneNumber("")
                .build();

        this.mockMvc.perform(post("/inst")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print()) //print response in console
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{\"name\":\"пустое имя\"," +
                                "\"telephoneNumber\": \"пустой телефонный номер\"," +
//                        "\"telephoneNumber\": \"пустой телефонный номер\","
                                "\"foundationDate\": \"будущее\"}"
                ));// check status
    }

    @Test
    @Disabled
    public void checkFoundationDateException() throws Exception {
        ObjectMapper objectMapper = new JsonMapper();
        LocalDate date = LocalDate.now().plusDays(6);
        InstitutionInDto dto = InstitutionInDto.builder()
                .name("test")
                .foundationDate(date)
                .telephoneNumber("+7 999 999 99 99")
                .build();

        String result = "institution with name \"" + "test" + "\"" +
                "has foundation date " + date;
        this.mockMvc.perform(post("/inst")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print()) //print response in console
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(result));
    }

    @Test
    public void changeOwner() throws Exception {
        Mockito.when(userServiceClients.getUser(any())).thenReturn(ResponseEntity.accepted()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new UserOutDto()));
        ChangeOwnerInDto dto = new ChangeOwnerInDto();
        dto.setNewOwnerId(15);
        dto.setOldOwnerId(13);
        this.mockMvc.perform(post("/inst/owner/change")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print()); //print response in console

    }


    @Test
    public void whenGetRequestToGetAllEndPoint_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/inst")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$['pageable']['paged']").value("true"));
    }
}
