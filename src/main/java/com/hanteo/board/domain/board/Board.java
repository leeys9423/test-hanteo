package com.hanteo.board.domain.board;

import com.hanteo.board.domain.category.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    private Long id;
    private String name;
    private int boardNumber;
    private List<Category> categories = new ArrayList<>();

    @Builder
    public Board(Long id, String name, int boardNumber) {
        this.id = id;
        this.name = name;
        this.boardNumber = boardNumber;
    }

    public boolean isNotice() {
        return "공지사항".equals(name);
    }

    public boolean isAnonymous() {
        return "익명게시판".equals(name);
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }
}
