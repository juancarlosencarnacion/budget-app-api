package com.devencarnacion.budget.service;

import java.util.List;

import com.devencarnacion.budget.dto.expense.ExpenseCreateRequestDTO;
import com.devencarnacion.budget.dto.expense.ExpenseResponseDTO;
import com.devencarnacion.budget.dto.expense.ExpenseUpdateRequestDTO;



public interface ExpenseService {

    ExpenseResponseDTO create(ExpenseCreateRequestDTO request);

    List<ExpenseResponseDTO> getAllForAdmin();

    List<ExpenseResponseDTO> getExpensesByUser();

    ExpenseResponseDTO getById(Long id);

    ExpenseResponseDTO update(Long id, ExpenseUpdateRequestDTO request);

    void delete(Long id);
}
