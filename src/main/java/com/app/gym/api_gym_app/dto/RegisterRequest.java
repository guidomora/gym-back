package com.app.gym.api_gym_app.dto;

import com.app.gym.api_gym_app.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private UserRole role; // El frontend enviará TRAINER o STUDENT
}