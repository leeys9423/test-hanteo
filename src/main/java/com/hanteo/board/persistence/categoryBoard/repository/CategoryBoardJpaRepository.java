package com.hanteo.board.persistence.categoryBoard.repository;

import com.hanteo.board.persistence.categoryBoard.entity.CategoryBoardEntity;
import com.hanteo.board.persistence.categoryBoard.entity.CategoryBoardKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryBoardJpaRepository extends JpaRepository<CategoryBoardEntity, CategoryBoardKey> {
}
