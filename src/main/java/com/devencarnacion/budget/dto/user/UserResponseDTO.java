package com.devencarnacion.budget.dto.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDTO {

    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
