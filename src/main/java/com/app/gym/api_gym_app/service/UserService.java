package com.app.gym.api_gym_app.service;

import com.app.gym.api_gym_app.dto.UserResponse;
import com.app.gym.api_gym_app.exception.ResourceNotFoundException;
import com.app.gym.api_gym_app.mapper.UserMapper;
import com.app.gym.api_gym_app.model.User;
import com.app.gym.api_gym_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ejercicio no encontrado con id: " + id));
        return userMapper.toUserDTO(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDTOs(users);
    }

}