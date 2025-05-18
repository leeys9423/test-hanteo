package com.hanteo.board.persistence.categoryBoard.entity;

import com.hanteo.board.persistence.board.entity.BoardEntity;
import com.hanteo.board.persistence.category.entity.CategoryEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "category_board")
public class CategoryBoardEntity {

    @EmbeddedId
    private CategoryBoardKey id;

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @MapsId("boardId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @Builder
    public CategoryBoardEntity(CategoryEntity category, BoardEntity board) {
        this.category = category;
        this.board = board;
        this.id = new CategoryBoardKey(category.getId(), board.getId());
    }
}
