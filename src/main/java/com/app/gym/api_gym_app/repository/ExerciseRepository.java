package com.app.gym.api_gym_app.repository;

import com.app.gym.api_gym_app.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
