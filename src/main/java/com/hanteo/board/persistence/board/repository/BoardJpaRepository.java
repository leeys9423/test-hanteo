package com.hanteo.board.persistence.board.repository;

import com.hanteo.board.persistence.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByName(String name);
    Optional<BoardEntity> findByBoardNumber(int boardNumber);
}
