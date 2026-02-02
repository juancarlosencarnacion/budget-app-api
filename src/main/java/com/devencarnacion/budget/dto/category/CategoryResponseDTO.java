package com.devencarnacion.budget.dto.category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.devencarnacion.budget.enums.category.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponseDTO {

    private Long id;

    private String name;

    private CategoryType categoryType;

    private BigDecimal monthlyBudget;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
