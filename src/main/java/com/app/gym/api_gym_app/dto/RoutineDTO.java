package com.app.gym.api_gym_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoutineDTO {
    private Long id;

    @NotBlank(message = "El nombre de la rutina es obligatorio")
    private String name;

    @NotNull(message = "El día de la semana es obligatorio")
    private DayOfWeek dayOfWeek;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate date;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long studentId;

    // IDs de los ejercicios asociados (opcional para la creación)
    private Set<Long> exerciseIds;
}