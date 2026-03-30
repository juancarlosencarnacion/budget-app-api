package com.devencarnacion.budget.dto.category;

import java.math.BigDecimal;
import com.devencarnacion.budget.enums.category.CategoryTypeEnum;

public record SectionInfoDto(
        CategoryTypeEnum categoryType,
        Long categoriesAmount,
        BigDecimal totalBudget) {
}
