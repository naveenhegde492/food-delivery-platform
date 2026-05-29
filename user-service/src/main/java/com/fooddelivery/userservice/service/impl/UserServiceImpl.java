package com.fooddelivery.userservice.service.impl;

import com.fooddelivery.userservice.dto.CreateUserRequestDto;
import com.fooddelivery.userservice.dto.UpdateUserRequestDto;
import com.fooddelivery.userservice.dto.UserResponseDto;
import com.fooddelivery.userservice.entity.User;
import com.fooddelivery.userservice.exception.UserAlreadyExistsException;
import com.fooddelivery.userservice.exception.UserNotFoundException;
import com.fooddelivery.userservice.mapper.UserMapper;
import com.fooddelivery.userservice.repository.UserRepository;
import com.fooddelivery.userservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUserById(Long id) {

        log.info("Fetching user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> {

                    log.error("User not found with id: {}", id);

                    return new UserNotFoundException(
                            "User not found with id: " + id
                    );
                });
        log.info("Successfully fetched user with id: {}", id);
        return UserMapper.toDto(user);
    }

    @Transactional
    @Override
    public UserResponseDto createUser(CreateUserRequestDto request) {

        log.info("Creating user with email: {}", request.getEmail());
        Optional<User> existingUser =
                userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            log.error("User already exists with email: {}", request.getEmail());
            throw new UserAlreadyExistsException(
                    "User already exists with email: "
                            + request.getEmail()
            );
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();

        User savedUser = userRepository.save(user);
        log.info("User created successfully with id: {}", savedUser.getId());
        return UserMapper.toDto(savedUser);
    }


    @Transactional
    @Override
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto request) {

        log.info("Updating user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with id: {}", id);
                    return new UserNotFoundException(
                            "User not found with id: " + id
                    );
                });

        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            log.error("Email already exists: {}", request.getEmail());
            throw new UserAlreadyExistsException(
                    "User already exists with email: "
                            + request.getEmail()
            );
        }

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        User updatedUser = userRepository.save(user);
        log.info("User updated successfully with id: {}", id);
        return UserMapper.toDto(updatedUser);
    }


    @Transactional
    @Override
    public void deleteUser(Long id) {

        log.info("Deleting user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with id: {}", id);
                    return new UserNotFoundException(
                            "User not found with id: " + id
                    );
                });

        userRepository.delete(user);
        log.info("User deleted successfully with id: {}", id);
    }
}