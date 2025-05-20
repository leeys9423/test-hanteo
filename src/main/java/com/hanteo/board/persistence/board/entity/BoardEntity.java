package com.hanteo.board.persistence.board.entity;

import com.hanteo.board.domain.board.Board;
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

    public static Board toDomain(BoardEntity entity) {
        if (entity == null) return null;

        return Board.builder()
                .id(entity.id)
                .name(entity.name)
                .boardNumber(entity.boardNumber)
                .build();
    }

    public static BoardEntity fromDomain(Board domain) {
        if (domain == null) return null;

        return BoardEntity.builder()
                .name(domain.getName())
                .boardNumber(domain.getBoardNumber())
                .build();
    }
}

