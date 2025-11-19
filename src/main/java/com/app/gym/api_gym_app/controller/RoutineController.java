package com.app.gym.api_gym_app.controller;

import com.app.gym.api_gym_app.dto.RoutineDTO;
import com.app.gym.api_gym_app.service.RoutineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routines")
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @PostMapping
    public ResponseEntity<RoutineDTO> createRoutine(@Valid @RequestBody RoutineDTO routineDTO) {
        RoutineDTO createdRoutine = routineService.createRoutine(routineDTO);
        return new ResponseEntity<>(createdRoutine, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutineDTO> getRoutineById(@PathVariable Long id) {
        return ResponseEntity.ok(routineService.getRoutineById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RoutineDTO>> getRoutineByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(routineService.getRoutineByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<RoutineDTO>> getAllRoutines() {
        return ResponseEntity.ok(routineService.getAllRoutines());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RoutineDTO> updateRoutine(@PathVariable Long id, @Valid @RequestBody RoutineDTO routineDTO) {
        return ResponseEntity.ok(routineService.updateRoutine(id, routineDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return ResponseEntity.noContent().build();
    }
}