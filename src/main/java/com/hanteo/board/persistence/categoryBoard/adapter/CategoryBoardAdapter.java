package com.hanteo.board.persistence.categoryBoard.adapter;

import com.hanteo.board.domain.board.Board;
import com.hanteo.board.domain.category.Category;
import com.hanteo.board.domain.categoryBoard.CategoryBoard;
import com.hanteo.board.domain.categoryBoard.repository.CategoryBoardRepository;
import com.hanteo.board.persistence.board.entity.BoardEntity;
import com.hanteo.board.persistence.board.repository.BoardJpaRepository;
import com.hanteo.board.persistence.category.entity.CategoryEntity;
import com.hanteo.board.persistence.category.repository.CategoryJpaRepository;
import com.hanteo.board.persistence.categoryBoard.entity.CategoryBoardEntity;
import com.hanteo.board.persistence.categoryBoard.repository.CategoryBoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryBoardAdapter implements CategoryBoardRepository {

    private final CategoryBoardJpaRepository categoryBoardJpaRepository;
    private final CategoryJpaRepository categoryJpaRepository;
    private final BoardJpaRepository boardJpaRepository;

    @Override
    public List<CategoryBoard> findByCategoryId(Long categoryId) {
        return categoryBoardJpaRepository.findByCategoryId(categoryId).stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public List<CategoryBoard> findByCategoryIdsWithBoards(List<Long> categoryIds) {
        return categoryBoardJpaRepository.findByCategoryIdsWithBoardsFetch(categoryIds).stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public List<CategoryBoard> findByBoardId(Long boardId) {
        return categoryBoardJpaRepository.findByBoardId(boardId).stream()
                .map(this::mapToDomain)
                .toList();
    }

    private CategoryBoard mapToDomain(CategoryBoardEntity entity) {
        Category category = CategoryEntity.toDomain(entity.getCategory());
        Board board = BoardEntity.toDomain(entity.getBoard());

        return new CategoryBoard(category, board);
    }
}
