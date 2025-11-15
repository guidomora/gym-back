package com.app.gym.api_gym_app.service;

import com.app.gym.api_gym_app.dto.GymDTO;
import com.app.gym.api_gym_app.mapper.GymMapper;
import com.app.gym.api_gym_app.model.Gym;
import com.app.gym.api_gym_app.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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
    private final GymMapper gymMapper;

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

    public GymDTO createGym(GymDTO gymRequest) {
        Gym gym = gymMapper.toGym(gymRequest);
        return gymMapper.toGymDTO(gymRepository.save(gym));
    }

    public GymDTO getGymById(Long id) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gimnasio no encontrado"));

        return gymMapper.toGymDTO(gym);
    }

    public List<GymDTO> getAllGyms() {
        return gymMapper.toGymDTOs(gymRepository.findAll());
    }

    public GymDTO updateGym(Long id, GymDTO gymRequest) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gimnasio no encontrado"));
        gym.setNombre(gymRequest.getNombre());
        return gymMapper.toGymDTO(gymRepository.save(gym));
    }

    public void deleteGym(Long id) {
        gymRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gimnasio no encontrado"));
        gymRepository.deleteById(id);
    }
}
