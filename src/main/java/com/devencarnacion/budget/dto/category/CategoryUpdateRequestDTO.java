package com.devencarnacion.budget.dto.category;

import java.math.BigDecimal;

import com.devencarnacion.budget.enums.category.CategoryTypeEnum;

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
    private CategoryTypeEnum categoryType;
    private BigDecimal monthlyBudget;
}
