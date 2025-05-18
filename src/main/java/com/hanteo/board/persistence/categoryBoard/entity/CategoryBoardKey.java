package com.hanteo.board.persistence.categoryBoard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class CategoryBoardKey implements Serializable {
    @Column(name = "category_id")
    @Comment("카테고리 ID")
    private Long categoryId;

    @Column(name = "board_id")
    @Comment("게시판 ID")
    private Long boardId;
}
