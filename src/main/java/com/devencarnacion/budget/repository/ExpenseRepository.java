package com.devencarnacion.budget.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devencarnacion.budget.dto.budgetSummary.BudgetSpentProjection;
import com.devencarnacion.budget.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findExpensesByUserId(Long userId);

    Optional<Expense> findByIdAndUserId(Long id, Long userId);

    @Query("""
                SELECT COALESCE(SUM(e.amount), 0)
                FROM Expense e
                WHERE e.category.id = :categoryId
                  AND e.user.id = :userId
            """)
    BigDecimal sumByCategoryAndUser(
            @Param("categoryId") Long categoryId,
            @Param("userId") Long userId);

    @Query("""
            SELECT new com.devencarnacion.budget.dto.budgetSummary.BudgetSpentProjection(
                e.category.id,
                COALESCE(SUM(e.amount), 0)
            )
            FROM Expense e
            WHERE e.user.id = :userId
            GROUP BY e.category.id
            """)
    List<BudgetSpentProjection> sumByUserGrouped(@Param("userId") Long userId);

}
