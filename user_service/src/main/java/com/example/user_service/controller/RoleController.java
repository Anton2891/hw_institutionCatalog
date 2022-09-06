package com.example.user_service.controller;

import com.example.user_service.dto.in.RoleInDto;
import com.example.user_service.dto.out.RoleOutDto;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RequestMapping("/role")
public interface RoleController {

    @PostMapping
    RoleOutDto createRole(@RequestBody RoleInDto roleInDto) throws RoleNotFoundException;

    @DeleteMapping( "/{id}")
    Long deleteRole (@PathVariable Long id) throws RoleNotFoundException;

//    @PostMapping("/add/user/{user_id}")
//    String addRole(@PathVariable Long id);
}
