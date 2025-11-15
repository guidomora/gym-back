package com.app.gym.api_gym_app.repository;

import com.app.gym.api_gym_app.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym, Long> {
    Optional<Gym> findByNombreIgnoreCase(String nombre);
}
