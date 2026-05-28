package com.fooddelivery.userservice.service;

import com.fooddelivery.userservice.dto.UserResponseDto;
import com.fooddelivery.userservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    private final UserService userService = new UserServiceImpl();

    @Test
    void shouldReturnUserSuccessfully() {

        UserResponseDto response = userService.getUserById(1L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, response.getId());
    }
}