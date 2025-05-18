package com.hanteo.board.persistence.category.repository;

import com.hanteo.board.persistence.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
}
