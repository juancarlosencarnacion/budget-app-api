package com.devencarnacion.budget.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devencarnacion.budget.dto.category.CategoryCreateRequestDTO;
import com.devencarnacion.budget.dto.category.CategoryResponseDTO;
import com.devencarnacion.budget.dto.category.CategoryUpdateRequestDTO;
import com.devencarnacion.budget.dto.category.SectionInfoDto;
import com.devencarnacion.budget.enums.category.CategoryTypeEnum;
import com.devencarnacion.budget.exception.DuplicateCategoryException;
import com.devencarnacion.budget.exception.ResourceNotFoundException;
import com.devencarnacion.budget.mapper.CategoryMapper;
import com.devencarnacion.budget.model.Category;
import com.devencarnacion.budget.model.User;
import com.devencarnacion.budget.repository.CategoryRepository;
import com.devencarnacion.budget.service.CategoryService;
import com.devencarnacion.budget.util.SecurityUtils;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private final SecurityUtils securityUtils;

    @Override
    public CategoryResponseDTO create(CategoryCreateRequestDTO request) {
        Category newCategory = categoryMapper.toEntity(request);
        User user = securityUtils.getCurrentUser();

        if (categoryRepository.existsByNameAndUserId(request.getName(), user.getId())) {
            throw new DuplicateCategoryException("Category already exists");
        }

        newCategory.setUser(user);

        return categoryMapper.toResponse(categoryRepository.save(newCategory));
    }

    @Override
    public List<CategoryResponseDTO> getAllForCurrentUser() {
        return categoryRepository.findByUserId(getCurrentUserId())
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<CategoryResponseDTO> getCategoriesByUserId(Long userId) {
        return categoryRepository.findByUserId(userId)
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long categoryId) {
        Category category = categoryRepository.findByIdAndUserId(getCurrentUserId(), categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        return categoryMapper.toResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponseDTO update(Long categoryId, CategoryUpdateRequestDTO request) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        categoryMapper.updateEntityFromDto(request, category);

        Category saved = categoryRepository.save(category);

        return categoryMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long categoryId) {
        if (!categoryRepository.existsByIdAndUserId(categoryId, getCurrentUserId())) {
            throw new EntityNotFoundException("Category wasn't found with id: " + categoryId);
        }

        categoryRepository.deleteByIdAndUserId(categoryId, getCurrentUserId());
    }

    // Section headers
    @Override
    public List<SectionInfoDto> getSectionHeaders() {
        return categoryRepository.getSectionSummaryByUser(getCurrentUserId());
    }

    @Override
    public Map<CategoryTypeEnum, List<CategoryResponseDTO>> getCategoriesGrouped() {
        return categoryRepository.findByUserId(getCurrentUserId())
                .stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.groupingBy(CategoryResponseDTO::getCategoryType));
    }

    // Get id from authenticated user
    private Long getCurrentUserId() { 
        return securityUtils.getCurrentUser().getId();
    }
}
