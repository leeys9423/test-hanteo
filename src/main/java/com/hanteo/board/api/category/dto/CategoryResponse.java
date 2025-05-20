package com.hanteo.board.api.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private int level;
    private Long parentId;
    private List<CategoryResponse> children;
    private List<BoardResponse> boards;
}
