package com.hanteo.board.persistence.category.repository;

import com.hanteo.board.persistence.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByName(String name);
    List<CategoryEntity> findByParentId(Long parentId);
    List<CategoryEntity> findByLevel(int level);
}
