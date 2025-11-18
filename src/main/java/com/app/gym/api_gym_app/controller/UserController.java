package com.app.gym.api_gym_app.controller;

import com.app.gym.api_gym_app.dto.UserResponse;
import com.app.gym.api_gym_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;  

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/my-qr-token")
    public ResponseEntity<Map<String, String>> getMyQrToken(Authentication authentication) {
        // 'authentication.getName()' obtiene el email del usuario desde el token JWT
        String userEmail = authentication.getName(); 
        
        String qrToken = userService.getQrTokenByEmail(userEmail);

        // Devuelve un JSON simple: { "qrToken": "el-uuid-va-aqui" }
        return ResponseEntity.ok(Map.of("qrToken", qrToken));
    }

}