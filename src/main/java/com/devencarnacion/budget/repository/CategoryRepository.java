package com.devencarnacion.budget.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devencarnacion.budget.model.Category;
import com.devencarnacion.budget.dto.category.SectionInfoDto;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUserId(Long userId);

    Optional<Category> findByIdAndUserId(Long categoryId, Long userId);

    boolean existsByIdAndUserId(Long categoryId, Long userId);

    boolean existsByNameAndUserId(String categoryId, Long userId);

    void deleteByIdAndUserId(Long id, Long userId);

    @Query("""
                SELECT new com.devencarnacion.budget.dto.category.SectionInfoDto(
                    c.categoryType,
                    COUNT(c),
                    COALESCE(SUM(c.monthlyBudget), 0)
                )
                FROM Category c
                WHERE c.user.id = :userId
                GROUP BY c.categoryType
            """)
    List<SectionInfoDto> getSectionSummaryByUser(@Param("userId") Long userId);

}
