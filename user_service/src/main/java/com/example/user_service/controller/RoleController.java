package com.example.user_service.controller;

import com.example.user_service.dto.in.RoleInDto;
import com.example.user_service.dto.out.RoleOutDto;
import org.springframework.web.bind.annotation.*;


public interface RoleController {

    @PostMapping("/create")
    RoleOutDto createRole(@RequestBody RoleInDto roleInDto);

    @DeleteMapping( "/delete/{id}")
    Long deleteRole (@PathVariable Long id);

//    @PostMapping("/add/user/{user_id}")
//    String addRole(@PathVariable Long id);
}
