package com.devencarnacion.budget.dto.auth;

import org.springframework.security.core.userdetails.UserDetails;

import com.devencarnacion.budget.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String token;
    User user;
}
