package com.hanteo.board.persistence.category.entity;

import com.hanteo.board.domain.category.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Set<CategoryEntity> children = new HashSet<>();

    @Builder
    public CategoryEntity(String name, int level, Long parentId) {
        this.name = name;
        this.level = level;
        this.parentId = parentId;
    }

    public static Category toDomain(CategoryEntity entity) {
        if (entity == null) return null;

        return Category.builder()
                .id(entity.id)
                .name(entity.name)
                .level(entity.level)
                .parentId(entity.parentId)
                .build();
    }

    public static CategoryEntity fromDomain(Category domain) {
        if (domain == null) return null;

        return CategoryEntity.builder()
                .name(domain.getName())
                .level(domain.getLevel())
                .parentId(domain.getParentId())
                .build();
    }
}
