package com.app.gym.api_gym_app.service;

import com.app.gym.api_gym_app.dto.UserResponse;
import com.app.gym.api_gym_app.exception.ResourceAlreadyUsed;
import com.app.gym.api_gym_app.exception.ResourceNotFoundException;
import com.app.gym.api_gym_app.mapper.UserMapper;
import com.app.gym.api_gym_app.model.Membership;
import com.app.gym.api_gym_app.model.User;
import com.app.gym.api_gym_app.repository.MembershipRepository;
import com.app.gym.api_gym_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MembershipRepository membershipRepository;

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

    public UserResponse linkMembership(String membershipKey, User user) {
        Membership membership = membershipRepository.findByKey(membershipKey)
            .orElseThrow(() -> new ResourceNotFoundException("Membresia no encontrada con key: " + membershipKey));
        if (membership.getExpirationDate()!=null) {
            membership.setExpirationDate(LocalDate.now().plusDays(30));
            user.setMembership(membership);
            userRepository.save(user);
            return userMapper.toUserDTO(user);
        } else {
            throw new ResourceAlreadyUsed("La membresia ya esta asociada a un usuario existente");
        }
    }

}