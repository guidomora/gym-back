package com.app.gym.api_gym_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GymDTO {
    private Long id;

    @NotBlank(message = "El nombre del gimnasio es obligatorio")
    private String nombre;

    // Opcional: IDs de los entrenadores, o DTOs de entrenadores
    // private Set<Long> entrenadorIds;
}
