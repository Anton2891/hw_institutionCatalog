package com.example.user_service.service.impl;

import com.example.user_service.dto.in.UserInDto;
import com.example.user_service.dto.out.UserOutDto;
import com.example.user_service.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserServiceImpl userService;

    @Test
    void createUser() {
        UserInDto userInDto = new UserInDto();
        UserOutDto userOutDto = userService.createUser(userInDto);
        assertEquals(userOutDto, userService.getUser(userOutDto.getId()));
    }

    @Test
    void updateUser() {
        UserInDto userInDto = new UserInDto();
        UserOutDto userOutDto = userService.createUser(userInDto);
        UserOutDto userUpdate = userService.updateUser(userInDto, userOutDto.getId());
        assertEquals(userOutDto, userUpdate);
    }

    @Test
    void deleteUser() {
        UserInDto userInDto = new UserInDto();
        UserOutDto userOutDto = userService.createUser(userInDto);
        Long userIdDel = userService.deleteUser(userOutDto.getId());
        assertEquals(userIdDel, userOutDto.getId());

    }

}