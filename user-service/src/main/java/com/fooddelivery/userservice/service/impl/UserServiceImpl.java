package com.fooddelivery.userservice.service.impl;

import com.fooddelivery.userservice.dto.CreateUserRequestDto;
import com.fooddelivery.userservice.dto.UserResponseDto;
import com.fooddelivery.userservice.entity.User;
import com.fooddelivery.userservice.exception.UserNotFoundException;
import com.fooddelivery.userservice.mapper.UserMapper;
import com.fooddelivery.userservice.repository.UserRepository;
import com.fooddelivery.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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

    @Override
    public UserResponseDto createUser(CreateUserRequestDto request) {

        log.info("Creating user with email: {}", request.getEmail());

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        log.info("User created successfully with id: {}", savedUser.getId());

        return UserMapper.toDto(savedUser);
    }
}