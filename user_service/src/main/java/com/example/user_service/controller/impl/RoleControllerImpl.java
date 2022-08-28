package com.example.user_service.controller.impl;

import com.example.user_service.controller.RoleController;
import com.example.user_service.dto.in.RoleInDto;
import com.example.user_service.dto.out.RoleOutDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleControllerImpl implements RoleController {
    @Override
    public RoleOutDto createRole(RoleInDto roleInDto) {
        return null;
    }

    @Override
    public Long deleteRole(Long id) {
        return null;
    }
}
