package com.example.hw_institutionCatalog.controller;

import com.example.hw_institutionCatalog.AppContextTest;
import com.example.hw_institutionCatalog.dto.in.InstitutionInDto;
import com.example.hw_institutionCatalog.dto.out.InstitutionOutDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class InstitutionControllerTest extends AppContextTest {
    @Autowired
    private MockMvc mockMvc;
    private InstitutionOutDto institutionOutDto;
    private InstitutionOutDto institutionOutDTOWithoutReview;
    private InstitutionInDto institutionInDto;

    @BeforeEach
    void beforeAddRestaurant() {
        institutionOutDto = InstitutionOutDto.builder()
                .id(11)
                .name("institutionInDto")
                .address("mmmmmmmmmmm2585")
                .description("zzzzzzzzzzzzzzz")
//                .telephoneNumber("+79992526363")
//                .email("fgh@gdfd.ru")
                .foundationDate(LocalDate.of(2008, 02, 28))
                .build();
        institutionInDto = InstitutionInDto.builder()
                .name("institutionInDto")
                .address("mmmmmmmmmmm2585")
                .description("zzzzzzzzzzzzzzz")
//                .telephoneNumber("+79992526363")
//                .email("fgh@gdfd.ru")
                .foundationDate(LocalDate.of(2008, 02, 28))
                .build();
        InstitutionOutDto.InstitutionOutDtoBuilder institutionOutDtoBuilder = InstitutionOutDto.builder()
                .id(25)
                .name("AAAAAAAAA")
                .address("aaa25")
                .description("aAaAQaaaaaa")
                .foundationDate(LocalDate.of(2011, 6,23));
//        institutionOutDTOWithoutReview = institutionOutDtoBuilder
//                .reviews(null)
//                .build();

    }

    @Test
    void addRestaurant() throws Exception {
        ObjectMapper objectMapper =new JsonMapper();
//        String afterSaveRestaurant = objectMapper.writeValueAsString(institutionOutDto);
        String afterSaveRestaurant = objectMapper.writeValueAsString(institutionOutDto);
        this.mockMvc.perform(post("/add/inst"/*,"?name=institutionInDto&address=mmmmmmmmmmm2585&description=zzzzzzzzzzzzzzz" +
                        "&foundation_date=2011-6-23"*/)
                        .param("name", "institutionInDto")
                        .param("address","mmmmmmmmmmm2585")
                        .param("description","zzzzzzzzzzzzzzz")
                        .param("foundation_date","2011-06-23"))
//                .contentType(MediaType.APPLICATION_JSON)
//                        .contentType("?name=institutionInDto&address=mmmmmmmmmmm2585&description=zzzzzzzzzzzzzzz\" +\n" +
//                                "                        \"&foundation_date=2011-6-23")
//                .content(objectMapper.writeValueAsString("name=institutionInDto&address=mmmmmmmmmmm2585&description=zzzzzzzzzzzzzzz\" +\n" +
//                        "                        \"&foundation_date=2011-6-23")))
//                .content(objectMapper.writeValueAsString("institutionInDto", "")))
                .andDo(print()) //print response in console
                .andExpect(status().isOk()) // check status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // check media typeof response
                .andExpect(content().json(afterSaveRestaurant)); // check response
    }

}
