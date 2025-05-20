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

    @Override
    public Optional<Category> findByIdWithChildren(Long id) {
        return categoryJpaRepository.findByIdWithChildrenFetch(id)
                .map(this::mapToDomainWithChildren);
    }

    @Override
    public List<Category> findByNameWithChildren(String name) {
        return categoryJpaRepository.findByNameWithChildrenFetch(name).stream()
                .map(this::mapToDomainWithChildren)
                .toList();
    }

    @Override
    public List<Category> findAllTopLevelWithChildren() {
        return categoryJpaRepository.findAllTopLevelWithChildrenFetch().stream()
                .map(this::mapToDomainWithChildren)
                .toList();
    }

    // 자식 카테고리를 포함한 매핑 메서드
    private Category mapToDomainWithChildren(CategoryEntity entity) {
        if (entity == null) return null;

        Category category = CategoryEntity.toDomain(entity);

        if (entity.getChildren() != null) {
            List<Category> children = entity.getChildren().stream()
                    .map(this::mapToDomainWithChildren)
                    .toList();
            category.setChildren(children);
        }

        return category;
    }
}
