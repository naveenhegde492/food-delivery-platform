package com.fooddelivery.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2, max = 100,
            message = "Name must be between 2 and 100 characters")
    private String name;


    @Schema(
            description = "User email",
            example = "naveen@gmail.com"
    )
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Size(max = 255,
            message = "Email cannot exceed 255 characters")
    private String email;
}