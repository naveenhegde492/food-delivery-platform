package com.fooddelivery.userservice.service.impl;

import com.fooddelivery.userservice.dto.UserResponseDto;
import com.fooddelivery.userservice.exception.UserNotFoundException;
import com.fooddelivery.userservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserResponseDto getUserById(Long id) {

        if (id <= 0) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        return new UserResponseDto(
                id,
                "Naveen",
                "naveen@example.com"
        );
    }
}