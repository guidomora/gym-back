package com.app.gym.api_gym_app.mapper;

import com.app.gym.api_gym_app.dto.RoutineDTO;
import com.app.gym.api_gym_app.model.Routine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoutineMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "exercises", target = "exerciseIds")
    RoutineDTO toRoutineDTO(Routine routine);

    @Mapping(source = "studentId", target = "student.id")
    @Mapping(target = "exercises", ignore = true)
    Routine toRoutine(RoutineDTO routineDTO);

    List<RoutineDTO> toRoutineDTOs(List<Routine> routines);

    default Long mapExerciseToId(com.app.gym.api_gym_app.model.Exercise exercise) {
        return exercise != null ? exercise.getId() : null;
    }
}