package com.hanteo.board.domain.category;

import com.hanteo.board.domain.board.Board;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    private Long id;
    private String name;
    private int level;
    private Long parentId;
    @Setter
    private List<Category> children = new ArrayList<>();
    private List<Board> boards = new ArrayList<>();

    @Builder
    public Category(Long id, String name, int level, Long parentId) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.parentId = parentId;
    }

    public boolean isTopLevel() {
        return level == 1;
    }

    public void addChild(Category child) {
        this.children.add(child);
    }

    public void addBoard(Board board) {
        this.boards.add(board);
    }
}
