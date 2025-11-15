package com.app.gym.api_gym_app.service;

import com.app.gym.api_gym_app.model.Gym;
import com.app.gym.api_gym_app.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GymService {

    private static final Set<String> AVAILABLE_GYMS = Set.of(
            "super sport",
            "gold gym",
            "fitter"
    );

    private final GymRepository gymRepository;

    public Gym getGymByName(String gymName) {
        if (gymName == null || gymName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El gimnasio es obligatorio");
        }

        String normalizedGym = gymName.toLowerCase(Locale.ROOT).trim();
        if (!AVAILABLE_GYMS.contains(normalizedGym)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gimnasio no disponible para registro");
        }

        return gymRepository.findByNombreIgnoreCase(gymName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gimnasio no encontrado"));
    }
}
