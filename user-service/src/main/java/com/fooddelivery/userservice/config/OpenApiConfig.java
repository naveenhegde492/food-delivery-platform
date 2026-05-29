package com.fooddelivery.userservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Food Delivery Platform - User Service")
                                .version("v1")
                                .description("User Management APIs")
                                .contact(
                                        new Contact()
                                                .name("Naveen")
                                                .email("naveen@example.com")
                                )
                );
    }
}