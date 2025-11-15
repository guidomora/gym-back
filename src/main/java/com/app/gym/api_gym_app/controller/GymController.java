package com.app.gym.api_gym_app.controller;

import com.app.gym.api_gym_app.dto.GymDTO;
import com.app.gym.api_gym_app.service.GymService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gyms")
public class GymController {

    @Autowired
    private GymService gymService;

    @PostMapping
    public ResponseEntity<GymDTO> createGym(@Valid @RequestBody GymDTO gymDTO) {
        GymDTO createdGym = gymService.createGym(gymDTO);
        return new ResponseEntity<>(createdGym, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymDTO> getGymById(@PathVariable Long id) {
        return ResponseEntity.ok(gymService.getGymById(id));
    }

    @GetMapping
    public ResponseEntity<List<GymDTO>> getAllGyms() {
        return ResponseEntity.ok(gymService.getAllGyms());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GymDTO> updateGym(@PathVariable Long id, @Valid @RequestBody GymDTO gymDTO) {
        return ResponseEntity.ok(gymService.updateGym(id, gymDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGym(@PathVariable Long id) {
        gymService.deleteGym(id);
        return ResponseEntity.noContent().build();
    }
}