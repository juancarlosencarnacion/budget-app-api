package com.devencarnacion.budget.dto.category;

import java.math.BigDecimal;

import com.devencarnacion.budget.enums.category.CategoryTypeEnum;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
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

    @NotNull(message = "Category type is required")
    private CategoryTypeEnum categoryType;

    @NotNull(message = "Monthly budget is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Monthly budget should be bigger than 0")
    @Digits(integer = 10, fraction = 2, message = "Invalid money format")
    private BigDecimal monthlyBudget;
}
