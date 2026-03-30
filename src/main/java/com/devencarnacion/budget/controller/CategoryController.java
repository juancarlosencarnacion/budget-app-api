package com.devencarnacion.budget.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;

import com.devencarnacion.budget.dto.category.CategoryCreateRequestDTO;
import com.devencarnacion.budget.dto.category.CategoryResponseDTO;
import com.devencarnacion.budget.dto.category.CategoryUpdateRequestDTO;
import com.devencarnacion.budget.dto.category.SectionInfoDto;
import com.devencarnacion.budget.enums.category.CategoryTypeEnum;
import com.devencarnacion.budget.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@Valid @RequestBody CategoryCreateRequestDTO request) {
        CategoryResponseDTO response = categoryService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllForCurrentUser() {
        return ResponseEntity.ok(categoryService.getAllForCurrentUser());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable Long categoryId,
            @Valid @RequestBody CategoryUpdateRequestDTO request) {
        return ResponseEntity.ok(categoryService.update(categoryId, request));
    }

    // DELETE
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sectionHeaders")
    public ResponseEntity<List<SectionInfoDto>> getSectionHeaders() {
        return ResponseEntity.ok(categoryService.getSectionHeaders());
    }

    @GetMapping("/grouped")
    public ResponseEntity<Map<CategoryTypeEnum, List<CategoryResponseDTO>>> getCategoriesGrouped() {
        return ResponseEntity.ok(categoryService.getCategoriesGrouped());
    }

    
}
