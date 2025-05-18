package com.hanteo.board.persistence.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "boards")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Comment("이름")
    private String name;

    @Column(nullable = false, unique = true, name = "board_number")
    @Comment("게시판 번호")
    private int boardNumber;

    @Builder
    public BoardEntity(String name, int boardNumber) {
        this.name = name;
        this.boardNumber = boardNumber;
    }
}

