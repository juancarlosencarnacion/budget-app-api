package com.devencarnacion.budget.service;

import java.util.List;

import com.devencarnacion.budget.dto.budgetSummary.BudgetSummaryDto;

public interface BudgetSummaryService {
    List<BudgetSummaryDto> getSummaryByUser();
}
