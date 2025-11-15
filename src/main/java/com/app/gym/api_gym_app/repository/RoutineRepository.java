package com.app.gym.api_gym_app.repository;

import com.app.gym.api_gym_app.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
}
