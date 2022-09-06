package com.example.user_service.dto.in;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public class UserInDto {



    @NotBlank(message = "пустое имя")
    private String name;
    private String surname;
    private String lastname;

    @Email(message = "not valid email")
    private String email;
    private String password;

}
