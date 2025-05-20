package com.hanteo.board.domain.categoryBoard;

import com.hanteo.board.domain.board.Board;
import com.hanteo.board.domain.category.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryBoard {
    private Category category;
    private Board board;

    @Builder
    public CategoryBoard(Category category, Board board) {
        this.category = category;
        this.board = board;
    }
}
