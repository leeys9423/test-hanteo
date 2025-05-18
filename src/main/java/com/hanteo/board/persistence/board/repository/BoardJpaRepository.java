package com.hanteo.board.persistence.board.repository;

import com.hanteo.board.persistence.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {
}
