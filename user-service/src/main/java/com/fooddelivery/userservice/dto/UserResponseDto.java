package com.fooddelivery.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User response payload")
public class UserResponseDto {

    @Schema(
            description = "Unique user identifier",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "User name",
            example = "Naveen"
    )
    private String name;

    @Schema(
            description = "User email address",
            example = "naveen@gmail.com"
    )
    private String email;
}