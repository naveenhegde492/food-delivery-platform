package com.fooddelivery.userservice.controller;

import com.fooddelivery.userservice.dto.CommonApiResponse;
import com.fooddelivery.userservice.dto.CreateUserRequestDto;
import com.fooddelivery.userservice.dto.UpdateUserRequestDto;
import com.fooddelivery.userservice.dto.UserResponseDto;
import com.fooddelivery.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @Operation(
            summary = "Get user by ID",
            description = "Fetches a user using the provided user ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User fetched successfully"
    )
    @ApiResponse(
            responseCode = "404",
            description = "User not found"
    )
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



    @Operation(
            summary = "Create User",
            description = "Creates a new user"
    )
    @ApiResponse(
            responseCode = "201",
            description = "User created successfully"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Validation failed"
    )
    @ApiResponse(
            responseCode = "409",
            description = "User already exists"
    )
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


    @Operation(
            summary = "Update User",
            description = "Updates an existing user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User updated successfully"
    )
    @ApiResponse(
            responseCode = "404",
            description = "User not found"
    )
    @ApiResponse(
            responseCode = "409",
            description = "Email already exists"
    )
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



    @Operation(
            summary = "Delete User",
            description = "Deletes a user by ID"
    )
    @ApiResponse(
            responseCode = "204",
            description = "User deleted successfully"
    )
    @ApiResponse(
            responseCode = "404",
            description = "User not found"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @Operation(
            summary = "Get all users",
            description = "Fetch users with pagination support"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Users fetched successfully"
    )
    @GetMapping
    public CommonApiResponse<Page<UserResponseDto>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<UserResponseDto> users =
                userService.getAllUsers(
                        PageRequest.of(page, size)
                );

        return CommonApiResponse.<Page<UserResponseDto>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .message("Users fetched successfully")
                .data(users)
                .build();
    }
}