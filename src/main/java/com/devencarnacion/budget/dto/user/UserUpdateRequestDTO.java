package com.devencarnacion.budget.dto.user;

import com.devencarnacion.budget.enums.user.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDTO {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Role role;
}
