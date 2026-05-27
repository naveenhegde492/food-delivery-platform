package com.fooddelivery.userservice.service;

import com.fooddelivery.userservice.dto.UserResponseDto;

public interface UserService {
    UserResponseDto getUserById(Long id);
}