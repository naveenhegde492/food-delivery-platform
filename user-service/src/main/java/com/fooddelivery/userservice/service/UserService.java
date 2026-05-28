package com.fooddelivery.userservice.service;

import com.fooddelivery.userservice.dto.CreateUserRequestDto;
import com.fooddelivery.userservice.dto.UpdateUserRequestDto;
import com.fooddelivery.userservice.dto.UserResponseDto;

public interface UserService {
    UserResponseDto getUserById(Long id);
    UserResponseDto createUser(CreateUserRequestDto request);
    UserResponseDto updateUser(Long id, UpdateUserRequestDto request);
}