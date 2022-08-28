package com.example.user_service.service;

import com.example.user_service.dto.in.RoleInDto;
import com.example.user_service.dto.in.UserRoleInDto;
import com.example.user_service.dto.out.RoleOutDto;
import com.example.user_service.dto.out.UserRoleOutDto;

import javax.management.relation.RoleNotFoundException;

public interface RoleService {

    RoleOutDto createRole(RoleInDto roleInDto) throws RoleNotFoundException;
    Long deleteRole(Long id) throws RoleNotFoundException;
    RoleOutDto getRole(Long id) throws RoleNotFoundException;
    UserRoleOutDto addRoleToUser(UserRoleInDto userRoleInDto) throws RoleNotFoundException;
    Long deleteRoleToUser(Long id) throws RoleNotFoundException;
    UserRoleOutDto getUserRole(Long id) throws RoleNotFoundException;
}
