package com.fooddelivery.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "Request payload for creating user")
@Getter
@NoArgsConstructor
public class CreateUserRequestDto {

    @Schema(
            description = "User name",
            example = "Naveen"
    )
    @NotBlank(message = "Name is required")
    private String name;


    @Schema(
            description = "User email",
            example = "naveen@gmail.com"
    )
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
}