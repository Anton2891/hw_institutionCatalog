package com.example.user_service.controller;

import com.example.user_service.dto.in.ChangePasswordInDto;
import com.example.user_service.dto.in.UserInDto;
import com.example.user_service.dto.out.UserOutDto;
import com.example.user_service.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface UserController {

    @PostMapping("/create")
    UserOutDto createUser(@Valid @RequestBody UserInDto userInDto);

    @PutMapping("/update/{id}")
    UserOutDto updateUser(UserInDto userInDto, @PathVariable Long id) throws UserNotFoundException;

    @DeleteMapping("/delete/{id}")
    Long deleteUser(@PathVariable Long id) throws UserNotFoundException;

    @GetMapping("/get/{id}")
    UserOutDto getUser(@PathVariable Long id) throws UserNotFoundException;

    @PutMapping("/password")
    void changePassword(@Valid @RequestBody ChangePasswordInDto changePasswordInDto) throws UserNotFoundException;
}
