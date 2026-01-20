package com.devencarnacion.budget.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devencarnacion.budget.dto.expense.ExpenseCreateRequestDTO;
import com.devencarnacion.budget.dto.expense.ExpenseResponseDTO;
import com.devencarnacion.budget.dto.expense.ExpenseUpdateRequestDTO;
import com.devencarnacion.budget.service.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> create(@Valid @RequestBody ExpenseCreateRequestDTO request) {
        return new ResponseEntity<>(expenseService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> getAll() {
        return new ResponseEntity<>(expenseService.getAllForAdmin(), HttpStatus.OK);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable Long expenseId){
        return ResponseEntity.ok(expenseService.getById(expenseId));
    }

    @PatchMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponseDTO> update(@PathVariable Long expenseId, @Valid @RequestBody ExpenseUpdateRequestDTO request) {
        return ResponseEntity.ok(expenseService.update(expenseId, request));
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> delete(@PathVariable Long expenseId) {
        expenseService.delete(expenseId);
        return ResponseEntity.noContent().build();
    }
}