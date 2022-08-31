package com.example.user_service.controller.impl;

import com.example.user_service.controller.RoleController;
import com.example.user_service.dto.in.RoleInDto;
import com.example.user_service.dto.out.RoleOutDto;
import com.example.user_service.service.impl.RoleServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/role")
public class RoleControllerImpl implements RoleController {
    private final RoleServiceImpl roleService;

    public RoleControllerImpl(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleOutDto createRole(RoleInDto roleInDto) throws RoleNotFoundException {
        return roleService.createRole(roleInDto);
    }

    @Override
    public Long deleteRole(Long id) throws RoleNotFoundException {
        return roleService.deleteRole(id);
    }
}
