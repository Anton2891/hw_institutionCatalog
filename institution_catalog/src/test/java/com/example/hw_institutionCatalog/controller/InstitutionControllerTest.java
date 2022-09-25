package com.example.hw_institutionCatalog.controller;

import com.example.hw_institutionCatalog.AppContextTest;
import com.example.hw_institutionCatalog.clients.UserOutDto;
import com.example.hw_institutionCatalog.clients.UserServiceClients;
import com.example.hw_institutionCatalog.dto.in.ChangeOwnerInDto;
import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.dto.in.ReviewInDto;
import com.example.hw_institutionCatalog.dto.out.InstitutionOutDto;
import com.example.hw_institutionCatalog.dto.out.ReviewOutDto;
import com.example.hw_institutionCatalog.mapper.InstitutionMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.assertj.core.util.Lists;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class InstitutionControllerTest extends AppContextTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    private InstitutionOutDto institutionOutDto11;
    private InstitutionOutDto institutionOutDto12;
    private InstitutionInDto institutionInDtoWithoutReview1;
    private InstitutionInDto institutionInDtoWithoutReview2;
    @MockBean
    private UserServiceClients userServiceClients;

    @BeforeEach
    void beforeAddInstitution() {
        institutionOutDto11 = InstitutionOutDto.builder()
                .id(11)
                .name("institutionInDto1")
                .address("mmmmmmmmmmm2585")
                .description("zzzzzzzzzzzzzzz")
                .telephoneNumber("+79996663322")
                .ownerId(13)
                .foundationDate(LocalDate.of(2011, 6, 23))
                .build();
        institutionOutDto12 = InstitutionOutDto.builder()
                .id(12)
                .name("institutionInDto2")
                .address("2345678kkkkkkkkkkkkk")
                .description("dddddddddddddddddddddddddddd")
                .telephoneNumber("+79999999999")
                .ownerId(10)
                .foundationDate(LocalDate.of(2002, 01, 11))
                .build();
        institutionInDtoWithoutReview1 = InstitutionInDto.builder()
                .name("institutionInDto1")
                .address("mmmmmmmmmmm2585")
                .description("zzzzzzzzzzzzzzz")
                .telephoneNumber("+79996663322")
                .ownerId(13)
                .foundationDate(LocalDate.of(2011, 06, 23))
                .build();
        institutionInDtoWithoutReview2 = InstitutionInDto.builder()
                .name("institutionInDto2")
                .address("2345678kkkkkkkkkkkkk")
                .description("dddddddddddddddddddddddddddd")
                .telephoneNumber("+79999999999")
                .ownerId(10)
                .foundationDate(LocalDate.of(2002, 01, 11))
                .build();
    }

    @Test
    void addInstitution() throws Exception {
        String afterSaveRestaurant = objectMapper.writeValueAsString(institutionOutDto11);
        MvcResult mvcResult = this.mockMvc.perform(post("/inst")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(institutionInDtoWithoutReview1)))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.name").value(institutionOutDto11.getName()))
                .andExpect(jsonPath("$.address").value(institutionOutDto11.getAddress()))
                .andExpect(jsonPath("$.description").value(institutionOutDto11.getDescription()))
                .andExpect(jsonPath("$.telephoneNumber").value(institutionOutDto11.getTelephoneNumber()))
                .andExpect(jsonPath("$.ownerId").value(institutionOutDto11.getOwnerId()))
                .andExpect(jsonPath("$.foundationDate").value(institutionOutDto11.getFoundationDate().format(DateTimeFormatter.ISO_DATE)))
                .andReturn();
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check media typeof response
//                .andExpect(content().json(afterSaveRestaurant)); // check response
        this.mockMvc.perform(post("/inst")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(institutionInDtoWithoutReview2)));

        this.mockMvc.perform(post("/inst/{id}/review", 11)
                .param("review", "test_text_1")
                .param("rating", "1"))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/inst/{id}/review", 11)
                        .param("review", "test_text_2")
                        .param("rating", "3"))
                .andDo(print())
                .andExpect(status().isOk());



        this.mockMvc.perform(get("/inst"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(get("/inst/{id}", 11))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.name").value(institutionOutDto11.getName()))
                .andExpect(jsonPath("$.address").value(institutionOutDto11.getAddress()))
                .andExpect(jsonPath("$.description").value(institutionOutDto11.getDescription()))
                .andExpect(jsonPath("$.telephoneNumber").value(institutionOutDto11.getTelephoneNumber()))
                .andExpect(jsonPath("$.ownerId").value(institutionOutDto11.getOwnerId()))
                .andExpect(jsonPath("$.foundationDate").value(institutionOutDto11.getFoundationDate().format(DateTimeFormatter.ISO_DATE)))
                .andExpect(jsonPath("$.reviewList").isArray())
                .andExpect(jsonPath("$.reviewList").isNotEmpty());

        this.mockMvc.perform(put("/inst/{id}/description", 11)
                        .param("description", "test_new_description"))
                .andDo(print())
                .andExpect(status().isOk());


    }

//    @Test
//    void refactorInstitutionById() throws Exception {
//        this.mockMvc.perform(put("/inst/{id}/description", 11)
//                .param("description", "test_new_description"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.description").value("test_new_description"));
//    }

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
