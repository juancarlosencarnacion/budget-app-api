package com.devencarnacion.budget.dto.category;

import java.math.BigDecimal;

import com.devencarnacion.budget.enums.CategoryType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryCreateRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    private CategoryType categoryType;
    
    @NotNull(message = "Monthly budget is required")
    private BigDecimal monthlyBudget;
    
    @NotNull(message = "User id is required")
    private Long userId;
}
