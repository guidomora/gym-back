package com.app.gym.api_gym_app.mapper;

import com.app.gym.api_gym_app.dto.ExerciseDTO;
import com.app.gym.api_gym_app.model.Exercise;
import com.app.gym.api_gym_app.model.Routine;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-18T01:02:20-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251023-0518, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ExerciseMapperImpl implements ExerciseMapper {

    @Override
    public ExerciseDTO toExerciseDTO(Exercise exercise) {
        if ( exercise == null ) {
            return null;
        }

        ExerciseDTO.ExerciseDTOBuilder exerciseDTO = ExerciseDTO.builder();

        exerciseDTO.routineId( exerciseRoutineId( exercise ) );
        exerciseDTO.id( exercise.getId() );
        exerciseDTO.name( exercise.getName() );
        exerciseDTO.repetitions( exercise.getRepetitions() );
        exerciseDTO.restTime( exercise.getRestTime() );
        exerciseDTO.sets( exercise.getSets() );
        exerciseDTO.weightType( exercise.getWeightType() );

        return exerciseDTO.build();
    }

    @Override
    public Exercise toExercise(ExerciseDTO exerciseDTO) {
        if ( exerciseDTO == null ) {
            return null;
        }

        Exercise.ExerciseBuilder exercise = Exercise.builder();

        exercise.routine( exerciseDTOToRoutine( exerciseDTO ) );
        exercise.id( exerciseDTO.getId() );
        exercise.name( exerciseDTO.getName() );
        exercise.repetitions( exerciseDTO.getRepetitions() );
        exercise.restTime( exerciseDTO.getRestTime() );
        exercise.sets( exerciseDTO.getSets() );
        exercise.weightType( exerciseDTO.getWeightType() );

        return exercise.build();
    }

    @Override
    public List<ExerciseDTO> toExerciseDTOs(List<Exercise> exercises) {
        if ( exercises == null ) {
            return null;
        }

        List<ExerciseDTO> list = new ArrayList<ExerciseDTO>( exercises.size() );
        for ( Exercise exercise : exercises ) {
            list.add( toExerciseDTO( exercise ) );
        }

        return list;
    }

    private Long exerciseRoutineId(Exercise exercise) {
        if ( exercise == null ) {
            return null;
        }
        Routine routine = exercise.getRoutine();
        if ( routine == null ) {
            return null;
        }
        Long id = routine.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Routine exerciseDTOToRoutine(ExerciseDTO exerciseDTO) {
        if ( exerciseDTO == null ) {
            return null;
        }

        Routine.RoutineBuilder routine = Routine.builder();

        routine.id( exerciseDTO.getRoutineId() );

        return routine.build();
    }
}
