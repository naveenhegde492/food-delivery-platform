package com.fooddelivery.userservice.controller;

import com.fooddelivery.userservice.entity.User;
import com.fooddelivery.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {

        userRepository.deleteAll();
    }

    @Test
    void shouldCreateUserSuccessfully() throws Exception {

        String requestBody = """
                {
                    "name": "Naveen",
                    "email": "naveen@gmail.com"
                }
                """;

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name").value("Naveen"))
                .andExpect(jsonPath("$.data.email").value("naveen@gmail.com"));
    }

    @Test
    void shouldReturnBadRequestForInvalidEmail() throws Exception {

        String requestBody = """
                {
                    "name": "Naveen",
                    "email": "invalid-email"
                }
                """;

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnConflictForDuplicateEmail() throws Exception {

        User user = User.builder()
                .name("Existing User")
                .email("duplicate@gmail.com")
                .build();

        userRepository.save(user);

        String requestBody = """
                {
                    "name": "New User",
                    "email": "duplicate@gmail.com"
                }
                """;

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isConflict());
    }

    @Test
    void shouldFetchUserSuccessfully() throws Exception {

        User user = User.builder()
                .name("Naveen")
                .email("naveen@gmail.com")
                .build();

        User savedUser = userRepository.save(user);

        mockMvc.perform(get("/api/v1/users/" + savedUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Naveen"))
                .andExpect(jsonPath("$.data.email").value("naveen@gmail.com"));
    }

    @Test
    void shouldReturnNotFoundForMissingUser() throws Exception {

        mockMvc.perform(get("/api/v1/users/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateUserSuccessfully() throws Exception {

        User user = User.builder()
                .name("Old Name")
                .email("old@gmail.com")
                .build();

        User savedUser = userRepository.save(user);

        String requestBody = """
                {
                    "name": "Updated Name",
                    "email": "updated@gmail.com"
                }
                """;

        mockMvc.perform(put("/api/v1/users/" + savedUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Updated Name"))
                .andExpect(jsonPath("$.data.email").value("updated@gmail.com"));
    }

    @Test
    void shouldReturnConflictWhileUpdatingDuplicateEmail() throws Exception {

        User user1 = User.builder()
                .name("User One")
                .email("user1@gmail.com")
                .build();

        User user2 = User.builder()
                .name("User Two")
                .email("user2@gmail.com")
                .build();

        User savedUser1 = userRepository.save(user1);

        userRepository.save(user2);

        String requestBody = """
                {
                    "name": "Updated User",
                    "email": "user2@gmail.com"
                }
                """;

        mockMvc.perform(put("/api/v1/users/" + savedUser1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isConflict());
    }

    @Test
    void shouldReturnNotFoundWhileUpdatingMissingUser() throws Exception {

        String requestBody = """
                {
                    "name": "Updated User",
                    "email": "updated@gmail.com"
                }
                """;

        mockMvc.perform(put("/api/v1/users/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteUserSuccessfully() throws Exception {

        User user = User.builder()
                .name("Delete User")
                .email("delete@gmail.com")
                .build();

        User savedUser = userRepository.save(user);

        mockMvc.perform(delete("/api/v1/users/" + savedUser.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundWhileDeletingMissingUser() throws Exception {

        mockMvc.perform(delete("/api/v1/users/999"))
                .andExpect(status().isNotFound());
    }
}