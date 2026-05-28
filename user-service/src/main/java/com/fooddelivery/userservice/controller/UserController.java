package com.fooddelivery.userservice.controller;

import com.fooddelivery.userservice.dto.CommonApiResponse;
import com.fooddelivery.userservice.dto.CreateUserRequestDto;
import com.fooddelivery.userservice.dto.UpdateUserRequestDto;
import com.fooddelivery.userservice.dto.UserResponseDto;
import com.fooddelivery.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public CommonApiResponse<UserResponseDto> getUserById(@PathVariable Long id) {

        UserResponseDto response =
                userService.getUserById(id);

        return CommonApiResponse.<UserResponseDto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("User fetched successfully")
                .data(response)
                .build();
    }

    @PostMapping
    public CommonApiResponse<UserResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto request) {

        UserResponseDto response =
                userService.createUser(request);

        return CommonApiResponse.<UserResponseDto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .message("User created successfully")
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public CommonApiResponse<UserResponseDto> updateUser(@PathVariable Long id,
                                                         @Valid @RequestBody UpdateUserRequestDto request) {

        UserResponseDto response =
                userService.updateUser(id, request);

        return CommonApiResponse.<UserResponseDto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("User updated successfully")
                .data(response)
                .build();
    }
}