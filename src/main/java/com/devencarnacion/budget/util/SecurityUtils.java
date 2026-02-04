package com.devencarnacion.budget.util;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;

import com.devencarnacion.budget.model.User;

import com.devencarnacion.budget.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    public User getCurrentUser() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (!(principal instanceof UserPrincipal userPrincipal)) {
            throw new RuntimeException("User not authenticated");
        }

        return userPrincipal.getUser();
    }
}
