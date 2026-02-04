package com.devencarnacion.budget.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devencarnacion.budget.dto.user.UserCreateRequestDTO;
import com.devencarnacion.budget.dto.user.UserResponseDTO;
import com.devencarnacion.budget.dto.user.UserUpdateRequestDTO;
import com.devencarnacion.budget.mapper.UserMapper;
import com.devencarnacion.budget.model.User;
import com.devencarnacion.budget.repository.UserRepository;
import com.devencarnacion.budget.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO create(UserCreateRequestDTO request) {
        User newUser = userMapper.toEntity(request);
        
        return userMapper.toResponse(userRepository.save(newUser));
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();        
    }

    @Override
    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponseDTO update(Long id, UserUpdateRequestDTO request) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userMapper.updateEntityFromDto(request, user);
        
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id " + id);
        }

        userRepository.deleteById(id);
    }
}
