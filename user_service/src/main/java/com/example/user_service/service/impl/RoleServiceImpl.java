package com.example.user_service.service.impl;

import com.example.user_service.dto.in.RoleInDto;
import com.example.user_service.dto.in.UserRoleInDto;
import com.example.user_service.dto.out.RoleOutDto;
import com.example.user_service.dto.out.UserRoleOutDto;
import com.example.user_service.entity.Role;
import com.example.user_service.entity.UserRole;
import com.example.user_service.mapper.RoleMapper;
import com.example.user_service.mapper.UserRoleMapper;
import com.example.user_service.repository.RoleRepository;
import com.example.user_service.repository.UserRoleRepository;
import com.example.user_service.service.RoleService;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, UserRoleRepository userRoleRepository, RoleMapper roleMapper, UserRoleMapper userRoleMapper) {
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    @Transactional
    public RoleOutDto createRole(RoleInDto roleInDto) throws RoleNotFoundException {
        if(roleRepository.existsByRoleName(roleInDto.getRoleName())){
            throw new RoleNotFoundException();
        }
        Role role = roleMapper.roleInDtoToRole(roleInDto);
        return roleMapper.roleToRoleOutDto(roleRepository.save(role));
    }

    @Override
    @Transactional
    public Long deleteRole(Long id) throws RoleNotFoundException {
        if(!roleRepository.existsById(id)){
            throw  new RoleNotFoundException();
        }
        roleRepository.deleteById(id);
        return id;
    }

    @Override
    public RoleOutDto getRole(Long id) throws RoleNotFoundException {
        Optional<Role> byId = roleRepository.findById(id);
        if(byId.isEmpty()){
            throw new RoleNotFoundException();
        }
        return roleMapper.roleToRoleOutDto(byId.get());
    }

    @Override
    @Transactional
    public UserRoleOutDto addRoleToUser(UserRoleInDto userRoleInDto) throws RoleNotFoundException {
        if(userRoleRepository.existsByIdUserAndIdRole(userRoleInDto.getIdUser(), userRoleInDto.getIdRole())){
            throw new RoleNotFoundException();
        }
        UserRole userRole = userRoleMapper.userRoleInDtoToUserRole(userRoleInDto);

        return userRoleMapper.userRoleToUserRoleOutDto(userRoleRepository.save(userRole));
    }

    @Override
    public Long deleteRoleToUser(Long id) throws RoleNotFoundException {
        if(!userRoleRepository.existsById(id)){
            throw new RoleNotFoundException();
        }
        userRoleRepository.deleteById(id);
        return id;
    }

    @Override
    public UserRoleOutDto getUserRole(Long id) throws RoleNotFoundException {
        Optional<UserRole> byId = userRoleRepository.findById(id);
        if(byId.isEmpty()){
            throw new RoleNotFoundException();
        }
        return userRoleMapper.userRoleToUserRoleOutDto(byId.get());
    }


}
