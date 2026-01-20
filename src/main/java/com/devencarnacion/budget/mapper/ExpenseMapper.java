package com.devencarnacion.budget.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.devencarnacion.budget.dto.expense.ExpenseCreateRequestDTO;
import com.devencarnacion.budget.dto.expense.ExpenseResponseDTO;
import com.devencarnacion.budget.dto.expense.ExpenseUpdateRequestDTO;
import com.devencarnacion.budget.model.Expense;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    // Entity -> Response
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "categoryId", source = "category.id")
    ExpenseResponseDTO toResponse(Expense expense);

    // Create DTO -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", ignore = true)
    Expense toEntity(ExpenseCreateRequestDTO dto);

    // Update parcial IGNORE NULLS
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updateEntityFromDto(ExpenseUpdateRequestDTO dto, @MappingTarget Expense entity);
}
