package com.devencarnacion.budget.dto.expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.devencarnacion.budget.dto.category.CategoryResponseDTO;

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
public class ExpenseResponseDTO {

    private Long id;

    private String description;

    private BigDecimal amount;

    private LocalDate date;

    private Long userId;

    private CategoryResponseDTO category;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
