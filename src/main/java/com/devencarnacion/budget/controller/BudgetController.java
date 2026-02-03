package com.devencarnacion.budget.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devencarnacion.budget.dto.budgetSummary.BudgetSummaryDto;
import com.devencarnacion.budget.service.BudgetSummaryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetSummaryService budgetSummaryService;
    @GetMapping("/summary")
    public ResponseEntity<List<BudgetSummaryDto>> getSummary() {
        return ResponseEntity.ok(budgetSummaryService.getSummaryByUser());
    }
}
