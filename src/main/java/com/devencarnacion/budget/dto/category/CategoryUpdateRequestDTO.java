package com.devencarnacion.budget.dto.category;

import java.math.BigDecimal;

import com.devencarnacion.budget.enums.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryUpdateRequestDTO {
    private String name;
    private CategoryType categoryType;
    private BigDecimal monthlyBudget;
}
