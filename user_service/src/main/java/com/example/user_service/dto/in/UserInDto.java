package com.example.user_service.dto.in;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInDto {

    private String name;
    private String surname;
    private String lastname;
    private String email;

}
