package com.hanteo.board.application.category.service;

import com.hanteo.board.api.category.dto.BoardResponse;
import com.hanteo.board.api.category.dto.CategoryResponse;
import com.hanteo.board.domain.board.Board;
import com.hanteo.board.domain.board.exception.CategoryException;
import com.hanteo.board.domain.board.repository.BoardRepository;
import com.hanteo.board.domain.category.Category;
import com.hanteo.board.domain.category.repository.CategoryRepository;
import com.hanteo.board.domain.categoryBoard.CategoryBoard;
import com.hanteo.board.domain.categoryBoard.repository.CategoryBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;
    private final CategoryBoardRepository categoryBoardRepository;

    // ID로 카테고리 조회
    public CategoryResponse findCategoryResponseById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(CategoryException::notFound);

        return buildCategoryHierarchy(category);
    }

    // 이름으로 카테고리 목록 조회
    public List<CategoryResponse> findCategoryResponsesByName(String name) {
        List<Category> categories = categoryRepository.findByName(name);

        if (categories.isEmpty()) {
            throw CategoryException.notFound();
        }

        return categories.stream()
                .map(this::buildCategoryHierarchy)
                .toList();
    }

    // 전체 카테고리 조회 (최상위 카테고리만)
    public List<CategoryResponse> findAllCategoryResponses() {
        List<Category> topLevelCategories = categoryRepository.findByLevel(1);

        return topLevelCategories.stream()
                .map(this::buildCategoryHierarchy)
                .toList();
    }

    // 카테고리의 계층 구조를 재귀적으로 구성
    private CategoryResponse buildCategoryHierarchy(Category category) {
        // 자식 카테고리 조회
        List<Category> children = categoryRepository.findByParentId(category.getId());

        // 카테고리에 연결된 게시판 조회
        List<CategoryBoard> categoryBoards = categoryBoardRepository.findByCategoryId(category.getId());
        List<Board> boards = categoryBoards.stream()
                .map(CategoryBoard::getBoard)
                .toList();

        // 자식 카테고리의 계층 구조 재귀적으로 구성
        List<CategoryResponse> childResponses = children.stream()
                .map(this::buildCategoryHierarchy)
                .toList();

        // DTO 변환
        return convertToCategoryResponse(category, childResponses, boards);
    }

    // Category를 CategoryResponse로 변환
    private CategoryResponse convertToCategoryResponse(
            Category category,
            List<CategoryResponse> children,
            List<Board> boards) {

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .level(category.getLevel())
                .parentId(category.getParentId())
                .children(children)
                .boards(boards.stream()
                        .map(this::convertToBoardResponse)
                        .toList())
                .build();
    }

    // Board를 BoardResponse로 변환
    private BoardResponse convertToBoardResponse(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .name(board.getName())
                .boardNumber(board.getBoardNumber())
                .build();
    }
}
