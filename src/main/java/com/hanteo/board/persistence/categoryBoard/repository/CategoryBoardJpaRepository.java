package com.hanteo.board.persistence.categoryBoard.repository;

import com.hanteo.board.persistence.categoryBoard.entity.CategoryBoardEntity;
import com.hanteo.board.persistence.categoryBoard.entity.CategoryBoardKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryBoardJpaRepository extends JpaRepository<CategoryBoardEntity, CategoryBoardKey> {
    List<CategoryBoardEntity> findByCategoryId(Long categoryId);
    List<CategoryBoardEntity> findByBoardId(Long boardId);

    @Query("SELECT cb FROM CategoryBoardEntity cb JOIN FETCH cb.board WHERE cb.category.id IN :categoryIds")
    List<CategoryBoardEntity> findByCategoryIdsWithBoardsFetch(@Param("categoryIds") List<Long> categoryIds);
}
