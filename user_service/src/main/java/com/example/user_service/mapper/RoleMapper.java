package com.example.user_service.mapper;

import com.example.user_service.dto.in.RoleInDto;
import com.example.user_service.dto.out.RoleOutDto;
import com.example.user_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleOutDto roleToRoleOutDto(Role role);
    @Mapping(target = "id", ignore = true)
    Role roleInDtoToRole(RoleInDto roleInDto);
}
