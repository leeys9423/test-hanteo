package com.hanteo.board.api.category.controller;

import com.hanteo.board.api.category.dto.CategoryResponse;
import com.hanteo.board.application.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 단일 카테고리 조회 (ID로)
    @GetMapping("/api/v1/categories/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findCategoryResponseById(id));
    }

    // 카테고리 목록 조회 (이름으로 검색 또는 전체 조회)
    @GetMapping("/api/v1/categories")
    public ResponseEntity<List<CategoryResponse>> getCategories(
            @RequestParam(required = false) String name) {

        if (name != null && !name.isEmpty()) {
            return ResponseEntity.ok(categoryService.findCategoryResponsesByName(name));
        } else {
            return ResponseEntity.ok(categoryService.findAllCategoryResponses());
        }
    }
}
