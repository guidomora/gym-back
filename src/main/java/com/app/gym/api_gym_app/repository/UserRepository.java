package com.app.gym.api_gym_app.repository;

import com.app.gym.api_gym_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Spring crea esta consulta automáticamente: SELECT * FROM users WHERE email = ?
    Optional<User> findByEmail(String email);
}