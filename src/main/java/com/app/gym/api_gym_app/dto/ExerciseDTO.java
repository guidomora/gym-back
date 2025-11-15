package com.app.gym.api_gym_app.dto;

import com.app.gym.api_gym_app.enums.WeightType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {
    private Long id;

    @NotBlank(message = "El nombre del ejercicio es obligatorio")
    private String name;

    @NotNull(message = "Las series son obligatorias")
    @Min(value = 1, message = "Debe haber al menos 1 serie")
    private Integer sets;

    @NotNull(message = "Las repeticiones son obligatorias")
    @Min(value = 1, message = "Debe haber al menos 1 repetición")
    private Integer repetitions;

    @NotNull(message = "El tiempo de descanso es obligatorio")
    @Min(value = 0, message = "El tiempo de descanso no puede ser negativo")
    private Integer restTime;

    @NotNull(message = "El tipo de peso es obligatorio")
    private WeightType weightType;

    @NotNull(message = "El ID de la rutina es obligatorio")
    private Long routineId;
}