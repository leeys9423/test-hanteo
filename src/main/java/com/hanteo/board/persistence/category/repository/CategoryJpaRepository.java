package com.hanteo.board.persistence.category.repository;

import com.hanteo.board.persistence.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByName(String name);
    List<CategoryEntity> findByParentId(Long parentId);
    List<CategoryEntity> findByLevel(int level);

    @Query("SELECT DISTINCT c FROM CategoryEntity c LEFT JOIN FETCH c.children WHERE c.id = :id")
    Optional<CategoryEntity> findByIdWithChildrenFetch(@Param("id") Long id);

    @Query("SELECT DISTINCT c FROM CategoryEntity c LEFT JOIN FETCH c.children WHERE c.name = :name")
    List<CategoryEntity> findByNameWithChildrenFetch(@Param("name") String name);

    @Query("SELECT DISTINCT c FROM CategoryEntity c LEFT JOIN FETCH c.children WHERE c.level = 1")
    List<CategoryEntity> findAllTopLevelWithChildrenFetch();
}
