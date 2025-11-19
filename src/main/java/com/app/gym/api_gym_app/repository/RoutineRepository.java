package com.app.gym.api_gym_app.repository;

import com.app.gym.api_gym_app.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    @Query("SELECT r FROM Routine r WHERE r.student.id = :studentId")
    List<Routine> findByStudentId(Long studentId);

}
