package com.devencarnacion.budget.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.devencarnacion.budget.model.User;
import com.devencarnacion.budget.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if (!(principal instanceof UserDetails userDetails)) {
            throw new RuntimeException("User not authenticated");
        }

        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow();
    }
}
