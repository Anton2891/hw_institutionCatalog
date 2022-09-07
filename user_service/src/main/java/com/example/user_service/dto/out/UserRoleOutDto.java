package com.example.user_service.dto.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "dto to display user rights")
@Data
public class UserRoleOutDto {
    private Long id;
    private Long idUser;
    private Long idRole;
}
