package com.hanteo.board.persistence.category.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Comment("카테고리 이름")
    private String name;

    @Column(nullable = false)
    @Comment("카테고리 레벨")
    private int level;

    @Column(name = "parent_id")
    @Comment("부모 카테고리 ID")
    private Long parentId;

    @Builder
    public CategoryEntity(String name, int level, Long parentId) {
        this.name = name;
        this.level = level;
        this.parentId = parentId;
    }
}
