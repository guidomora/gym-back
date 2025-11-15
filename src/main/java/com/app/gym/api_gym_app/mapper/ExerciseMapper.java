package com.app.gym.api_gym_app.mapper;

import com.app.gym.api_gym_app.dto.ExerciseDTO;
import com.app.gym.api_gym_app.model.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExerciseMapper {

    @Mapping(source = "routine.id", target = "routineId")
    ExerciseDTO toExerciseDTO(Exercise exercise);

    @Mapping(source = "routineId", target = "routine.id")
    Exercise toExercise(ExerciseDTO exerciseDTO);

    List<ExerciseDTO> toExerciseDTOs(List<Exercise> exercises);
}