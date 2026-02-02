package com.devencarnacion.budget.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devencarnacion.budget.dto.category.CategoryResponseDTO;
import com.devencarnacion.budget.model.Category;
import com.devencarnacion.budget.model.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoriesByUserId(Long userId);

    List<CategoryResponseDTO> findByUser(User user);
}
