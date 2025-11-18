package com.app.gym.api_gym_app.mapper;

import com.app.gym.api_gym_app.dto.RoutineDTO;
import com.app.gym.api_gym_app.model.Exercise;
import com.app.gym.api_gym_app.model.Routine;
import com.app.gym.api_gym_app.model.User;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-18T01:03:25-0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class RoutineMapperImpl implements RoutineMapper {

    @Override
    public RoutineDTO toRoutineDTO(Routine routine) {
        if ( routine == null ) {
            return null;
        }

        RoutineDTO.RoutineDTOBuilder routineDTO = RoutineDTO.builder();

        routineDTO.studentId( routineStudentId( routine ) );
        routineDTO.exerciseIds( exerciseSetToLongSet( routine.getExercises() ) );
        routineDTO.id( routine.getId() );
        routineDTO.name( routine.getName() );
        routineDTO.dayOfWeek( routine.getDayOfWeek() );
        routineDTO.date( routine.getDate() );

        return routineDTO.build();
    }

    @Override
    public Routine toRoutine(RoutineDTO routineDTO) {
        if ( routineDTO == null ) {
            return null;
        }

        Routine.RoutineBuilder routine = Routine.builder();

        routine.student( routineDTOToUser( routineDTO ) );
        routine.id( routineDTO.getId() );
        routine.name( routineDTO.getName() );
        routine.dayOfWeek( routineDTO.getDayOfWeek() );
        routine.date( routineDTO.getDate() );

        return routine.build();
    }

    @Override
    public List<RoutineDTO> toRoutineDTOs(List<Routine> routines) {
        if ( routines == null ) {
            return null;
        }

        List<RoutineDTO> list = new ArrayList<RoutineDTO>( routines.size() );
        for ( Routine routine : routines ) {
            list.add( toRoutineDTO( routine ) );
        }

        return list;
    }

    private Long routineStudentId(Routine routine) {
        if ( routine == null ) {
            return null;
        }
        User student = routine.getStudent();
        if ( student == null ) {
            return null;
        }
        Long id = student.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<Long> exerciseSetToLongSet(Set<Exercise> set) {
        if ( set == null ) {
            return null;
        }

        Set<Long> set1 = new LinkedHashSet<Long>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Exercise exercise : set ) {
            set1.add( mapExerciseToId( exercise ) );
        }

        return set1;
    }

    protected User routineDTOToUser(RoutineDTO routineDTO) {
        if ( routineDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( routineDTO.getStudentId() );

        return user.build();
    }
}
