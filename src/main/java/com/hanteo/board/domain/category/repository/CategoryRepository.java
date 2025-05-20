package com.hanteo.board.domain.category.repository;

import com.hanteo.board.domain.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(Long id);
    List<Category> findByName(String name);
    List<Category> findByParentId(Long parentId);
    List<Category> findByLevel(int level);
}
