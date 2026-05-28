package com.fooddelivery.userservice.mapper;

import com.fooddelivery.userservice.dto.UserResponseDto;
import com.fooddelivery.userservice.entity.User;

public class UserMapper {

    private UserMapper() {
    }

    public static UserResponseDto toDto(User user) {

        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}