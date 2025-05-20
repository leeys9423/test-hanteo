package com.hanteo.board.persistence.category.adapter;

import com.hanteo.board.domain.category.Category;
import com.hanteo.board.domain.category.repository.CategoryRepository;
import com.hanteo.board.persistence.category.entity.CategoryEntity;
import com.hanteo.board.persistence.category.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryAdapter implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Optional<Category> findById(Long id) {
        return categoryJpaRepository.findById(id)
                .map(CategoryEntity::toDomain);
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryJpaRepository.findByName(name).stream()
                .map(CategoryEntity::toDomain)
                .toList();
    }

    @Override
    public List<Category> findByParentId(Long parentId) {
        return categoryJpaRepository.findByParentId(parentId).stream()
                .map(CategoryEntity::toDomain)
                .toList();
    }

    @Override
    public List<Category> findByLevel(int level) {
        return categoryJpaRepository.findByLevel(level).stream()
                .map(CategoryEntity::toDomain)
                .toList();
    }
}
