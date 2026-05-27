package com.fooddelivery.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonApiResponse<T> {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;
}