package com.example.user_service.dto.in;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRoleInDto {
    private Long idUser;
    private Long idRole;
}
