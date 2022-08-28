package com.example.user_service.service.impl;

import com.example.user_service.UserServiceApplicationTests;
import com.example.user_service.dto.in.RoleInDto;
import com.example.user_service.dto.in.UserInDto;
import com.example.user_service.dto.in.UserRoleInDto;
import com.example.user_service.dto.out.RoleOutDto;
import com.example.user_service.dto.out.UserOutDto;
import com.example.user_service.dto.out.UserRoleOutDto;
import com.example.user_service.entity.UserRole;
import com.example.user_service.repository.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.relation.RoleNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RoleServiceImplTest extends UserServiceApplicationTests {

    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRoleRepository userRoleRepository;


    @Test
    void createRole() throws RoleNotFoundException {
        RoleInDto roleInDto = RoleInDto.builder()
                .roleName("admin")
                .build();
        RoleOutDto roleOutDto = roleService.createRole(roleInDto);
        assertEquals(roleOutDto, roleService.getRole(roleOutDto.getId()));
    }

    @Test
    void deleteRole() throws RoleNotFoundException {
        RoleInDto roleInDto = RoleInDto.builder()
                .roleName("user")
                .build();
        RoleOutDto roleOutDto = roleService.createRole(roleInDto);
        Long roleIdDel = roleService.deleteRole(roleOutDto.getId());
        assertEquals(roleIdDel, roleOutDto.getId());
    }

    @Test
    void addRoleToUser() throws RoleNotFoundException {
        RoleInDto roleInDto = RoleInDto.builder()
                .roleName("admin1")
                .build();
        RoleOutDto roleOutDto = roleService.createRole(roleInDto);
        UserInDto userInDto = UserInDto.builder()
                .name("Test1")
                .surname("service")
                .lastname("catalog")
                .email("Test1@gmail.com")
                .build();
        UserOutDto userOutDto = userService.createUser(userInDto);
        UserRoleInDto userRoleInDto = UserRoleInDto.builder()
                .idRole(roleOutDto.getId())
                .idUser(userOutDto.getId())
                .build();
        UserRoleOutDto userRoleOutDto = roleService.addRoleToUser(userRoleInDto);
        assertEquals(userRoleOutDto, roleService.getUserRole(userRoleOutDto.getId()));
    }

    @Test
    void deleteRoleToUser() throws RoleNotFoundException {
        RoleInDto roleInDto = RoleInDto.builder()
                .roleName("admin2")
                .build();
        RoleOutDto roleOutDto = roleService.createRole(roleInDto);
        UserInDto userInDto = UserInDto.builder()
                .name("Test1")
                .surname("service")
                .lastname("catalog")
                .email("Test2@gmail.com")
                .build();
        UserOutDto userOutDto = userService.createUser(userInDto);
        UserRoleInDto userRoleInDto = UserRoleInDto.builder()
                .idRole(roleOutDto.getId())
                .idUser(userOutDto.getId())
                .build();
        UserRoleOutDto userRoleOutDto = roleService.addRoleToUser(userRoleInDto);
        Long userRoleDelId = roleService.deleteRoleToUser(userRoleOutDto.getId());
        assertEquals(userRoleDelId, userRoleOutDto.getId());
        Optional<UserRole> byId = userRoleRepository.findById(userRoleDelId);
        assertFalse(byId.isPresent());
    }
}