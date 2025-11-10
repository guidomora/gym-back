package com.app.gym.api_gym_app.service;

import com.app.gym.api_gym_app.dto.AuthResponse;
import com.app.gym.api_gym_app.dto.LoginRequest;
import com.app.gym.api_gym_app.dto.RegisterRequest;
import com.app.gym.api_gym_app.model.User;
import com.app.gym.api_gym_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

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
        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse register(RegisterRequest request) {
        // Crea el objeto User a partir del DTO
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Hasheamos la contraseña
                .role(request.getRole())
                .build();
        
        // Guarda el usuario en la base de datos
        userRepository.save(user);
        
        // Genera el token para el nuevo usuario
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
}