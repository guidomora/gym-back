package com.app.gym.api_gym_app.service;

import com.app.gym.api_gym_app.dto.ExerciseDTO;
import com.app.gym.api_gym_app.exception.ResourceNotFoundException;
import com.app.gym.api_gym_app.mapper.ExerciseMapper;
import com.app.gym.api_gym_app.model.Exercise;
import com.app.gym.api_gym_app.model.Routine;
import com.app.gym.api_gym_app.repository.ExerciseRepository;
import com.app.gym.api_gym_app.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private ExerciseMapper exerciseMapper;

    public ExerciseDTO createExercise(ExerciseDTO exerciseDTO) {
        Routine routine = routineRepository.findById(exerciseDTO.getRoutineId())
                .orElseThrow(() -> new ResourceNotFoundException("Rutina no encontrada con id: " + exerciseDTO.getRoutineId()));

        Exercise exercise = exerciseMapper.toExercise(exerciseDTO);
        exercise.setRoutine(routine);

        Exercise savedExercise = exerciseRepository.save(exercise);
        return exerciseMapper.toExerciseDTO(savedExercise);
    }

    @Transactional(readOnly = true)
    public ExerciseDTO getExerciseById(Long id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ejercicio no encontrado con id: " + id));
        return exerciseMapper.toExerciseDTO(exercise);
    }

    @Transactional(readOnly = true)
    public List<ExerciseDTO> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exerciseMapper.toExerciseDTOs(exercises);
    }

    public ExerciseDTO updateExercise(Long id, ExerciseDTO exerciseDTO) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ejercicio no encontrado con id: " + id));

        if (exerciseDTO.getName() != null) {
            exercise.setName(exerciseDTO.getName());
        }
        if (exerciseDTO.getSets() != null) {
            exercise.setSets(exerciseDTO.getSets());
        }
        if (exerciseDTO.getRepetitions() != null) {
            exercise.setRepetitions(exerciseDTO.getRepetitions());
        }
        if (exerciseDTO.getRestTime() != null) {
            exercise.setRestTime(exerciseDTO.getRestTime());
        }
        if (exerciseDTO.getWeightType() != null) {
            exercise.setWeightType(exerciseDTO.getWeightType());
        }
        if (exerciseDTO.getRoutineId() != null) {
            Routine routine = routineRepository.findById(exerciseDTO.getRoutineId())
                    .orElseThrow(() -> new ResourceNotFoundException("Rutina no encontrada con id: " + exerciseDTO.getRoutineId()));
            exercise.setRoutine(routine);
        }

        Exercise updatedExercise = exerciseRepository.save(exercise);
        return exerciseMapper.toExerciseDTO(updatedExercise);
    }

    public void deleteExercise(Long id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ejercicio no encontrado con id: " + id));
        exerciseRepository.delete(exercise);
    }
}