package com.hanteo.board.domain.categoryBoard.repository;

import com.hanteo.board.domain.categoryBoard.CategoryBoard;

import java.util.List;

public interface CategoryBoardRepository {
    List<CategoryBoard> findByCategoryId(Long categoryId);
    List<CategoryBoard> findByBoardId(Long boardId);
}
