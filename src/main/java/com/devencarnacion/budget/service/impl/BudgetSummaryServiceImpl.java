package com.devencarnacion.budget.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devencarnacion.budget.dto.budgetSummary.BudgetSummaryDto;
import com.devencarnacion.budget.model.Category;
import com.devencarnacion.budget.repository.CategoryRepository;
import com.devencarnacion.budget.repository.ExpenseRepository;
import com.devencarnacion.budget.service.BudgetSummaryService;
import com.devencarnacion.budget.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudgetSummaryServiceImpl implements BudgetSummaryService {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;
    private final SecurityUtils securityUtils;

    @Override
    public List<BudgetSummaryDto> getSummaryByUser() {
        Long currentUserId = securityUtils.getCurrentUser().getId();

        List<Category> categories = categoryRepository.findByUserId(currentUserId);

        return categories.stream().map(category -> {

            BigDecimal spent = expenseRepository
                    .sumByCategoryAndUser(category.getId(), currentUserId);

            if (spent == null) {
                spent = BigDecimal.ZERO;
            }

            BigDecimal budget = category.getMonthlyBudget() != null
                    ? category.getMonthlyBudget()
                    : BigDecimal.ZERO;

            BigDecimal remaining = budget.subtract(spent);

            double percentage = budget.compareTo(BigDecimal.ZERO) > 0
                    ? spent.divide(budget, 2, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100))
                            .doubleValue()
                    : 0;

            return new BudgetSummaryDto(
                    category.getId(),
                    category.getName(),
                    category.getCategoryType(),
                    budget,
                    spent,
                    remaining,
                    percentage);

        }).toList();
    }

}
