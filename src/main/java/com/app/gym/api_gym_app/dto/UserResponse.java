package com.app.gym.api_gym_app.dto;

import com.app.gym.api_gym_app.enums.UserRole;
import com.app.gym.api_gym_app.model.Membership;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private UserRole role;
    private String phoneNumber;
    private LocalDate birthdate;
    private String gymName;
    private Membership membership;
}
