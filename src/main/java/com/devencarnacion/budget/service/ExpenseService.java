package com.devencarnacion.budget.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    Map<LocalDate, List<ExpenseResponseDTO>> getRecordsByDate();
}
