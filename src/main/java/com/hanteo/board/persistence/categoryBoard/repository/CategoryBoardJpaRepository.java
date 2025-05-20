package com.hanteo.board.persistence.categoryBoard.repository;

import com.hanteo.board.persistence.category.entity.CategoryEntity;
import com.hanteo.board.persistence.categoryBoard.entity.CategoryBoardEntity;
import com.hanteo.board.persistence.categoryBoard.entity.CategoryBoardKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryBoardJpaRepository extends JpaRepository<CategoryBoardEntity, CategoryBoardKey> {
    List<CategoryBoardEntity> findByCategoryId(Long categoryId);
    List<CategoryBoardEntity> findByBoardId(Long boardId);

    // ID로 카테고리 조회 시 하위 카테고리까지 한 번에 가져오기
    @Query("SELECT DISTINCT c FROM CategoryEntity c " +
            "LEFT JOIN FETCH c.children c1 " +
            "LEFT JOIN FETCH c1.children c2 " +
            "WHERE c.id = :id")
    Optional<CategoryEntity> findByIdWithChildrenFetch(@Param("id") Long id);

    // 이름으로 카테고리 조회 시 하위 카테고리까지 한 번에 가져오기
    @Query("SELECT DISTINCT c FROM CategoryEntity c " +
            "LEFT JOIN FETCH c.children c1 " +
            "LEFT JOIN FETCH c1.children c2 " +
            "WHERE c.name = :name")
    List<CategoryEntity> findByNameWithChildrenFetch(@Param("name") String name);

    // 최상위 카테고리 조회 시 하위 카테고리까지 한 번에 가져오기
    @Query("SELECT DISTINCT c FROM CategoryEntity c " +
            "LEFT JOIN FETCH c.children c1 " +
            "LEFT JOIN FETCH c1.children c2 " +
            "WHERE c.level = 1")
    List<CategoryEntity> findAllTopLevelWithChildrenFetch();
}
