package com.example.user_service.mapper;

import com.example.user_service.dto.in.RoleInDto;
import com.example.user_service.dto.out.RoleOutDto;
import com.example.user_service.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleOutDto roleToRoleOutDto(Role role);
    Role roleInDtoToRole(RoleInDto roleInDto);
}
