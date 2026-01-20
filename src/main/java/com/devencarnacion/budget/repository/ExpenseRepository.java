package com.devencarnacion.budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devencarnacion.budget.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
    List<Expense> findExpensesByUserId(Long userId);
}
