package com.devencarnacion.budget.service;

import java.util.List;
import java.util.Map;

import com.devencarnacion.budget.dto.category.CategoryCreateRequestDTO;
import com.devencarnacion.budget.dto.category.CategoryResponseDTO;
import com.devencarnacion.budget.dto.category.CategoryUpdateRequestDTO;
import com.devencarnacion.budget.dto.category.SectionInfoDto;
import com.devencarnacion.budget.enums.category.CategoryTypeEnum;

public interface CategoryService {

    CategoryResponseDTO create(CategoryCreateRequestDTO request);

    List<CategoryResponseDTO> getAllForCurrentUser();

    List<CategoryResponseDTO> getCategoriesByUserId(Long userId);

    CategoryResponseDTO getCategoryById(Long categoryId);

    CategoryResponseDTO update(Long categoryId, CategoryUpdateRequestDTO request);

    void delete(Long id);

    List<SectionInfoDto> getSectionHeaders();

    Map<CategoryTypeEnum, List<CategoryResponseDTO>>getCategoriesGrouped();
}
