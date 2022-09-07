package com.example.user_service.service.impl;

import com.example.user_service.UserServiceApplicationTests;
import com.example.user_service.dto.in.UserInDto;
import com.example.user_service.dto.out.UserOutDto;
import com.example.user_service.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest extends UserServiceApplicationTests{

    @Autowired
    private UserServiceImpl userService;

    @Test
    void createUser() throws UserNotFoundException {
        UserInDto userInDto = UserInDto.builder()
                .name("Test1")
                .surname("service")
                .lastname("catalog")
                .email("Test1@gmail.com")
                .build();
        UserOutDto userOutDto = userService.createUser(userInDto);
        assertEquals(userOutDto, userService.getUser(userOutDto.getId()));
        System.out.println(userOutDto);
    }

    @Test
    void updateUser() throws UserNotFoundException {
        UserInDto userInDto = UserInDto.builder()
                .name("Test2")
                .surname("Institution service")
                .lastname("institution catalog")
                .email("Test2@gmail.com")
                .password("Test@1234")
                .build();
        UserOutDto userOutDto = userService.createUser(userInDto);
        UserOutDto userUpdate = userService.updateUser(userInDto, userOutDto.getId());
        assertEquals(userOutDto, userUpdate);
        System.out.println(userOutDto);
        System.out.println(userUpdate);
    }

    @Test
    void deleteUser() throws UserNotFoundException {
        UserInDto userInDto = UserInDto.builder()
                .name("Test3")
                .surname("Institution service")
                .lastname("institution catalog")
                .email("Test3@gmail.com")
                .build();
        UserOutDto userOutDto = userService.createUser(userInDto);
        Long userIdDel = userService.deleteUser(userOutDto.getId());
        assertEquals(userIdDel, userOutDto.getId());

    }

}