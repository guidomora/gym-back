package com.app.gym.api_gym_app.service;

import com.app.gym.api_gym_app.dto.AuthResponse;
import com.app.gym.api_gym_app.dto.LoginRequest;
import com.app.gym.api_gym_app.dto.RegisterRequest;
import com.app.gym.api_gym_app.dto.UserResponse;
import com.app.gym.api_gym_app.model.User;
import com.app.gym.api_gym_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final GymService gymService;

    public AuthResponse login(LoginRequest request) {
        // Autentica al usuario usando Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        // Si la autenticación fue exitosa, busca el usuario y genera el token
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found after authentication"));
        
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .message("Inicio de sesión exitoso")
                .user(mapToUserResponse(user))
                .token(jwtToken)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        if (request.getPhoneNumber() == null || request.getPhoneNumber().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El número de teléfono es obligatorio");
        }
        if (request.getBirthdate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de nacimiento es obligatoria");
        }

        var gym = gymService.getGymByName(request.getGymName());

        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .phoneNumber(request.getPhoneNumber())
                .birthdate(request.getBirthdate())
                .gym(gym)
                .qrToken(UUID.randomUUID())
                .build();
                

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .message("Usuario registrado exitosamente")
                .user(mapToUserResponse(user))
                .token(jwtToken)
                .build();
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .phoneNumber(user.getPhoneNumber())
                .birthdate(user.getBirthdate())
                .gymName(user.getGym() != null ? user.getGym().getNombre() : null)
                .build();
    }
}
