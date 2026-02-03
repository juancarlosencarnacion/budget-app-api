package com.devencarnacion.budget.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;

import com.devencarnacion.budget.dto.expense.ExpenseCreateRequestDTO;
import com.devencarnacion.budget.dto.expense.ExpenseResponseDTO;
import com.devencarnacion.budget.dto.expense.ExpenseUpdateRequestDTO;
import com.devencarnacion.budget.exception.ResourceNotFoundException;
import com.devencarnacion.budget.mapper.ExpenseMapper;
import com.devencarnacion.budget.model.Expense;
import com.devencarnacion.budget.model.User;
import com.devencarnacion.budget.model.Category;
import com.devencarnacion.budget.repository.CategoryRepository;
import com.devencarnacion.budget.repository.ExpenseRepository;
import com.devencarnacion.budget.service.ExpenseService;
import com.devencarnacion.budget.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    private final ExpenseMapper expenseMapper;

    private final SecurityUtils securityUtils;

    @Transactional
    @Override
    public ExpenseResponseDTO create(ExpenseCreateRequestDTO request) {
        User currentUser = securityUtils.getCurrentUser();

        Category category = categoryRepository
                .findByIdAndUserId(request.getCategoryId(), currentUser.getId())
                .orElseThrow(() -> new AccessDeniedException("Invalid category"));

        Expense newExpense = expenseMapper.toEntity(request);
        newExpense.setUser(currentUser);
        newExpense.setCategory(category);

        return expenseMapper.toResponse(expenseRepository.save(newExpense));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<ExpenseResponseDTO> getAllForAdmin() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseMapper::toResponse)
                .toList();
    }

    @Override
    public List<ExpenseResponseDTO> getExpensesByUser() {
        User user = securityUtils.getCurrentUser();
        return expenseRepository.findExpensesByUserId(user.getId())
                .stream()
                .map(expenseMapper::toResponse)
                .toList();
    }

    @Override
    public ExpenseResponseDTO getById(Long id) {
        User user = securityUtils.getCurrentUser();

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));

        if (!expense.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("You are not the owner of this expense");
        }

        return expenseMapper.toResponse(expense);
    }

    @Override
    public ExpenseResponseDTO update(Long id, ExpenseUpdateRequestDTO request) {
        User user = securityUtils.getCurrentUser();

        Expense expense = expenseRepository
                .findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new AccessDeniedException("You are not the owner of this expense"));

        expenseMapper.updateEntityFromDto(request, expense);

        return expenseMapper.toResponse(expenseRepository.save(expense));
    }

    @Override
    public void delete(Long id) {
        User user = securityUtils.getCurrentUser();

        Expense expense = expenseRepository
                .findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new AccessDeniedException("You are not the owner of this expense"));

        expenseRepository.delete(expense);
    }

}
