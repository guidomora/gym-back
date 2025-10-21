package com.app.gym.api_gym_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Schema(name = "ErrorResponse", description = "Respuesta de error estándar de la API")
public class ErrorResponseDTO {

    @Schema(description = "Código de estado HTTP", example = "400")
    private String statusCode;

    @Schema(description = "Título del error", example = "Bad Request")
    private String title;

    @Schema(description = "Descripción detallada del error", example = "El parámetro requerido 'id' no fue proporcionado")
    private String detail;
}