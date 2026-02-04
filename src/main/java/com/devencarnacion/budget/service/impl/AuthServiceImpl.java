package com.devencarnacion.budget.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devencarnacion.budget.dto.auth.AuthResponse;
import com.devencarnacion.budget.dto.auth.LoginRequest;
import com.devencarnacion.budget.dto.auth.RegisterRequest;
import com.devencarnacion.budget.enums.user.Role;
import com.devencarnacion.budget.mapper.AuthMapper;
import com.devencarnacion.budget.model.User;
import com.devencarnacion.budget.repository.UserRepository;
import com.devencarnacion.budget.security.UserPrincipal;
import com.devencarnacion.budget.service.AuthService;
import com.devencarnacion.budget.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthMapper authMapper;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getEmail());

        String token = jwtService.getToken(userDetails);

        return AuthResponse.builder()
                .token(token)
                .email(userDetails.getUsername())
                .build();
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        User newUser = authMapper.toEntity(request);

        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(Role.USER);

        User savedUser = userRepository.save(newUser);

        UserDetails userDetails = new UserPrincipal(savedUser);

        String token = jwtService.getToken(userDetails);

        return AuthResponse.builder()
                .token(token)
                .email(userDetails.getUsername())
                .build();
    }

}
