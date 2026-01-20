package com.devencarnacion.budget.service;

import java.util.List;

import com.devencarnacion.budget.dto.user.UserCreateRequestDTO;
import com.devencarnacion.budget.dto.user.UserResponseDTO;
import com.devencarnacion.budget.dto.user.UserUpdateRequestDTO;

public interface UserService {

    UserResponseDTO create(UserCreateRequestDTO request);

    List<UserResponseDTO> getAll();

    UserResponseDTO getById(Long id);

    UserResponseDTO update(Long id, UserUpdateRequestDTO request);

    void delete(Long id);
}
