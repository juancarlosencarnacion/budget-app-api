package com.devencarnacion.budget.service;

import com.devencarnacion.budget.dto.auth.AuthResponse;
import com.devencarnacion.budget.dto.auth.LoginRequest;
import com.devencarnacion.budget.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
}
