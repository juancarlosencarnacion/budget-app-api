package com.devencarnacion.budget.dto.budgetSummary;

import java.math.BigDecimal;

import com.devencarnacion.budget.enums.category.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BudgetSummaryDto {

    private Long categoryId;
    private String categoryName;
    private CategoryType categoryType;

    private BigDecimal budgetAssigned;
    private BigDecimal totalSpent;
    private BigDecimal remaining;
    private Double percentageUsed;
}
