package com.devencarnacion.budget.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devencarnacion.budget.dto.expense.ExpenseCreateRequestDTO;
import com.devencarnacion.budget.dto.expense.ExpenseResponseDTO;
import com.devencarnacion.budget.dto.expense.ExpenseUpdateRequestDTO;
import com.devencarnacion.budget.mapper.ExpenseMapper;
import com.devencarnacion.budget.model.Expense;
import com.devencarnacion.budget.repository.CategoryRepository;
import com.devencarnacion.budget.repository.ExpenseRepository;
import com.devencarnacion.budget.repository.UserRepository;
import com.devencarnacion.budget.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private final ExpenseMapper expenseMapper;

    @Override
    public ExpenseResponseDTO create(ExpenseCreateRequestDTO request) {
        Expense newExpense = expenseMapper.toEntity(request);

        newExpense.setUser(userRepository.getReferenceById(request.getUserId()));
        newExpense.setCategory(categoryRepository.getReferenceById(request.getCategoryId()));

        return expenseMapper.toResponse(expenseRepository.save(newExpense));
    }

    @Override
    public List<ExpenseResponseDTO> getAllForAdmin() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseMapper::toResponse)
                .toList();
    }

    @Override
    public List<ExpenseResponseDTO> getExpensesByUser(Long userId) {
        return expenseRepository.findExpensesByUserId(userId)
                .stream()
                .map(expenseMapper::toResponse)
                .toList();
    }

    @Override
    public ExpenseResponseDTO getById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        return expenseMapper.toResponse(expense);
    }

    @Override
    public ExpenseResponseDTO update(Long id, ExpenseUpdateRequestDTO request) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        expenseMapper.updateEntityFromDto(request, expense);

        return expenseMapper.toResponse(expenseRepository.save(expense));
    }

    @Override
    public void delete(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Expense not found with id " + id);
        }

        expenseRepository.deleteById(id);
    }

}
