package com.devencarnacion.budget.dto.budgetSummary;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BudgetSpentProjection {
    Long categoryId;
    BigDecimal total;
}
