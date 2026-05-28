package com.fooddelivery.userservice.service.impl;

import com.fooddelivery.userservice.dto.UserResponseDto;
import com.fooddelivery.userservice.exception.UserNotFoundException;
import com.fooddelivery.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserResponseDto getUserById(Long id) {

        log.info("Fetching user with id: {}", id);

        if (id <= 0) {
            log.error("Invalid user id received: {}", id);
            throw new UserNotFoundException("User not found with id: " + id);
        }

        UserResponseDto response = UserResponseDto.builder()
                .id(id)
                .name("Naveen")
                .email("naveen@example.com")
                .build();

        log.info("Successfully fetched user with id: {}", id);

        return response;
    }
}