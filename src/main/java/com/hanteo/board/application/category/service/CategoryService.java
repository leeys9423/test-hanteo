package com.hanteo.board.application.category.service;

import com.hanteo.board.api.category.dto.BoardResponse;
import com.hanteo.board.api.category.dto.CategoryResponse;
import com.hanteo.board.domain.board.Board;
import com.hanteo.board.domain.board.exception.CategoryException;
import com.hanteo.board.domain.category.Category;
import com.hanteo.board.domain.category.repository.CategoryRepository;
import com.hanteo.board.domain.categoryBoard.CategoryBoard;
import com.hanteo.board.domain.categoryBoard.repository.CategoryBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
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

    // ID로 카테고리 조회 (최적화)
    public CategoryResponse findCategoryResponseByIdV2(Long id) {
        // 카테고리와 하위 카테고리를 한 번에 조회
        Category category = categoryRepository.findByIdWithChildren(id)
                .orElseThrow(CategoryException::notFound);

        // 카테고리 계층 구조에서 모든 카테고리 ID 수집
        List<Long> allCategoryIds = collectAllCategoryIds(category);

        // 모든 관련 게시판을 한 번에 조회
        List<CategoryBoard> categoryBoards = categoryBoardRepository.findByCategoryIdsWithBoards(allCategoryIds);

        // 카테고리 ID별 게시판 매핑 구성
        Map<Long, List<Board>> categoryBoardsMap = new HashMap<>();
        for (CategoryBoard cb : categoryBoards) {
            Long categoryId = cb.getCategory().getId();
            Board board = cb.getBoard();

            if (!categoryBoardsMap.containsKey(categoryId)) {
                categoryBoardsMap.put(categoryId, new ArrayList<>());
            }
            categoryBoardsMap.get(categoryId).add(board);
        }

        // 응답 DTO 구성
        return buildOptimizedResponse(category, categoryBoardsMap);
    }

    // 이름으로 카테고리 조회 (최적화)
    public List<CategoryResponse> findCategoryResponsesByNameV2(String name) {
        // 카테고리와 하위 카테고리를 한 번에 조회
        List<Category> categories = categoryRepository.findByNameWithChildren(name);

        if (categories.isEmpty()) {
            throw CategoryException.notFound();
        }

        // 모든 카테고리 ID 수집
        List<Long> allCategoryIds = new ArrayList<>();
        for (Category category : categories) {
            allCategoryIds.addAll(collectAllCategoryIds(category));
        }

        // 모든 관련 게시판을 한 번에 조회
        List<CategoryBoard> categoryBoards = categoryBoardRepository.findByCategoryIdsWithBoards(allCategoryIds);

        // 카테고리 ID별 게시판 매핑 구성
        Map<Long, List<Board>> categoryBoardsMap = new HashMap<>();
        for (CategoryBoard cb : categoryBoards) {
            Long categoryId = cb.getCategory().getId();
            Board board = cb.getBoard();

            if (!categoryBoardsMap.containsKey(categoryId)) {
                categoryBoardsMap.put(categoryId, new ArrayList<>());
            }
            categoryBoardsMap.get(categoryId).add(board);
        }

        // 응답 DTO 구성
        return categories.stream()
                .map(category -> buildOptimizedResponse(category, categoryBoardsMap))
                .toList();
    }

    // 전체 카테고리 조회 (최적화)
    public List<CategoryResponse> findAllCategoryResponsesV2() {
        // 최상위 카테고리와 하위 카테고리를 한 번에 조회
        List<Category> topLevelCategories = categoryRepository.findAllTopLevelWithChildren();

        // 모든 카테고리 ID 수집
        List<Long> allCategoryIds = new ArrayList<>();
        for (Category category : topLevelCategories) {
            allCategoryIds.addAll(collectAllCategoryIds(category));
        }

        // 모든 관련 게시판을 한 번에 조회
        List<CategoryBoard> categoryBoards = categoryBoardRepository.findByCategoryIdsWithBoards(allCategoryIds);

        // 카테고리 ID별 게시판 매핑 구성
        Map<Long, List<Board>> categoryBoardsMap = new HashMap<>();
        for (CategoryBoard cb : categoryBoards) {
            Long categoryId = cb.getCategory().getId();
            Board board = cb.getBoard();

            if (!categoryBoardsMap.containsKey(categoryId)) {
                categoryBoardsMap.put(categoryId, new ArrayList<>());
            }
            categoryBoardsMap.get(categoryId).add(board);
        }

        // 응답 DTO 구성
        return topLevelCategories.stream()
                .map(category -> buildOptimizedResponse(category, categoryBoardsMap))
                .toList();
    }

    // 카테고리와 하위 카테고리의 모든 ID 수집
    private List<Long> collectAllCategoryIds(Category category) {
        List<Long> ids = new ArrayList<>();
        ids.add(category.getId());

        for (Category child : category.getChildren()) {
            ids.addAll(collectAllCategoryIds(child));
        }

        return ids;
    }

    // 최적화된 응답 DTO 구성
    private CategoryResponse buildOptimizedResponse(
            Category category,
            Map<Long, List<Board>> categoryBoardsMap) {

        // 자식 카테고리 응답 구성
        List<CategoryResponse> childResponses = category.getChildren().stream()
                .map(child -> buildOptimizedResponse(child, categoryBoardsMap))
                .toList();

        // 게시판 응답 구성
        List<BoardResponse> boardResponses = new ArrayList<>();
        if (categoryBoardsMap.containsKey(category.getId())) {
            boardResponses = categoryBoardsMap.get(category.getId()).stream()
                    .map(this::convertToBoardResponse)
                    .toList();
        }

        // 카테고리 응답 구성
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .level(category.getLevel())
                .parentId(category.getParentId())
                .children(childResponses)
                .boards(boardResponses)
                .build();
    }
}
