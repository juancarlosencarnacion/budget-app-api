package com.devencarnacion.budget.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devencarnacion.budget.dto.category.CategoryCreateRequestDTO;
import com.devencarnacion.budget.dto.category.CategoryResponseDTO;
import com.devencarnacion.budget.dto.category.CategoryUpdateRequestDTO;
import com.devencarnacion.budget.mapper.CategoryMapper;
import com.devencarnacion.budget.model.Category;
import com.devencarnacion.budget.repository.CategoryRepository;
import com.devencarnacion.budget.repository.UserRepository;
import com.devencarnacion.budget.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDTO create(CategoryCreateRequestDTO request) {
        Category newCategory = categoryMapper.toEntity(request);

        newCategory.setUser(userRepository.getReferenceById(request.getUserId()));

        return categoryMapper.toResponse(categoryRepository.save(newCategory));
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<CategoryResponseDTO> getCategoriesByUserId(Long userId) {
        return categoryRepository.findCategoriesByUserId(userId)
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponseDTO update(Long categoryId, CategoryUpdateRequestDTO request) {
        Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        categoryMapper.updateEntityFromDto(request, category);

        return categoryMapper.toResponse(category);
    }

    @Override
    public void delete(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new RuntimeException("Category not found with id " + categoryId);
        }

        categoryRepository.deleteById(categoryId);
    }
}
