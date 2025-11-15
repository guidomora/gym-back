package com.app.gym.api_gym_app.configuration;

import com.app.gym.api_gym_app.model.Gym;
import com.app.gym.api_gym_app.repository.GymRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GymDataInitializer {

    private final GymRepository gymRepository;

    private static final List<String> DEFAULT_GYMS = List.of(
            "Super Sport",
            "Gold Gym",
            "Fitter"
    );

    @PostConstruct
    public void initializeGyms() {
        DEFAULT_GYMS.forEach(gymName ->
                gymRepository.findByNombreIgnoreCase(gymName)
                        .orElseGet(() -> gymRepository.save(Gym.builder().nombre(gymName).build()))
        );
    }
}
