package com.devencarnacion.budget.dto.expense;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpenseUpdateRequestDTO {
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private Long categoryId;
    private Long userId;
}
