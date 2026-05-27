package com.fooddelivery.userservice.controller;

import com.fooddelivery.userservice.dto.CommonApiResponse;
import com.fooddelivery.userservice.dto.CreateUserRequestDto;
import com.fooddelivery.userservice.dto.UserResponseDto;
import com.fooddelivery.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public CommonApiResponse<UserResponseDto> getUserById(@PathVariable Long id) {

        UserResponseDto response = userService.getUserById(id);

        return CommonApiResponse.<UserResponseDto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("User fetched successfully")
                .data(response)
                .build();
    }


    @PostMapping
    public CommonApiResponse<UserResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto request) {

        UserResponseDto response = UserResponseDto.builder()
                .id(1L)
                .name(request.getName())
                .email(request.getEmail())
                .build();

        return CommonApiResponse.<UserResponseDto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .message("User created successfully")
                .data(response)
                .build();
    }

}