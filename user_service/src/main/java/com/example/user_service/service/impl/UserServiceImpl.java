package com.example.user_service.service.impl;

import com.example.user_service.dto.in.ChangePasswordInDto;
import com.example.user_service.dto.in.UserInDto;
import com.example.user_service.dto.out.UserOutDto;
import com.example.user_service.entity.User;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserOutDto createUser(UserInDto userInDto) {
        if (userRepository.existsByEmail(userInDto.getEmail())){
            throw new RuntimeException();
        }
        User user = userMapper.userInDtoToUser(userInDto);
        return userMapper.userToUserOutDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserOutDto updateUser(UserInDto userInDto, Long id) throws UserNotFoundException {
        if(!userRepository.existsById(id) && userRepository.existsByEmail(userInDto.getEmail())){
            throw new UserNotFoundException(id);
        }
        User user = userMapper.userInDtoToUser(userInDto);
        user.setId(id);
        user.setRegistrationDate(getUser(id).getRegistrationDate());
        user.setPassword(getUser(id).getPassword());
//        user.setRegistrationDate(LocalDateTime.now());
        User saveUser = userRepository.save(user);
        return userMapper.userToUserOutDto(saveUser);
    }

    @Override
    public Long deleteUser(Long id) throws UserNotFoundException {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return id;
    }

    @Override
    public UserOutDto getUser(Long id) throws UserNotFoundException {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()){
            throw new UserNotFoundException(id);
        }
        return userMapper.userToUserOutDto(byId.get());
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordInDto changePasswordInDto) throws UserNotFoundException {
        Optional<User> byEmail = userRepository.findByEmail(changePasswordInDto.getEmail());
        if(byEmail.isEmpty()){
            throw new UserNotFoundException(changePasswordInDto.getEmail());
        }
        User user = byEmail.get();
        if(!Objects.equals(changePasswordInDto.getOldPassword(), user.getPassword())){
            throw new UserNotFoundException(changePasswordInDto.getEmail());
        }
        user.setPassword(changePasswordInDto.getNewPassword());
    }
}
