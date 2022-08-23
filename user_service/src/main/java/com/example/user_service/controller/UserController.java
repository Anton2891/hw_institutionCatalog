package com.example.user_service.controller;

import com.example.user_service.dto.in.UserInDto;
import com.example.user_service.dto.out.UserOutDto;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
public interface UserController {

    @PostMapping("/create")
    UserOutDto createUser(@RequestBody UserInDto userInDto);

    @PutMapping("/update/{id}")
    UserOutDto updateUser(UserInDto userInDto, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    Long deleteUser(@PathVariable Long id);

    @GetMapping("/get/{id}")
    UserOutDto getUser(@PathVariable Long id);
}
