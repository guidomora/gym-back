package com.app.gym.api_gym_app.service;

import com.app.gym.api_gym_app.dto.UpdateUserRequest;
import com.app.gym.api_gym_app.dto.UserResponse;
import com.app.gym.api_gym_app.exception.ResourceNotFoundException;
import com.app.gym.api_gym_app.mapper.UserMapper;
import com.app.gym.api_gym_app.model.User;
import com.app.gym.api_gym_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID; 
import lombok.NonNull;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserResponse getUserById(@NonNull Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        return userMapper.toUserDTO(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDTOs(users);
    }

    @Transactional
    public String getQrTokenByEmail(String email) {
        
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con email: " + email));

        UUID qrToken = user.getQrToken();

        if (qrToken == null) {
            qrToken = UUID.randomUUID();
            user.setQrToken(qrToken);
            userRepository.save(user); 
        }

        return qrToken.toString();
    }

    @SuppressWarnings("null")
    public UserResponse updateUser(@NonNull Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getBirthdate() != null) {
            user.setBirthdate(request.getBirthdate());
        }

        User updatedUser = userRepository.save(user);
        return userMapper.toUserDTO(updatedUser);
    }
}