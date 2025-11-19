package com.app.gym.api_gym_app.service;

import com.app.gym.api_gym_app.dto.RoutineDTO;
import com.app.gym.api_gym_app.exception.ResourceNotFoundException;
import com.app.gym.api_gym_app.mapper.RoutineMapper;
import com.app.gym.api_gym_app.model.Routine;
import com.app.gym.api_gym_app.model.User;
import com.app.gym.api_gym_app.repository.RoutineRepository;
import com.app.gym.api_gym_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoutineService {

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoutineMapper routineMapper;

    public RoutineDTO createRoutine(RoutineDTO routineDTO) {
        User student = userRepository.findById(routineDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + routineDTO.getStudentId()));

        Routine routine = routineMapper.toRoutine(routineDTO);
        routine.setStudent(student);

        Routine savedRoutine = routineRepository.save(routine);
        return routineMapper.toRoutineDTO(savedRoutine);
    }

    @Transactional(readOnly = true)
    public RoutineDTO getRoutineById(Long id) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina no encontrada con id: " + id));
        return routineMapper.toRoutineDTO(routine);
    }

    @Transactional(readOnly = true)
    public List<RoutineDTO> getRoutineByUserId(Long userId) {
        List<Routine> routines = routineRepository.findByStudentId(userId);
        return routineMapper.toRoutineDTOs(routines);
    }

    @Transactional(readOnly = true)
    public List<RoutineDTO> getAllRoutines() {
        List<Routine> routines = routineRepository.findAll();
        return routineMapper.toRoutineDTOs(routines);
    }

    public RoutineDTO updateRoutine(Long id, RoutineDTO routineDTO) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina no encontrada con id: " + id));

        if (routineDTO.getName() != null) {
            routine.setName(routineDTO.getName());
        }
        if (routineDTO.getDayOfWeek() != null) {
            routine.setDayOfWeek(routineDTO.getDayOfWeek());
        }
        if (routineDTO.getDate() != null) {
            routine.setDate(routineDTO.getDate());
        }
        if (routineDTO.getStudentId() != null) {
            User student = userRepository.findById(routineDTO.getStudentId())
                    .orElseThrow(() -> new com.app.gym.api_gym_app.exception.ResourceNotFoundException("Usuario no encontrado con id: " + routineDTO.getStudentId()));
            routine.setStudent(student);
        }

        Routine updatedRoutine = routineRepository.save(routine);
        return routineMapper.toRoutineDTO(updatedRoutine);
    }

    public void deleteRoutine(Long id) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina no encontrada con id: " + id));
        routineRepository.delete(routine);
    }
}