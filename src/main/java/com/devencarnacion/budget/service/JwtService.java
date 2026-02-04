package com.devencarnacion.budget.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String getToken(UserDetails user);

    String getSubjectFromToken(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
